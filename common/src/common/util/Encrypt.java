package common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.axis.encoding.Base64;

public final class Encrypt {
	public static String encriptar(String textoplano) throws IllegalStateException {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA"); // Instancia de generador
													// SHA-1
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e.getMessage());
		}

		try {
			md.update(textoplano.getBytes("UTF-8")); // Generación de resumen de
														// mensaje
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e.getMessage());
		}

		byte raw[] = md.digest(); // Obtención del resumen de mensaje
//		String hash = (new BASE64Encoder()).encode(raw); // Traducción a BASE64
		String hash = Base64.encode(raw); // Traducción a BASE64
		return hash;
	}
}