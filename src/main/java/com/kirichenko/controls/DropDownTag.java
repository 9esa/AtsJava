package com.kirichenko.controls;

import com.kirichenko.utils.JSHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @model
 * @author Brian J. Stewart (Aqua Data Technologies, Inc. http://www.aquadatatech.com)
 *
 */
public class DropDownTag extends TagSupport {

	/**
	 * The DropDownTag provides asynchronously populated data values for a drop down. 
	 * It also supports cascading drop down, that enables controls to dynamically and
	 * asynchronously be populated with data values based on the value of other drop down 
	 * controls.
	 */
	private static final long serialVersionUID = 2409788141416178684L;
	private String m_controlId = null;
	private String m_cssClass = null;
	private String m_dataUrl = null;
	private String m_updateMessage = null;
	private String m_controlValue = null;
	private String m_cascadeFrom = null;
	private String m_cascadeTo= null;
	private int m_controlWidth = 0;
	
	/**
	 * The getWidth method returns the width of the drop down control. The default is 
	 * autofit. 
	 * @return Control width
	 */
	public int getWidth() {
		return this.m_controlWidth;
	}
	
	/**
	 * The setWidth method sets the width of the drop down control.
	 * @param value Control width
	 */
	public void setWidth(int value) {
		this.m_controlWidth = value;
	}
	
	/** 
	 * The setCascadeto method sets the list controls to which the current control cascades to.
	 * The list should be comma separated.
	 * @param value Comma separated list
	 */
	public void setCascadeto(String value) {
		this.m_cascadeTo = value;
	}
	
	/**
	 * The getCascadeto method returns the list controls to which the current control 
	 * cascades to.
	 * @return Comma separated list
	 */
	public String getCascadeto() {
		return this.m_cascadeTo;
	}
	
	/** 
	 * The setCascadefrom method sets the list controls from which the current control 
	 * cascades. The list should be comma separated.
	 * @param value Comma separated list
	 */
	public void setCascadefrom(String value) {
		this.m_cascadeFrom = value;
	}
	
	/**
	 * The getCascadefrom method returns the list controls from which the current control 
	 * cascades from.
	 * @return Comma separated list
	 */
	public String getCascadefrom() {
		return this.m_cascadeFrom;
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
	 * The setUpdatemessage sets the status message that is displayed when updating 
	 * the current control.
	 * @param value Status message to display
	 */
	public void setUpdatemessage(String value) {
		this.m_updateMessage = value;
	}
	
	/**
	 * The getUpdatemessage returns the status message that is displayed when updating
	 * the current control.
	 * @return Status message to display
	 */
	public String getUpdatemessage() {
		return this.m_updateMessage;
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
	 * The getSelectControlHtml method returns the html code to render the drop down (html
	 * select) control. 
	 * @return Html code for drop down (html select) control
	 */
	protected String getSelectControlHtml() {
		StringBuffer html = new StringBuffer();
		
		// Render dropdown/select control
		html.append("<select id='");
		html.append(this.getId());
		
		// Render on focus event handler
		html.append("' onfocus='");
		html.append(this.getId());
		html.append("_onSelect(this)'");
		
		// Render on change event handler
		html.append(" onChange='");
		html.append(this.getId());
		html.append("_onChange(this)'");
		
		// Render css class if specified
		if (this.getCssclass() != null && this.getCssclass().length() > 0) {
			html.append(" class='");
			html.append(this.getCssclass());
			html.append("'");
		}		
		
		// Render width if applicable (not 0/default/auto-fit)
		if (this.getWidth() > 0) {
			html.append(" style='width:");
			html.append(this.getWidth());
			html.append("px'");
		}
		
		html.append("/>");
		
		return html.toString();
	}
	
	/**
	 * The getOnChangeFunction method returns the javascript code for the onchange event for
	 * the drop down
	 * @return Javascript code for the onchange event for the drop down
	 */
	protected String getOnChangeFunction() {
		StringBuffer html = new StringBuffer();
		html.append("function ");
		html.append(this.getId());
		html.append("_onChange(curControl) {");
		html.append("var toList=");
		html.append(JSHelper.convertStringToJsArray(this.getCascadeto(), ","));
		html.append(";");
		
		html.append("if (toList == null || toList.length == 0) return;");
		
		// Iterate through all controls in toList and clear the options
		html.append("for (var i=0; i < toList.length; i++) {");
		html.append("var curControlName = toList[i];");
		html.append("var curToControl = document.getElementById(curControlName);");
		html.append("if (curToControl == null) return;");
		html.append("clearOptions(curToControl);");
		
		html.append("}");
				
		html.append("}");
		
		return html.toString();
	}
	
	/**
	 * The getDataUrlForAsyncCallBack builds the Url used for async callback to 
	 * server for data retrieval. The data values for the controls specified in the 
	 * cascadefrom attribute are automatically appended to the Url.
	 * @return Url (servlet or jsp page) to retrieve data values
	 */
	protected String getDataUrlForAsyncCallBack() {
		StringBuffer html = new StringBuffer();
		
		html.append("var dataUrl = '");
		html.append(pageContext.getServletContext().getContextPath());
		html.append(this.getDataurl());
		html.append("?format=json&");		
		html.append(this.getId());
		html.append("='");
		html.append(" + getSelectedValue('");
		html.append(this.getId());
		html.append("')");
		// Iterate through all cascade from values and add to query string
		if (this.getCascadefrom() != null && this.getCascadefrom().length() > 0) { 
			StringTokenizer tokenizer = new StringTokenizer(this.getCascadefrom(), ",");
			while (tokenizer.hasMoreTokens()) {
				String curToken = tokenizer.nextToken();
				html.append("+ '&");
				html.append(curToken);
				html.append("='");
				html.append(" + getSelectedValue('");
				html.append(curToken);
				html.append("')");
			}		
		}
		html.append(";");
		//html.append("alert('dataurl=' + dataUrl);");
		return html.toString();
	}
	
	/**
	 * The getOnSelectFunction method returns the javascript code for the onselect event for
	 * the drop down
	 * @return Javascript code for the onselect event for the drop down
	 */
	protected String getOnSelectFunction() {
		StringBuffer html = new StringBuffer();

		html.append("function ");
		html.append(this.getId());
		html.append("_onSelect(curControl) {");
		html.append("if(curControl.options.length > 0) return;");
		
		html.append("clearOptions(curControl);");
		html.append("var waitingOption = new Option(");
		html.append("'Загружаем данные...','',true,true);");
		html.append("curControl.options[curControl.options.length]=waitingOption;");
		
		// Render data url		
		html.append(this.getDataUrlForAsyncCallBack());
		
		// Render XmlHttpRequest and open control
		html.append("initializeXmlHttpRequest();");
		html.append("if (req!=null) {");
		html.append("req.onreadystatechange=");
		html.append(this.getId());
		html.append("_onServerResponse;");		
		if (this.getUpdatemessage() != null && this.getUpdatemessage().length() > 0) {
			html.append("window.status='");
			html.append(this.getUpdatemessage());
			html.append("';");	
		}
 		html.append("req.open('GET',dataUrl,true);");
		html.append("req.send(null);");
		html.append("}");
		html.append("}");
		
		return html.toString();
	}
	
	
	/**
	 * The getOnServerResponse method returns the javascript code for the on server response
	 * received event for the drop down
	 * @return Javascript code for the on async callback event for the drop down
	 */
	protected String getOnServerResponse() {
		StringBuffer html = new StringBuffer();

		// Render the onServerResponse_CONTROLNAME
		html.append("function ");
		html.append(this.getId());
		html.append("_onServerResponse() {");
		html.append("if(req.readyState!=4) return;");
		html.append("if(req.status != 200) {");
		html.append("alert('Ошибка загрузки данных.');");
		html.append("return;");
		html.append("}");					
		
		html.append("var curControl = document.getElementById('");
		html.append(this.getId());
		html.append("');");

		// Clear options		
		html.append("clearOptions(curControl);");

		// Get options		
		html.append("var responseData = req.responseText;");
		html.append("var dataValues=eval('(' + responseData + ')');");
		
		// Populate current control
		html.append("populateSelectControl(curControl, dataValues);");
		
		// Render up status 
		if (this.getUpdatemessage() != null && this.getUpdatemessage().length() > 0) {
			html.append("window.status='';");			
		}
		html.append("}");
		
		return html.toString();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		StringBuffer html = new StringBuffer();
		
		// Render dropdown/select control
		html.append(this.getSelectControlHtml());
		
		// Render the script tag
		html.append("<script type='text/javascript' language='javascript'>");
		html.append(this.getOnChangeFunction());
		html.append(this.getOnSelectFunction());
		html.append(this.getOnServerResponse());
		html.append("</script>");

		// Write output to response
		JspWriter out = pageContext.getOut();		
		try {
			out.append(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return DropDownTag.EVAL_BODY_INCLUDE;
	}
}
