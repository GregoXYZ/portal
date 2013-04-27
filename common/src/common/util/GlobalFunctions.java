package common.util;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.util.spring.SpringUtil;

public class GlobalFunctions {
	public static String pathAbatar(Long user, HttpSession session) throws BusinessException, IOException
	{
		UsuariosBO userBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
		UsuariosDTO userDTO = userBO.getByPrimaryKey(user);
		
		String contextPath = session.getServletContext().getContextPath();
		String absolutePath = session.getServletContext().getRealPath("/");
		String path = absolutePath.replaceAll(contextPath, "/portal" +
			Constants.TEMP_USER_DIR +
			Constants.DEFAUL_AVATAR_DIR +
			userDTO.getUsuUkUsuario());

		if ( FileUtils.fileExists(path) )
		{
			path = "/portal" + 
				Constants.TEMP_USER_DIR + 
				Constants.DEFAUL_AVATAR_DIR + 
				userDTO.getUsuUkUsuario();
		}
		else
		{
    		if ( userDTO.getUsuAvatar() != null && userDTO.getUsuAvatar().length > 0 )
    		{
    			FileUtils.carga(path, userDTO.getUsuAvatar());
    			
    			path = "/portal" + 
				Constants.TEMP_USER_DIR + 
				Constants.DEFAUL_AVATAR_DIR + 
				userDTO.getUsuUkUsuario();
    		}
    		else
    		{
    			path = "/portal/images/" + 
    			Constants.DEFAUL_AVATAR;    			    							
    		}	
		}
		
		return path;
	}
}
