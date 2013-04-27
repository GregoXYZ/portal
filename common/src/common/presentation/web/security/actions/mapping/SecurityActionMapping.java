package common.presentation.web.security.actions.mapping;

import org.apache.struts.action.ActionMapping;

public class SecurityActionMapping extends ActionMapping    
{   
    /**
	 * 
	 */
	private static final long serialVersionUID = -4018117071621020217L;
	private String zone = null;
	private String url = null;

    public String getApplicationZone()    
    {
		return zone;   
    }   

    public void setApplicationZone(String zone)    
    {   
        this.zone = zone;   
    }

	public void setApplicationUrl(String url) {
		this.url = url;
	}

	public String getApplicationUrl() {
		return url;
	}   
}
