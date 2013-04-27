package presentation.util.images.servlets;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import presentation.util.Parametros;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.CompartidosBO;
import common.business.bo.FicherosBO;
import common.business.bo.UsuariosBO;
import common.dto.AssetsDTO;
import common.dto.CompartidosDTO;
import common.dto.FicherosDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.util.spring.SpringUtil;

/**
 * Servlet resizes an image located in a directory in a web project ex.
 * /image?file=/thumbs/imagename.jpg&width=270&height=100 ex.
 * /image?file=/thumbs/imagename.jpg&width=270 (calculated height)
 */

/**
 * Servlet implementation class ImageResizer
 */
public class RotateImage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3907585757200983446L;
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(RotateImage.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RotateImage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletOutputStream stream = null;
		
		HttpSession session = request.getSession();
    	UserInfo suser = (UserInfo) session.getAttribute("user");

    	// Optional: Only supports output of jpg and png, defaults to png if not
		// specified
		String imageOutput = getParam(request, "output", "jpg");
		// Required: asset from image
		Long asset = Long.parseLong(getParam(request, "asset", null));
		// Optional: Angle image should be totated to
		int angle = Integer.parseInt(getParam(request, "angle", "0"));
		
		String imageLoc = null;
		String imageRoot = null;
		String imageFile = null;
		try {
			AssetsBO assetBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
			AssetsDTO assetDTO = assetBO.getByPrimaryKey(asset);
			Long user = assetDTO.getUsuFk();

			// Server Location of the image
			if (suser.getPk().equals(user))
			{
				imageFile = getFileName(asset);
				imageRoot = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + suser.getUser();
			}
			else if (isShared(suser.getPk(), asset))
			{
				UsuariosBO userBO = (UsuariosBO)SpringUtil.getInstance().getBean("UsuariosBO");
				UsuariosDTO userDTO = userBO .getByPrimaryKey(user);
				imageFile = getFileName(asset);
				imageRoot = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + userDTO.getUsuUkUsuario();
			}
			else
			{
				imageRoot = request.getSession().getServletContext().getRealPath("/");			
				imageFile = IConstantes.UNKNOWN_IMAGE;				
			}
			
			// Set the mime type of the image
			if ("png".equals(imageOutput))
				response.setContentType("image/png");
			else
				response.setContentType("image/jpeg");
	    	
			imageLoc = imageRoot + "/" + imageFile;
	
			// Read the original image from the Server Location
			File image = new File(imageLoc);
			if (!image.exists())
			{
				imageRoot = request.getSession().getServletContext().getRealPath(IConstantes.UNKNOWN_IMAGE);
				image = new File(imageRoot);
			}

			BufferedImage bufferedImage = ImageIO.read(image);
			stream = response.getOutputStream();
			ImageIO.write(createRotatedCopy(bufferedImage, angle), imageOutput, stream);
		} catch (BusinessException e1) {
			logger.error("Problem with file: " + imageLoc);
			logger.error(e1);
			response.sendError(-400, e1.getMessage());
			throw new ServletException(e1.getMessage());
		} catch (Exception e) {
			logger.error("Problem with file: " + imageLoc);
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
	
	private BufferedImage createRotatedCopy(BufferedImage image, int angle) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage toStore = new BufferedImage(h, w, image.getType());
		Graphics2D g2 = toStore.createGraphics();
		double x = (h - w)/2.0;
		double y = (w - h)/2.0;
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
		g2.drawRenderedImage(image, at);
		g2.dispose();

		/*
		Graphics2D g = image.createGraphics();

		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle), image.getWidth()/2, image.getHeight()/2);
		
		g.drawRenderedImage(image, at);
		g.dispose();
		*/
        
		return toStore;
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
	
	private Boolean isShared(Long user, Long asset) throws BusinessException
	{
		CompartidosBO compartidosBO = (CompartidosBO)SpringUtil.getInstance().getBean("CompartidosBO");
		CompartidosDTO compartidosDTO = compartidosBO.getByUniqueKey(asset, user);
		return compartidosDTO==null?false:true;
	}
	
	private String getFileName(Long asset) throws BusinessException
	{
		FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
		FicherosDTO ficherosDTO = ficherosBO.getByAsset(asset);
		
		if (ficherosDTO != null )
			return ficherosDTO.getFicSysName();
		else
			return null;
	}
}
