package common;

public interface IConstantes extends Constants {
	public static final Long TIPO_ASSET_CARPETA = 1L;
	public static final Long TIPO_ASSET_FICHERO = 2L;
	public static final Long TIPO_ASSET_URL = 3L;

	public static final Integer NUBE_CONTADOR = 1;
	public static final Integer NUBE_TOTAL_BUSQUEDAS = 2;
	public static final Integer NUBE_BUSQUEDA_PERSONAL = 3;

	public static final String DEFAUL_MIME_ICON_DIR = "images/mimeicons/";

	public static final String DEFAUL_ICON_DIR = "images/";
	public static final String DEFAUL_ICON_FOLDER = "folder.png";
	public static final String DEFAUL_ICON_FILE = "mimeicons/unknown.png";
	public static final String DEFAUL_ICON_LINK = "mimeicons/unknown.png";
	public static final String DEFAUL_UP_ICON = "up.png";
	
	public static final Long PESO_CARPETA = 5120L; 	// Indica el tamaño que ocupa una carpeta en bits
													// Este valor sólo se tiene en cuenta a la hora de crear
	
	public static final String UNKNOWN_IMAGE = "images/enigma.jpg";
	public static final String UNKNOWN_MULTIMEDIA = "sounds/dialog-warning.ogg";
	public static final String MINIATURES_FOLDER = "thumbs/";

	//tipo de Mime que no se trata como un attachment
	public static final String HTML_MIME = "text/html";
}
