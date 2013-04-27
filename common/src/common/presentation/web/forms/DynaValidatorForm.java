package common.presentation.web.forms;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.Resources;

public class DynaValidatorForm extends
		org.apache.struts.validator.DynaValidatorForm {
	
	private static final long serialVersionUID = -7949899843944311127L;
	private static Log log = LogFactory.getLog(DynaValidatorForm.class);

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors ar=super.validate(mapping, request);
		return ar;
	}
	
	public ActionErrors validate(String key,ActionMapping mapping,
			HttpServletRequest request) {
		
		    ServletContext application = getServlet().getServletContext();
	        ActionErrors errors = new ActionErrors();

	        String validationKey = key;

	        Validator validator = Resources.initValidator(validationKey,
	                             this,
	                             application, request,
	                             errors, page);

	        try {
	            validatorResults = validator.validate();
	        } catch (ValidatorException e) {
	            log.error(e.getMessage(), e);
	        }

	        return errors;
	}
}
