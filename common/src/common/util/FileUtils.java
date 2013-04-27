package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

import common.Constants;

public class FileUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(FileUtils.class);

	// Instancia del Singleton
	private static FileUtils _instance = null;

	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new FileUtils();
	}

	/**
	 * Acceso al Singleton
	 * 
	 * @return ConfigProperties
	 */
	public static FileUtils getInstance() {
		/*
		 * if (_instance == null) { _instance = new ConfigProperties(); }
		 */
		return _instance;
	}

	/**
	 * Devuelve la ruta padre del subdirectorio actual
	 * 
	 * @param File
	 *            --> El archivo del cual se quiere sacar su directorio o
	 *            directorio padre
	 * @return File --> Crea un archivo con la ruta del directorio padre
	 */
	public File parent(File f) {
		String dirname = f.getParent();
		if (dirname == null) {
			return new File(File.separator);
		}
		return new File(dirname);
	}

	/**
	 * Crear un subdirectorio si este no existe
	 * 
	 * @param dir
	 *            --> El path del archivo (direcci�n + nombre)
	 * @return True -> Existe o se ha creado False --> No existe y no se ha
	 *         podido crear
	 */
	public boolean makeSureDirectoryExists(File dir) {
		if (!dir.exists()) {
			if (makeSureDirectoryExists(parent(dir)))
				dir.mkdir();
			else
				return false;
		}
		return true;
	}

	public static void carga(String path, byte[] file, Long cuota)
			throws IOException, FileUploadException {
		// Controlamos las condiciones para subirlo
		// se comprueba que la ruta exista
		File f = new File(path);
		if (f.length() > cuota)
			throw new FileUploadException(
					"El tamaño del fichero excede la cuota asignada.");

		carga(path, file);
	}

	public static void carga(String path, byte[] file) throws IOException {
		// Controlamos las condiciones para subirlo
		// se comprueba que la ruta exista
		File f = new File(path);
		if (FileUtils.getInstance().makeSureDirectoryExists(FileUtils.getInstance().parent(f))) {
			// Se graba en la ruta la foto;
			FileOutputStream out = new FileOutputStream(f);
			out.write(file);
			out.flush();
			out.close();
		}
	}

	public static byte[] descarga(String path, String name) throws IOException {
		FileInputStream file = new FileInputStream(path + name);
		// BufferedReader file = new BufferedReader(new FileReader(path +
		// name));
		int longitud = file.available();
		byte[] datos = new byte[longitud];
		file.read(datos);
		file.close();

		return datos;
	}

	public static Boolean delete(String file) {
		File f = new File(file);
		return f.delete();
	}

	public static void deleteFiles(String directory, String extension) {
		ExtensionFilter filter = new ExtensionFilter(extension);
		File dir = new File(directory);

		String[] list = dir.list(filter);
		File file;
		if (list.length == 0)
			return;

		for (int i = 0; i < list.length; i++) {
			// file = new File(directory + list[i]);
			file = new File(directory, list[i]);
			System.out.print(file + "  deleted : " + file.delete());
		}
	}

	public static Boolean fileExists(String file) {
		File f = new File(file);

		return f.exists();
	}

	public static void readFromDisk(String path, String name, HttpServletResponse response) throws IOException {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(path + name);
			int bytesRead = 0;
			byte[] buffer = null;

			response.setContentLength(stream.available());

			buffer = new byte[Constants.READ_BUFFER_SIZE];
			while ((bytesRead = stream.read(buffer, 0,
					Constants.READ_BUFFER_SIZE)) != -1) {
				response.getOutputStream().write(buffer, 0, bytesRead);
				// response.getOutputStream().flush();
			}
			// response.getOutputStream().flush();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				if (stream != null)
					stream.close();
				// response.getOutputStream().close();
			} catch (IOException e) {
				throw new IOException(e);
			}
		}
	}

	public static String writeToDisk(FormFile file, String fileName, String destinationPath) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		logger.info("[writeToDisk] - Entering Fuction");
		InputStream stream = null;
		OutputStream bos = null;
		int bytesRead = 0;
		int fileSize = 0;
		byte[] buffer = null;
		String result = "";
		MessageDigest complete = MessageDigest.getInstance(Constants.ALGORITHM_HASH_FILE);

		File f = new File(destinationPath + fileName);
		if (FileUtils.getInstance().makeSureDirectoryExists(FileUtils.getInstance().parent(f))) {
			logger.info("[writeToDisk] - Get InputFileStream and FileOutputStream");
			stream = file.getInputStream();
			bos = new FileOutputStream(destinationPath + fileName);

			fileSize = file.getFileSize();
			logger.info("[writeToDisk] - Got File Size of " + fileSize + " bytes");

			bytesRead = 0;

			buffer = new byte[Constants.WRITE_BUFFER_SIZE];

			while ((bytesRead = stream.read(buffer, 0, Constants.WRITE_BUFFER_SIZE)) != -1) {
				complete.update(buffer, 0, bytesRead);
				bos.write(buffer, 0, bytesRead);
			}
			logger.info("[writeToDisk] - Finished writing file(" + fileSize + ") to disk (" + destinationPath + ").");

			bos.close();
			stream.close();

			buffer = complete.digest();
			for (int i = 0; i < buffer.length; i++) {
				result += Integer.toString((buffer[i] & 0xff) + 0x100, 16).substring(1);
			}
		}
		
		return result;

	}

	public static final String copyInputStream(InputStream in, OutputStream out) throws IOException, NoSuchAlgorithmException {
		MessageDigest complete = MessageDigest.getInstance(Constants.ALGORITHM_HASH_FILE);
		byte[] buffer = new byte[Constants.WRITE_BUFFER_SIZE];
		int len;
		while ((len = in.read(buffer)) >= 0) {
			complete.update(buffer, 0, len);
			out.write(buffer, 0, len);
			out.flush();
		}
		in.close();
		out.close();

		String result = "";
		buffer = complete.digest();
		for (int i = 0; i < buffer.length; i++) {
			result += Integer.toString((buffer[i] & 0xff) + 0x100, 16).substring(1);
		}

		return result;
	}

	// see this How-to for a faster way to convert
	// a byte array to a HEX string
	public static String getChecksum(String filename) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return getChecksum(filename, Constants.ALGORITHM_HASH_FILE);
	}
	
	public static String getChecksum(String filename, String algorithm) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		byte[] b = createChecksum(new FileInputStream(filename), algorithm);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static String getChecksum(InputStream stream) throws NoSuchAlgorithmException, IOException {
		return getChecksum(stream, Constants.ALGORITHM_HASH_FILE);
	}
	
	public static String getChecksum(InputStream stream, String algorithm) throws NoSuchAlgorithmException, IOException {
		byte[] b = createChecksum(stream, algorithm);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static String getChecksum(File file) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return getChecksum(file, Constants.ALGORITHM_HASH_FILE);
	}
	
	public static String getChecksum(File file, String algorithm) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		byte[] b = createChecksum(new FileInputStream(file), algorithm);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	private static byte[] createChecksum(InputStream fis, String algorithm) throws NoSuchAlgorithmException, IOException {
		byte[] buffer = new byte[Constants.WRITE_BUFFER_SIZE];
		MessageDigest complete = MessageDigest.getInstance(algorithm);
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}
}
