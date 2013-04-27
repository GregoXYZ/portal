package presentation.util.multimedia.servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import presentation.util.Parametros;
import presentation.web.actions.ImagesMiniaturesAction;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.CompartidosBO;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.business.bo.UsuariosBO;
import common.dto.AssetsDTO;
import common.dto.CompartidosDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.util.spring.SpringUtil;

public class Multimedia extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 8543108327141634215L;

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ImagesMiniaturesAction.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Multimedia() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfo suser = (UserInfo) session.getAttribute("user");
	
		// Required: asset
		Long asset = Long.parseLong(getParam(request, "asset", null));
		
		String fileLoc = null;
		String fileRoot = null;
		String fileName = null;
		FicherosDTO fileDTO = null;
		String contentType = "audio/mpeg";
		
		
		ServletOutputStream stream = response.getOutputStream();
		BufferedInputStream buf = null;
		try {
			AssetsBO assetBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
			AssetsDTO assetDTO = assetBO.getByPrimaryKey(asset);
			Long user = assetDTO.getUsuFk();
		
			// Server Location of the image
			if (suser.getPk().equals(user)) {
				fileName = (fileDTO = getFile(asset)).getFicSysName();
				contentType = getMimeType(fileDTO.getMimFilFk()).getMimFilMime();
				fileRoot = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + suser.getUser();
			} else if (isShared(suser.getPk(), asset))	{
				UsuariosBO userBO = (UsuariosBO)SpringUtil.getInstance().getBean("UsuariosBO");
				UsuariosDTO userDTO = userBO .getByPrimaryKey(user);
				fileName = (fileDTO = getFile(asset)).getFicSysName();				
				contentType = getMimeType(fileDTO.getMimFilFk()).getMimFilMime();
				fileRoot = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) +  userDTO.getUsuUkUsuario();
			}
			else
			{
				fileRoot = request.getSession().getServletContext().getRealPath("/");			
				fileName = IConstantes.UNKNOWN_MULTIMEDIA;				
			}
		
			fileLoc = fileRoot + "/" + fileName;	
			File file = new File(fileLoc);
			if (!file.exists())
			{
				fileRoot = request.getSession().getServletContext().getRealPath(IConstantes.UNKNOWN_MULTIMEDIA);
				file = null;
				file = new File(fileRoot);
			}
			
			//set response headers
			response.reset();
			response.setHeader("Expires", "-1");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType(contentType);
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setContentLength((int) file.length());
			
			//read from the file; write to the ServletOutputStream
			buf = new BufferedInputStream(new FileInputStream(file));						
			byte[] bytes = new byte[buf.available()];
			while (buf.read(bytes) != -1) {
				stream.write(bytes);
			}
		} catch (BusinessException e) {
			logger.error("Problem with file: " + fileLoc);
			logger.error(e);
			response.sendError(488, e.getMessage());
			throw new ServletException(e.getMessage());
		} catch (SocketException e) {
			logger.error("Problem with file: " + fileLoc);
			logger.error(e);
			response.sendError(488, e.getMessage());
			throw new ServletException(e.getMessage());
		}
		catch (Exception e) {
			logger.error("Problem with file: " + fileLoc);
			logger.error(e);
			response.sendError(488, e.getMessage());
			throw new ServletException(e.getMessage());
		} finally {
			try {
				if (stream != null){
					stream.flush();
					stream.close();
				}				
			}
			finally {
				if (buf != null) {
					buf.close();				
				}				
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
		doGet(request, response);
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
	
	private FicherosDTO getFile(Long asset) throws BusinessException
	{
		FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
		FicherosDTO ficherosDTO = ficherosBO.getByAsset(asset);
		
		if (ficherosDTO != null )
			return ficherosDTO;
		else
			return null;
	}
	
	private MimeFilesDTO getMimeType(Long pk) throws BusinessException
	{
		MimeFilesBO mimeBO = (MimeFilesBO)SpringUtil.getInstance().getBean("MimeFilesBO");
		MimeFilesDTO mimeDTO = mimeBO.getByPrimaryKey(pk);
		
		if (mimeDTO != null )
			return mimeDTO;
		else
			return null;
	}

}
