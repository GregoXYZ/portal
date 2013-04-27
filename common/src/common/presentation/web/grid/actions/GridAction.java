package common.presentation.web.grid.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.DynaActionForm;

import common.presentation.util.grid.handler.Handler;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;

public class GridAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaActionForm df= (DynaActionForm)form;
		String gridId = df.getString("gridId");
		String eventId = df.getString("eventId");
		String eventValue = df.getString("eventValue");

		Handler gh = (Handler)request.getSession().getAttribute( gridId );
		gh.processEvent(eventId, eventValue, request);

		return mapping.findForward("success");
	}

}
