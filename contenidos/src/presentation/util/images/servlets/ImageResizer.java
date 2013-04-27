package presentation.util.images.servlets;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
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
public class ImageResizer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1746452800580019467L;
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ImageResizer.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageResizer() {
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
		// Optional: Width image should be resized to
		int width = Integer.parseInt(getParam(request, "width", "0"));
		// Optional: If specified used, otherwise proportions are calculated
		int height = Integer.parseInt(getParam(request, "height", "0"));
		
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
			if (width > 0 || height > 0){
				// Calculate the new Height/Width if not specified
				Dimension dimension = new Dimension(width, height).Ajusta(bufferedImage);
				if (dimension == null)
				{
					response.getOutputStream().print("");
				}
				else
					ImageIO.write(createResizedCopy(bufferedImage, dimension.getWidth(), dimension.getHeight()), imageOutput, stream);
			}
			else
			{
				ImageIO.write(bufferedImage, imageOutput, stream);
			}
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
	
	private BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight) {
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = scaledBI.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
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
	
	protected class Dimension
	{
		private int width;
		private int height;
		
		Dimension(int width, int height)
		{
			this.width = width; 
			this.height = height; 
		}
		
		public void setWidth(int width) {
			this.width = width;
		}
		public int getWidth() {
			return width;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getHeight() {
			return height;
		}
		
		public Dimension Ajusta(BufferedImage image)
		{
			if (image == null)
			{
				return null;
			}
			else
			{
				int imageWidth = image.getWidth();
				int imageHeight = image.getHeight();
				if (this.width > 0 && this.width < imageWidth)
				{
					int calcWidth = this.width;
					int calcHeight = calcWidth * imageHeight / imageWidth;
					if (this.height > 0 && this.height < calcHeight)
					{
						calcHeight = this.height;
						calcWidth = calcHeight * imageWidth / imageHeight;
					}
					return new Dimension(calcWidth, calcHeight);
				}
				else if (this.height > 0 && this.height < imageHeight)
				{
					int calcHeight = this.height;
					int calcWidth = calcHeight * imageWidth / imageHeight;
					if (this.width > 0 && this.width < calcWidth)
					{
						calcWidth = this.width;
						calcHeight = calcWidth * imageHeight / imageWidth;
					}
					return new Dimension(calcWidth, calcHeight);				
				}
				return new Dimension(imageWidth, imageHeight);				
			}
		}
	}
}
