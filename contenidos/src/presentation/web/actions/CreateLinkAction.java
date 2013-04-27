package presentation.web.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.LinkForm;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.LinksBO;
import common.dto.AssetsDTO;
import common.dto.LinksDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class CreateLinkAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		LinkForm lform = (LinkForm) form;

		AssetsBO assetsBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO assDTO = assetsBO.getByPrimaryKey(lform.getAssPk(), user.getPk());

		if (assDTO != null)
		{			
			if (lform.getNombre().trim().length() > 0
				&& lform.getDescripcion().trim().length() > 0 
				&& URLCheck(lform.getNombre())) {
				assDTO = new AssetsDTO();
				assDTO.setAssNombre(lform.getNombre());
				assDTO.setAssDescripcion(lform.getDescripcion());
				assDTO.setUsuFk(user.getPk());
				assDTO.setTipAssFk(IConstantes.TIPO_ASSET_URL);
	
				LinksDTO linkDTO = new LinksDTO();
				linkDTO.setAssFkRef(lform.getAssPk());
	
				LinksBO linksBO = (LinksBO) SpringUtil.getInstance().getBean("LinksBO");
				linksBO.add(linkDTO, assDTO);
				lform.limpia();
			}

		}
		return mapping.findForward("success");
	}

	private Boolean URLCheck(String urlString) throws BusinessException {
		URL url;
		try {
			url = new URL(urlString);
			URLConnection connection;
			connection = url.openConnection();
			if (connection instanceof HttpURLConnection) {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.connect();
				int response = httpConnection.getResponseCode();
				System.out.println("Response: " + response);
				InputStream is = httpConnection.getInputStream();
				byte[] buffer = new byte[256];
				while (is.read(buffer) != -1) {
				}
				is.close();
				return true;
			}
			else
				return false;
		} catch (MalformedURLException e) {
			throw new BusinessException(e);
		} catch (IOException e) {
			throw new BusinessException(e);
		}
	}
}
