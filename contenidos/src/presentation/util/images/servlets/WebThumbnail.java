package presentation.util.images.servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet resizes an image located in a directory in a web project ex.
 * /image?file=/thumbs/imagename.jpg&width=270&height=100 ex.
 * /image?file=/thumbs/imagename.jpg&width=270 (calculated height)
 */

/**
 * Servlet implementation class WebThumbnail
 */
public class WebThumbnail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1817521683656623471L;
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(WebThumbnail.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebThumbnail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletOutputStream stream = null;

		// Required: asset from image
		String url = getParam(request, "url", null);
		
		try {			
			stream = response.getOutputStream();
			ImageIO.write(createThumbnail(url), "jpg", stream);
		} catch (IOException e) {
			logger.error("Problem with url: " + url);
			logger.error(e);
			response.sendError(-400, e.getMessage());
			throw new ServletException(e.getMessage());
		}
		finally
		{
			if (stream != null)
				stream.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private BufferedImage createThumbnail(String url) throws IOException {
		JFrame someWindow = new JFrame();
		JEditorPane htmlPane = new JEditorPane(url);

		someWindow.setBounds(30, 30, 750, 750);
		htmlPane.setEditable(false);
		someWindow.add(new JScrollPane(htmlPane));
		someWindow.setVisible(true);

		BufferedImage pageImage = new BufferedImage ( 500, 500,	BufferedImage.TYPE_INT_RGB );
		Graphics2D pageGraphics = pageImage.createGraphics();
		htmlPane.paint ( pageGraphics );
		//ImageIO.write(pageImage, "jpg", new FileOutputStream("C:\\Documents and Settings\\d\\Desktop\\test.jpg"));
		
		return pageImage;
	}

	
	// Check the param if it's not present return the default
	private String getParam(HttpServletRequest request, String param, String def) {
		String parameter = request.getParameter(param);
		if (parameter == null || "".equals(parameter)) {
			return def;
		} else {
			return parameter;
		}
	}
}
