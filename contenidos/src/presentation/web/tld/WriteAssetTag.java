package presentation.web.tld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

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
import common.util.StringUtils;
import common.util.spring.SpringUtil;

public class WriteAssetTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361273797110187775L;
	private static Log logger = LogFactory.getLog(WriteAssetTag.class);
	private Long asset;

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		HttpSession session = this.pageContext.getSession();	
		UserInfo user = (UserInfo) session.getAttribute("user");

		AssetsBO assetsBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO assetsDTO;
		try {
			assetsDTO = assetsBO.getByPrimaryKey(this.asset);
		
			FicherosDTO ficherosDTO = null;
			String path = null;
			if (assetsDTO.getUsuFk().equals(user.getPk()))
			{	    	
				FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
				ficherosDTO = ficherosBO.getByAsset(assetsDTO.getAssPk());
	
				path = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + user.getUser() + "/";
			}
			else
			{
				CompartidosBO compartidosBO = (CompartidosBO)SpringUtil.getInstance().getBean("CompartidosBO");
				CompartidosDTO compartidosDTO = compartidosBO.getByUniqueKey(assetsDTO.getAssPk(), user.getPk());
				
				if (compartidosDTO !=null)
				{
					FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
					ficherosDTO = ficherosBO.getByAsset(assetsDTO.getAssPk());		
	
					UsuariosBO usuariosBO = (UsuariosBO)SpringUtil.getInstance().getBean("UsuariosBO");
					UsuariosDTO usuariosDTO = usuariosBO.getByPrimaryKey(assetsDTO.getUsuFk());
	
					path = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + usuariosDTO.getUsuUkUsuario() + "/";
				}
			}
	
			if (new File(path + ficherosDTO.getFicSysName()).exists())
			{
	            //Construct the BufferedReader object
				FileReader fr = new FileReader(path + ficherosDTO.getFicSysName());
		        BufferedReader bufferedReader = new BufferedReader(fr);
	            
	            String line = null;	            
	            while ((line = bufferedReader.readLine()) != null) {
	                //Process the data, here we just print it out
		        	this.pageContext.getOut().print(StringUtils.escapeXML(line) + "<br/>");
		        	this.pageContext.getOut().flush();
	            }
	            bufferedReader.close();
	            fr.close();
			}
			
			// Escribimos codigo html en la pagina
			//this.pageContext.getOut().print(html.toString());
			if ( logger.isDebugEnabled() )
				logger.debug("Done!");
			
			//JspWriter salida = this.pageContext.getOut();
			//salida.println(getMenu());
		} catch (BusinessException e) {
			logger.error(e);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}
		
		// Acabamos tag y continuamos pagina
		return EVAL_PAGE;
	}

	public void setAsset(Long asset) {
		this.asset = asset;
	}

	public Long getAsset() {
		return asset;
	}
}
