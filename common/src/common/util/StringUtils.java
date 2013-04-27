package common.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * Comprueba si el mail esta formado correctamente
	 * 
	 * @param mail
	 * @return 
	 */
	public Boolean checkEmailAddress(String mail)
	{
		Pattern pattern_email = Pattern.compile("/b(^(S+@).+((.com)|(.net)|(.edu)|(.mil)|(.gov)|(.org)(.info)(.biz)|(..{2,2}))$)b/gi");
		Matcher matcher = pattern_email.matcher(mail);
		return matcher.matches();
	}

	/**
	 * Nos pasan un array e inicializamos a "" las posiciones que estan a null, las que
	 * ya estan inicializadas no se tocaran.
	 * 
	 * @param s
	 */
	public static void initArray(String [] s) {
		for(int i = 0; i < s.length; i++) {
			if(s[i] == null) {
				s[i] = "";
			}
		}
	}
	
	/**
	 * Devuelve una String que representa todos los valors del array separados 
	 * por separator.
	 * 
	 * @param s
	 * @param separator
	 * @return
	 */
	public static String listArray(String [] s, String separator) {
		if(s.length > 0) {
			String res = s[0];
			for(int i = 1; i < s.length; i++) {
				res += separator + s[i];
			}
			return res;
		}
		else {
			return "";
		}
	}
	
	/**
	 * Nos devuelve el numero de ocurrencias del array que cumplen la expresion
	 * regular regex.
	 * 
	 * @param s
	 * @param regex
	 * @return
	 */
	public static int numeroMatches(String [] s, String regex) {
		int cont = 0; 
		Pattern p = Pattern.compile(regex);
		for(int i = 0; i < s.length; i++) {
			Matcher m = p.matcher(s[i]);
			if(m.matches()) {
				cont++;
			}
		}
		
		return cont;
	}
	
	
	/**
	 * Fusiona los dos arrays de strings en 1 solo.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String [] fusionArrays(String [] s1, String [] s2) {
		String [] s = new String[s1.length + s2.length];
		initArray(s);
		
		for(int i = 0; i < s1.length; i++) {
			s[i] = s1[i];
		}
		
		for(int i = 0; i < s2.length; i++) {
			s[i+s1.length] = s2[i];
		}
		
		return s;
	}
	
	/**
	 * Fusiona los dos arrays de strings en 1 solo con un tama�o minimo especificado en el parametro min
	 * 
	 * @param s1
	 * @param s2
	 * @param min
	 * @return
	 */
	public static String [] fusionArrays(String [] s1, String [] s2, int min) {
		String [] s; 
		if(min > s1.length + s2.length) {
			s = new String[min];
		}
		else {
			s = new String[s1.length + s2.length];
		}
		initArray(s);
		
		for(int i = 0; i < s1.length; i++) {
			s[i] = s1[i];
		}
		
		for(int i = 0; i < s2.length; i++) {
			s[i+s1.length] = s2[i];
		}
		
		return s;
	}
	
	/**
	 * Devuelve un array con todas las posiciones que coinciden con el string s eliminadas.
	 * 
	 * @param s
	 * @param regex
	 * @return
	 */
	public static String [] removePositionByString(String [] array, String s) {
		List<String> l = new ArrayList<String>();
		for(int i = 0; i < array.length; i++) {
			if(array[i].equals(s) == false) {
				l.add(array[i]);
			}
		}
		
		return StringUtils.convertObject2String(l.toArray());
	}
	
	
	/**
	 * Devuelve un String [] sin posiciones repetidas con el mismo valor.
	 * 
	 * No pueden passar-se un String[] donde algun valor sea null.
	 * 
	 * @param s
	 * @return
	 */
	public static String [] removeRepetidos(String [] s) {
		List<String> l = new ArrayList<String>();
		while(s.length > 0) {
			l.add(s[0]);
			s = StringUtils.removePositionByString(s, s[0]);
		}
		
		return StringUtils.convertObject2String(l.toArray());
	}
	
	
	/**
	 * Devuelve un array de String a partir de un array de Object. Funcion util cuando tenemos un objeto
	 * java.util.List de Strings y llamamos la funcino toArray que nos devuelve un Object[]. Como no se
	 * puede hacer un cast a String[], usamos esta funcion para ahorrarnos un bucle para hacer la conversion.
	 * 
	 * Si los Object no son Strings no pasa nada pq llamaremos la funcion toString
	 * @param o
	 * @return
	 */
	public static String[] convertObject2String(Object [] o) {
		String [] s = new String[o.length];
		
		for(int i = 0; i < s.length; i++) {
			s[i] = o[i].toString();
		}
		
		return s;
	}
	
	/**
	 * Devuelve un array con la posicion indicada eliminada. En caso que la posicion
	 * no sea correcta, devuelve el mismo array.
	 * 
	 * @param s
	 * @param position
	 * @return
	 */
	public static String [] removePosition(String [] s, int position) {
		if(position < 0) return s;
		
		if(position >= s.length) return s;
		
		String [] res = new String[s.length - 1];
		
		int j = 0;
		for(int i = 0; i < s.length; i++) {
			if(i != position) {
				res[j] = s[i];
				j++;
			}
		}
		
		return res;
	}
	
	/**
	 * Reemplaza la expresion regular especificada en regex por el String replacement, en
	 * cada posicion del array. Es lo mismo que la funcion replaceALl de String pero 
	 * aplicado a un vector.
	 * @param s
	 * @return
	 */
	public static String [] replaceAll(String [] s, String regex, String replacement) {
		for(int i = 0; i < s.length; i++) {
			s[i] = s[i].replaceAll(regex, replacement);
		}
		
		return s;
	}
	
	/**
	 * igual que la funcio de split de String, nomes que en el seg�ent exemple:
	 * 
	 *	"hola///adeu".split("/");
	 *
	 * en l'objecte String ens retornaria un String[2] amb hola i adeu, i en 
	 * aquesta funcio retorna un String[4] on dues posicions retorna un String buit.
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public static String [] split(String s, String token) {
		int i = 0;
		int aux = 0;
		List<String> l = new ArrayList<String>();
		
		while((aux = s.indexOf(token, aux)) != -1) {
			l.add(s.substring(i, aux));
			i = aux + 1;
			aux++;
			
		}
		l.add(s.substring(i));
		
		String [] res = new String[l.size()];
		for(int j = 0; j < res.length; j++) {
			res[j] = (String)l.get(j);
		}
		return res;
	}
	
	
	/**
	 * Formateja un text d'entrada. Consisteix en verificar si es un 
	 * String a null, en el qual retornem el String buit.
	 * 
	 * @param s
	 * @return
	 */
	public static String formatText(String s) {
		return s==null?"":s;
	}
	
	/**
	 * Formateja un objecte d'entrada. Consisteix en verificar si es un 
	 * Object a null, en el qual retornem el String buit.
	 * 
	 * @param s
	 * @return
	 */
	public static String formatText(Object o) {
		if(o == null) {
			return "";
		}
		return o.toString();
	}
	
	/**
	 * Ens retorna true si el string s es null o te 0 caracters (cadena buida)
	 * altrament false.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullBuit(String s) {
		if(s == null) {
			return true;
		}
		if(s.trim().length() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Ens retorna true si el string es null o te 0 caracters o sino es buit te
	 * una longitud diferent del parametre tam que ens passen.
	 * 
	 * Altrament retorna false.
	 * 
	 * @param s
	 * @param tam
	 * @return
	 */
	public static boolean isNullBuitDiferentTamany(String s, int tam) {
		if(StringUtils.isNullBuit(s) == true) {
			return true;
		}
		// Aqui assegurem que el string no te 0 caracters.
		if(s.length() != tam) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Nos dice si el String s es un numero valido entero valido (sin coma flotante).
	 * Tambien retornara false si el String es null o vacio.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumero(String s) {
		if(s == null) {
			return false;
		}
		if(s.matches("[0-9]+")) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * A�ade los zeros que sean necesarios a la izquierda del codigo para
	 * que tenga la longitud definida en numeroDigitos. Si el codigo que nos
	 * tiene mas caracteres que numeroDigitos, se devuelve sin hacer cambio alguno
	 * 
	 * @param codigo
	 * @param numeroDigitos
	 * @return
	 */
	public static String rellenarZeros(int codigo, int numeroDigitos) {
		return StringUtils.rellenarZeros(codigo + "", numeroDigitos);
	}
	
	
	/**
	 * 
	 * A�ade los zeros que sean necesarios a la izquierda del codigo para
	 * que tenga la longitud definida en numeroDigitos. Si el codigo que nos
	 * tiene mas caracteres que numeroDigitos, se devuelve sin hacer cambio alguno
	 * 
	 * @param codigo
	 * @param numeroDigitos
	 * @return
	 */
	public static String rellenarZeros(byte codigo, int numeroDigitos) {
		return StringUtils.rellenarZeros(codigo + "", numeroDigitos);
	}
	
	/**
	 * A�ade los zeros que sean necesarios a la izquierda del codigo para
	 * que tenga la longitud definida en numeroDigitos. Si el codigo que nos
	 * tiene mas caracteres que numeroDigitos, se devuelve sin hacer cambio alguno
	 * 
	 * @param codigo
	 * @param numeroDigitos
	 * @return
	 */
	public static String rellenarZeros(long codigo, int numeroDigitos) {
		return StringUtils.rellenarZeros(codigo + "", numeroDigitos);
	}
	
	/**
	 * A�ade los zeros que sean necesarios a la izquierda del codigo para
	 * que tenga la longitud definida en numeroDigitos. Si el codigo que nos
	 * tiene mas caracteres que numeroDigitos, se devuelve sin hacer cambio alguno
	 * 
	 * @param codigo
	 * @param numeroDigitos
	 * @return
	 */
	public static String rellenarZeros(String codigo, int numeroDigitos) {
		for(int i = codigo.length(); i < numeroDigitos; i++) {
			codigo = "0" + codigo;
		}
		return codigo;		
	}
	
	/**
	 * Limita el tama�o maximo del string
	 * 
	 * @param string
	 * @param tamanyo
	 * 
	 */
	public static String limitSize(String s, int size) {
		if(s!=null && s.length()>size) {
			return s.substring(0, size);
		} else {
			return s;
		}
	}
	
	public static String trataCaracteresEspecialesSQL(String cadena)
	{
		return cadena==null?"":cadena.replace("'", "''").replace("%", " ");
	}

	
    public static Object escapeXML(Object data) {
		if (data instanceof String)
		{
	        StringBuilder result = new StringBuilder();
	        StringCharacterIterator i = new StringCharacterIterator((String) data);
	        char c =  i.current();
	        while (c != CharacterIterator.DONE ){
	            switch (c) {
	                case '<':
	                    result.append("&lt;");
	                    break;
	                case '>':
	                    result.append("&gt;");
	                    break;
	                case '"':
	                    result.append("&quot;");
	                    break;
	                case '\'':
	                    result.append("&apos;");
	                    break;
	                case '&':
	                    result.append("&amp;");
	                    break;
	                default:
	                    result.append(c);
	            }
	            c = i.next();
	        }
	        return result.toString();
		}
		else
		{
			return data;
		}
    }
	
	 public static String _utf8_decode(String utftext) {
		String string = "";
		int i = 0;
		int c;
		int c2;
		int c3;
		
		while ( utftext !=null && i < utftext.length() ) {

			c = utftext.codePointAt(i);

			if (c < 128) {
				string += String.valueOf(Character.toChars(c));
				i++;
			}
			else if((c > 191) && (c < 224) && i + 1 < utftext.length()) {
				c2 = utftext.codePointAt(i+1);
				string += String.valueOf(Character.toChars(((c & 31) << 6) | (c2 & 63)));
				i += 2;
			}
			else if (i + 2 < utftext.length()){
				c2 = utftext.codePointAt(i+1);
				c3 = utftext.codePointAt(i+2);
				string += String.valueOf(Character.toChars(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63)));
				i += 3;
			}
			else
			{
				string += String.valueOf(Character.toChars(c));
				i++;
			}
		}

		return utftext==null?null:string;
	}
}
