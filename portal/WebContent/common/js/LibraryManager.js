
var LibraryManager = Class.create({
    initialize: function(){
        var carpetaBase = "common/";
        this._rutaArchivos = new Array(); // ruta según tipo de archivo (manejado por extensión)
        this._rutaArchivos["css"] = carpetaBase + "css/";
        this._rutaArchivos["js"] = carpetaBase + "js/";
        this._archivosCargandose = new Array(); // archivos que actualmente se están cargando
        this._archivosCargados = new Array(); // archivos que ya se han cargado
    },
	/**
	 * Método reiterativo. Carga un archivo por cada llamada.
	 * @param {Object} libreria (Array) 	-> Listado de archivos a cagar
	 * @param {Object} callback (Function) 	-> Se llamará esta función al terminar la carga de todos los archivos
	 */
    cargar: function(libreria, callback){
        if (libreria.length == 0) 
            callback.call();
        else {
            // si no se ha cargado
            if (!this._archivosCargados[libreria.first()]) {
                var scope = this;
                // si ya se está cargando, esperar a que esté listo antes de continuar
                // (ésto es útil en caso de cargar múltiples librerías a la vez que requieran ese mismo archivo)
                if (this._archivosCargandose[libreria.first()]) {
                    var interval = setInterval(function(){
                        if (scope._archivosCargados[libreria.first()]) {
                            libreria.shift();
                            clearInterval(interval);
                            scope.cargar(libreria, callback);
                        }
                    }, 10);
                }
                else {
                    var tipo = libreria.first().split(".").last();
                    this._archivosCargandose[libreria.first()] = true;
                    switch (tipo) {
                        case "css":
                            var nodoCss = document.createElement('link');
                            with (nodoCss) {
                                type = 'text/css';
                                rel = 'stylesheet';
                                media = 'screen';
                            }
                            nodoCss.href = this._rutaArchivos[tipo] + libreria.first();
                            document.getElementsByTagName("head")[0].appendChild(nodoCss);
                            this._archivosCargados[libreria.first()] = true;
                            libreria.shift();
                            this.cargar(libreria, callback);
                            break;
                        case "js":
                            new Ajax.Request(this._rutaArchivos[tipo] + libreria.first(), {
                                method: 'get',
                                onComplete: function(){
                                    scope._archivosCargados[libreria.first()] = true;
                                    libreria.shift();
                                    scope.cargar(libreria, callback);
                                }
                            });
                            break;
                        default:
                            libreria.shift();
                            this.cargar(libreria, callback);
                    }
                }
            }
            // si ya se ha cargado
            else {
                libreria.shift();
                this.cargar(libreria, callback);
            }
        }
    }
});

var libraryManager = new LibraryManager();
