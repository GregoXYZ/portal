package common.presentation.web.tld;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

public class LabelTag extends BaseHandlerTag {
    
	private static final long serialVersionUID = -4639837431160398504L;

	/**
     * The body content of this tag (if any).
     */
    protected String text = null;

    /**
     * The id of the that are associated
     */
    protected String forField = null;
    
    /**
     * The key that is displayed
     */
    protected String key = null;

	public String getForField() {
		return forField;
	}

	public void setForField(String forField) {
		this.forField = forField;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    
    /**
     * Render the beginning of the label.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	//forField and key are required

        // Generate the opening element
        StringBuffer results = new StringBuffer("<label");

        prepareAttribute(results, "for", forField);

        prepareAttribute(results, "accesskey", getAccesskey());
        results.append(prepareStyles());
        results.append(prepareEventHandlers());
        prepareOtherAttributes(results);
        results.append(">");
        
        //si key informada, escribimos valor
        if(key!=null && !key.equals(""))
        	results.append(message(null,key));

        TagUtils.getInstance().write(pageContext, results.toString());

        // Evaluate the body of this tag
        return (EVAL_BODY_BUFFERED);

    }

    /**
     * Save the associated label from the body content.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            String value = bodyContent.getString().trim();
            if (value.length() > 0)
                text = value;
        }
        return (SKIP_BODY);

    }


    /**
     * Render the end of the hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Prepare the textual content and ending element of this hyperlink
        StringBuffer results = new StringBuffer();
        if (text != null) {
            results.append(text);
        }
        results.append("</label>");

        TagUtils.getInstance().write(pageContext, results.toString());

        return (EVAL_PAGE);

    }
}
