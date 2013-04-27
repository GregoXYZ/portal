package common;

import java.util.regex.Pattern;

public interface Constants {
	
	/**
	 * Formato de fecha por defecto (para usar con SimpleDateFormat)
	 */
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

	public static final Pattern HTTP_STD_PORT_PATTERN = Pattern.compile("^(http://[^:]+):80/(.*)$");
	public static final Pattern HTTPS_STD_PORT_PATTERN = Pattern.compile("^(https://[^:]+):443/(.*)$");

	public static final String ALGORITHM_HASH_FILE = "SHA1";
	
	public static final String SESSION_COOKIE = "SSOSESSIONID";
	public static final String SHARE_SESSION_COOKIE = "SHARESESSIONID";

	public static final String MENU_ICON_FOLDER = "/images/menus/";
	public static final String DEFAULT_MENU_ICON = "empty.png";

	public static final String TEMP_USER_DIR = "/data/users/";
	public static final String DEFAUL_USER_DIR = "DATA_PATH";
	public static final String DEFAUL_AVATAR = "nobody.png";
	public static final String DEFAUL_AVATAR_DIR = "avatar/";
	public static final Long MAX_SIZE_AVATAR = 20480L;
	
	public final static int WRITE_BUFFER_SIZE = 1024*4;	
	public final static int READ_BUFFER_SIZE = 1024*4;
	
	public final static String SYSTEM_USER = "system";

	public static final String PAGE_LAST_VISITED = "portal.pageLastVisited";
	public static final String PAGE_BEFORE_LAST_VISITED = "portal.pageBeforeLastVisited";
	
	public static final Long RELACION_SOLICITADA = 1L;
	public static final Long RELACION_ACEPTADA = 2L;
	
	public static final int	SOLICITAR_RELACION = 1;
	public static final int	CANCELAR_SOLICITUD = 2;
	public static final int	ACEPTAR_SOLICITUD = 3;
	public static final int	RECHAZAR_SOLICITUD = 4;
	public static final int	CANCELAR_RELACION = 5;
	
	public static final int AVISO_COMPARTIDOS = 1;
	public static final int AVISO_AMISTAD = 2;	
	public static final int AVISO_AMISTAD_ACEPTDA = 3;	
	public static final int AVISO_MENSAJE = 4;	
	public static final int AVISO_FORO = 5;	
}
