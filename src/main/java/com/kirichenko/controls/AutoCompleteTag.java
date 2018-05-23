package com.kirichenko.controls;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @model
 * @author Brian J. Stewart (Aqua Data Technologies, Inc. http://www.aquadatatech.com)
 *
 */
public class AutoCompleteTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5946042625770978958L;

	private String m_controlId = null;
	private String m_cssClass = null;
	private String m_dataUrl = "/AutoCompleteData";
	private String m_controlValue = null;
	private int m_minChars = 3;
	private int m_maxCount = 10;
	private int m_width = 40;
	private String m_providerClass = "";
	
	/**
	 * The getProviderclass method returns the class name for the data provider
	 * @return Provider class for control
	 */
	public String getProviderclass(){
		return this.m_providerClass;
	}
	
	/**
	 * The setProviderclass method sets the class name for the data provider
	 * @param value Provider class for control
	 */
	public void setProviderclass(String value){
		this.m_providerClass = value;
	}
	
	/**
	 * The getWidth method returns the control width.
	 * @return Control width
	 */
	public int getWidth(){
		return this.m_width;
	}
	
	/**
	 * The setWidth method sets the control width. The default is 40.
	 * @param value Control width
	 */
	public void setWidth(int value){
		this.m_width = value;
	}
	
	/**
	 * The getMaxcount method returns the maximum number of entries for the 
	 * auto-complete list.
	 * @return Maximum number of entries for auto-complete list
	 */
	public int getMaxcount(){
		return this.m_maxCount;
	}
	
	/**
	 * The setMaxcount method sets the maximum number of entries for the 
	 * auto-complete list. The default is 10 entries.
	 * @param value Maximum number of entries for auto-complete list
	 */
	public void setMaxcount(int value){
		this.m_maxCount = value;
	}
	
	/**
	 * The getMinimumlength method returns the minimum number of characters
	 * required before submitting asynchronous request to server for auto-complete list.
	 * The default is 3 characters.
	 * @return Minimum number of characters
	 */
	public int getMinimumlength(){
		return this.m_minChars;
	}
	
	/**
	 * The setMinimumlength method sets the minimum number of characters
	 * required before submitting asynchronous request to server for auto-complete list
	 * @param value Minimum number of characters
	 */
	public void setMinimumlength(int value){
		this.m_minChars = value;
	}
		
	/**
	 * The setValue method sets the value of the current control
	 * @param newValue New value for control
	 */
	public void setValue(String newValue) {
		this.m_controlValue = newValue;
	}
	
	/**
	 * The getValue returns the value of the current control
	 * @return Value of current control
	 */
	public String getValue() {
		return this.m_controlValue;
	}
	
	/**
	 * The setId method sets the Id of the current control.
	 * @param value Id of current control
	 */
	public void setId(String value) {
		this.m_controlId = value;
	}

	/**
	 * The getId method returns the Id of the current control.
	 * @return Id of current control
	 */
	public String getId() {
		return this.m_controlId;
	}
	
	/**
	 * The setCssclass method sets the CSS class name for the current control
	 * @param value CSS class name
	 */
	public void setCssclass(String value) {
		this.m_cssClass = value;
	}
	
	/**
	 * The getCssclass method returns the CSS class name for the current control
	 * @return CSS class name
	 */
	public String getCssclass() {
		return this.m_cssClass;
	}
	
	/**
	 * The setDataurl method sets the Url to which to connect (asynchronously) to retrieve
	 * data values. The control automatically appends the values in the cascadeFrom 
	 * control attribute.
	 * @param value Url to retrieve data values
	 */
	public void setDataurl(String value) {
		this.m_dataUrl = value;
	}	
	
	/**
	 * The getDataurl method returns the Url to which to connect (asynchronously) to retrieve
	 * data values. The control automatically appends the values in the cascadeFrom 
	 * control attribute.
	 * @return Url to retrieve data values
	 */
	public String getDataurl() {
		return this.m_dataUrl;
	}

	/**
	 * The getInputControl method returns the html code for the input code that performs
	 * the asynchronous callback to retrieve data to automatically pre-populate other form
	 * fields.
	 * @return Html code for the input code
	 */
	protected String getInputControl() {
		StringBuffer html = new StringBuffer();
		
		// Render input text control
		html.append("<input type='text' id='");
		html.append(this.getId());
		html.append("' ");
		
		// Render css class if specified
		if (this.getCssclass() != null && this.getCssclass().length() > 0) {
			html.append("class='");
			html.append(this.getCssclass());
			html.append("' ");
		}
		
		// Render value if specified
		if (this.getValue() != null && this.getValue().length() > 0) {
			html.append(" value='");
			html.append(this.getValue());
			html.append("' ");
		}
		
		// Render width 
		html.append(" size='");
		html.append(this.getWidth());
		html.append("' ");
		
		html.append(" onblur='onSuggestionFocusLost(this)'");

		// Render Key down event handler
		html.append(" onkeydown='onSuggestionKeyDown(this, event)'");			
		
		// Render Key up event handler
		html.append(" onkeyup='onSuggestionKeyUp(this, event, ");
		html.append(this.getMinimumlength());
		html.append(", getDataUrl_");
		html.append(this.getId());
		html.append("())'");								
		
		// Disable browser autocomplete for this control
		html.append(" autocomplete='off' />");
		
		// Render div tag for values
		html.append("<div id='");
		html.append(this.getId());
		html.append("_suggest'");		
		html.append(" class='autoComplete' ");
		
		html.append(">");
		
		html.append("</div>");
		
		return html.toString();
	}
	
	/**
	 * The getDataUrlForAsyncCallBack builds the Url used for async callback to 
	 * server for data retrieval. The data values for the controls specified in the 
	 * cascadefrom attribute are automatically appended to the Url.
	 * @return Url (servlet or jsp page) to retrieve data values
	 */
	protected String getDataUrlFunction() {
		StringBuffer html = new StringBuffer();
		html.append("function getDataUrl_");
		html.append(this.getId());
		html.append("() {");
		html.append("var curControl = document.getElementById('");
		html.append(this.getId());
		html.append("');");
		
		html.append("var dataUrl = '");
		html.append(pageContext.getServletContext().getContextPath());
		html.append(this.getDataurl());
		html.append("?format=json&criteria='");
		html.append(" + curControl.value + '&maxCount=");
		html.append(this.getMaxcount());
		html.append("&providerClass=");
		html.append(this.getProviderclass());
		html.append("';");
		
		html.append("return dataUrl;");
		html.append("}");
		
		return html.toString();
	}
	
	
	/**
	 * The getOnServerResponse method returns the javascript code for the on server response
	 * received event for the drop down
	 * @return Javascript code for the on async callback event for the drop down
	 */
	public String getServerReponseFunction() {
		StringBuffer html = new StringBuffer();

		html.append("function ");
		html.append(this.getId());
		html.append("_onServerResponse() {");
		
			html.append("if(req.readyState!=4) { return; }");
			html.append("if(req.status != 200) {");
				html.append("alert('An error occured retrieving data.');");
				html.append("return;");
			html.append("}");			
			
			// Get response data
			html.append("var responseData = req.responseText;");
							
			html.append("var curControl = document.getElementById('");
			html.append(this.getId());
			html.append("');");
			
			// Convert JSON response into data array
			html.append("var responseData = req.responseText;");
			html.append("var dataValues=eval('(' + responseData + ')');");
					
			html.append("populateSuggestionList(curControl, dataValues);");

		html.append("}");
		
		return html.toString();
			
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		StringBuffer html = new StringBuffer();
				
		html.append(this.getInputControl());
		
		// Render the script tag
		html.append("<script type='text/javascript' language='javascript'>");
		html.append(this.getServerReponseFunction());
		html.append(this.getDataUrlFunction());
		html.append("</script>");

		JspWriter out = pageContext.getOut();
		try {
			out.append(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return AutoCompleteTag.EVAL_BODY_INCLUDE;
	}

	@Override
	public void release() {
		super.release();
	}
}
