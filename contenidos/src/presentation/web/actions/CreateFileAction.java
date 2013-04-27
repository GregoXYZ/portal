package presentation.web.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.ui.upload.UploadInfo;
import presentation.util.Parametros;
import presentation.web.forms.FileForm;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.CuotasBO;
import common.business.bo.FicherosBO;
import common.dto.AssetsDTO;
import common.dto.CuotasDTO;
import common.dto.FicherosDTO;
import common.presentation.WebException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class CreateFileAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CreateFileAction.class);

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward;
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		UploadInfo uploadInfo = (UploadInfo) session.getAttribute("uploadInfo");
		FileForm lform = (FileForm) form;
		
		if (uploadInfo!=null && uploadInfo.getStatus().equals("done"))
		{
			CuotasBO cuotaBO = (CuotasBO) SpringUtil.getInstance().getBean("CuotasBO");
	    	CuotasDTO cuota = cuotaBO.getCuotaRestante(user.getPk());
	    	
			if ( cuota != null && cuota.getCuoCuotaDisk() > lform.getFile().getFileSize() && cuota.getCuoCuotaFile() > lform.getFile().getFileSize() )
			{
				if (lform.isDescomprimir())
					addZipFile(request, user, lform);
				else
					addFile(request, user, lform);
			    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
			    forward =  redirect;
			}
			else
			{
				StringBuffer info = new StringBuffer();
		    	
				Long wCuota = cuota==null?0:cuota.getCuoCuotaDisk();
	        	info.append("No tienes cuota de disco (" + wCuota + ") suficiente para subir este archivo (" + lform.getFile().getFileSize() + ")");
	        	info.append("o");
	        	wCuota = cuota==null?0:cuota.getCuoCuotaFile();
	        	info.append("No tienes cuota de archivo (" + wCuota + ") suficiente para subir este archivo (" + lform.getFile().getFileSize() + ").");
				logger.warn(user.getUser() + " ha intentado superar la cuota asignada " + wCuota + "/" + lform.getFile().getFileSize());
				throw new WebException(info.toString(), request);
			    //ActionRedirect redirect =  new ActionRedirect(mapping.findForward("home"));
			    //forward =  redirect;
			}			
		}
		else{
	    	if (uploadInfo!=null)
	    	{
				logger.warn(uploadInfo.getStatus());
				throw new WebException(uploadInfo.getStatus(), request);
	    	}
			throw new WebException("Error no controlado al subir el fichero.", request);
		}
		
		if (lform.getFile() != null ) lform.getFile().destroy();
		lform.limpia();
		session.removeAttribute("uploadInfo");
		return forward;
	}
	
	private boolean addFile(HttpServletRequest request, UserInfo user, FileForm lform) throws BusinessException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = sdf.format(new Date());
		
    	Long carpeta = null;
    	if (lform!=null && lform.getCarPk()!=null && lform.getCarPk() >0 ) carpeta = lform.getCarPk();

		AssetsDTO assDTO = new AssetsDTO();
		assDTO.setAssNombre(lform.getFile().getFileName());
		assDTO.setAssDescripcion(lform.getDescripcion());
		assDTO.setTipAssFk(IConstantes.TIPO_ASSET_FICHERO);
		assDTO.setUsuFk(user.getPk());

		FicherosDTO ficDTO = new FicherosDTO();
		ficDTO.setCarFk(carpeta);
		ficDTO.setFicSize(Long.valueOf(lform.getFile().getFileSize()));
		ficDTO.setFicSysName(fileName);
		ficDTO.setMimFilFk(lform.getMimFilFk());
		
		FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
		String userPath = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + user.getUser() + "/";
		ficherosBO.add(userPath, lform.getFile(), assDTO, ficDTO, lform.getUsers(), lform.getTags());
		logger.info("Subido el archivo: " + assDTO.getAssNombre() + "(" + ficDTO.getFicSysName() +")");
		return true;
	}
	
	private boolean addZipFile(HttpServletRequest request, UserInfo user, FileForm lform) throws BusinessException
	{
    	Long carpeta = null;
    	if (lform!=null && lform.getCarPk()!=null && lform.getCarPk() >0 ) carpeta = lform.getCarPk();

		AssetsDTO assDTO = new AssetsDTO();
		assDTO.setAssDescripcion(lform.getDescripcion());
		assDTO.setTipAssFk(IConstantes.TIPO_ASSET_FICHERO);
		assDTO.setUsuFk(user.getPk());

		FicherosDTO ficDTO = new FicherosDTO();
		ficDTO.setCarFk(carpeta);
		ficDTO.setMimFilFk(null);

		FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
		String userPath = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + user.getUser() + "/";
		ficherosBO.addZipFile(userPath, lform.getFile(), assDTO, ficDTO, lform.getUsers(), lform.getTags());
		logger.info("Subido el archivo: " + lform.getFile().getFileName() + "(Archivo ZIP descomprimido correctamente)");
		return true;
	}
}
