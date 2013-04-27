package common.business.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.util.ConfigProperties;



public class ConnectionPool {

	// DataSource para las consutlas
	// ahora lo lee de fichero de configuracion
	private static String DATA_SOURCE_NAME;
	
	// Logger de la clase
	private final Log logger = LogFactory.getLog(this.getClass());
	
	// DataSource
	private DataSource ds;
	
	// UserName
	private String user;
	
	// Instancia del Singleton
	private static ConnectionPool _instance = null;
	
	// Inicializador de clase
	static {
		_instance = new ConnectionPool();
	}
	
	// Constructor privado
	private ConnectionPool () {
		DATA_SOURCE_NAME = ConfigProperties.getInstance().get("datasource");
		
		this.initDataSource();
	}
	
	
	// Acceso al Singleton
	public static ConnectionPool getInstance () {
		return _instance;
	}
		
	// Metodo que inicializa la fuente de datos
	private void initDataSource () {
		try {
			
			Context ctx = new InitialContext();			
			
			ds = (DataSource)ctx.lookup(DATA_SOURCE_NAME);
			
			if (ds == null) {
				logger.error("DataSource not found!");
			} else {
				logger.info("Got DataSource.");
			}
			
		} catch (NamingException ne) {
			logger.error(ne.getMessage());
		}
	}
	
	
	// Metodo para obtener una conexion a BBDD
	public Connection getConnection () throws SQLException {
		Connection con = ds.getConnection();
		user = con.getMetaData().getUserName();	
		return (con);
	}

	// Metodo para obtener el usuario asociado a la conexion
	public String getUser() {
		return user;
	}
	
}
