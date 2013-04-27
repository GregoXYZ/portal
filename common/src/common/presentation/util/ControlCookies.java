package common.presentation.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.ConfigProperties;

public class ControlCookies {

	/***********************************************************************************
	 * Actualizo la cookie con 'nuevoValor'. Si no hubiese
	 * cookie, la creo de nuevo y almaceno igualmente el nuevo valor.
	 **********************************************************************************/
	public static void actualizarCookie(HttpServletRequest request,
			HttpServletResponse response,
			String cookie, String nuevoValor) {

		// // Obtener cookie
		Cookie galleta = getCookie(request, cookie);

		// // Creo nueva cookie con el nombre de la anterior y añado la cookie a
		// la cabecera de la respuesta
		galleta = crearCookie(cookie, nuevoValor);
		response.addCookie(galleta);
	}

	/***********************************************************************************
	 * Obtengo todas las cookies a partir de la petición (request). Devuelve
	 * aquella cuyo nombre es 'cookieName'. Si no la encuentra, devuelve null.
	 **********************************************************************************/
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return cookie;
		}
		return null;
	}

	/*******************************************************************************
	 * Obtiene las cookies de la petición (request). Busca una que coincida con
	 * 'cookieName'. Si lo encuentra, devuelve su valor; si no, devuelve
	 * 'defaultValue'
	 *******************************************************************************/
	public static String getCookieValue(HttpServletRequest request,
			String cookieName, String defaultValue) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return (cookie.getValue());
		}
		
		return defaultValue;
	}

	/***********************************************************************************
	 * Crea una cookie cuyo nombre y valor son los argumentos. Duración: expireTime
	 * (atributo en segundos).
	 **********************************************************************************/
	public static Cookie crearCookie(String nombre, String valor, HttpServletRequest request) {
		Cookie c = new Cookie(nombre, valor);
		String expireTime = ConfigProperties.getInstance().get("cookieExpireTime");

		String host = request.getHeader("Host");
		
		c.setDomain(host);

		if ( expireTime != null)
		{
			c.setMaxAge(Integer.valueOf(expireTime) * 60);			
		}
		return c;
	}

	public static Cookie crearCookie(String nombre, String valor) {
		Cookie c = new Cookie(nombre, valor);
		String expireTime = ConfigProperties.getInstance().get("cookieExpireTime");
		
		if ( expireTime != null)
		{
			c.setMaxAge(Integer.valueOf(expireTime) * 60);			
		}
		return c;
	}

	public static void eliminarCookie(HttpServletRequest request, HttpServletResponse response, String name)
	{
		Cookie cookie = getCookie(request, name);
		if ( cookie != null ){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.setContentType("text/html");
		}
	}
	
	public static void limpiaCookies(HttpServletRequest request, HttpServletResponse response)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				//cookie.setPath("/");
				//cookie.setDomain(request.getHeader("host"));
				response.addCookie(cookie);
				response.setContentType("text/html");
			}			
		}
	}
}
