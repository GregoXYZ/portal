package common.presentation.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;

public class LocaleHelper {
	public static Locale getLocale(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
		if(locale==null) {
			locale = request.getLocale();
		}
		if(locale==null) {
			locale = new Locale("es");
		}
		if( !"es".equals(locale.getLanguage()) && !"ca".equals(locale.getLanguage()) ) {
			locale = new Locale("es");
		}
		return locale;
	}
	
	public static String getLanguage(HttpServletRequest request) {
		Locale locale = getLocale(request);
		String language = "es";
		if(locale!=null) {
			language = locale.getLanguage();
		} else {
			language = "es";
		}
		if( !"es".equals(language) && !"ca".equals(language) ) {
			language = "es";
		}
		return language;
	}
}
