/**
 * 
 */
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
public class AjaxPageTag extends TagSupport {

	/**
	 * The AjaxPageTag class is a JSP tag that adds support asynchronous callbacks. It also
	 * adds common javascript helper functions to page
	 *
	 */
	private static final long serialVersionUID = 7503522819220260138L;

	/**
	 * The getInitializeXmlHttpRequestFunction method returns the javascript code 
	 * to initialize the XmlHttpRequest object for asynchronous communication.
	 * @return Javascript code to initialize the XmlHttpRequest 
	 */
	protected String getInitializeXmlHttpRequestFunction() {
		StringBuffer html = new StringBuffer();		
		html.append("function initializeXmlHttpRequest() {");		
		html.append("if (window.ActiveXObject) {");
		// Support for Microsoft browsers
		html.append("req=new ActiveXObject('Microsoft.XMLHTTP');");
		html.append("}");
		// Support for non-Microsoft browsers (and IE7+)
		html.append("else {");		
		html.append("req=new XMLHttpRequest();");
		html.append("}");
		html.append("}");
		
		return html.toString();
	}

	/**
	 * The getDisableFieldFunction returns the javascript code to disable a form field
	 * specified by controlName argument
	 * @return Javascript function disable field
	 */
	protected String getDisableFieldFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function disableFormField(controlName, isDisabled) {");
		// Default to disabled if argument not found
		html.append("if (isDisabled == null) isDisabled = true;");
		
		// Check argument(s)
		html.append("if (controlName == null) return;");
		
		// Get control by name
		html.append("var curControl = document.getElementById(controlName);");
		
		// Check if control was found
		html.append("if (curControl == null) return;");
		
		html.append("curControl.disabled = isDisabled;");
		
		html.append("}");
		
		return html.toString();
	}
	/**
	 * The getClearOptionsFunction returns the javascript code to clear a SELECT  control.
	 * @return Javascript code to clear a select control
	 */
	protected String getClearOptionsFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function clearOptions(curControl) {");
		html.append("if (curControl == null)  {");
		html.append("alert('Unable to clear control');");
		html.append("return;");
		html.append("}");
		html.append("if (curControl.options.length < 1) return;");
		html.append("curControl.options.length = 0;");
		html.append("}");
		
		return html.toString();
	}
	
	/**
	 * The getSelectedValueFunction gets the javascript code to retrieve the selected
	 * value for the specified SELECT control
	 * @return Javascript code to retrieved selected value for specified SELECT control
	 */
	protected String getSelectedValueFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function getSelectedValue(controlName) {");

		// Check argument(s)
		html.append("if (controlName == null) return '';");
		
		// Get control by name
		html.append("var curControl = document.getElementById(controlName);");
		
		// Check if control was found
		html.append("if (curControl == null) return '';");
		
		// If control was found, check if item is selected
		html.append("if (curControl.selectedIndex < 0) return '';");
		
		// Finally get selected value
		html.append("var selectedValue = curControl.options[curControl.selectedIndex].value;");
		html.append("return selectedValue;");

		html.append("}");
		
		return html.toString();
	}
	
	/**
	 * The getHandleSelectSuggestItem gets the javascript code to handle
	 * the selection of a item in the suggestionList
	 * @return Javascript code for onSelect Suggest Item handler
	 */
	public String getHandleSelectSuggestItem() {
		StringBuffer html = new StringBuffer();
		
		html.append("function handleSelectSuggestItem(curControl, suggestionList) {");			

		// Get selected node
	    html.append("var selectedNode = suggestionList.childNodes[cursor];");
	    
	    // Get selected item	    
	    html.append("var selectedValue = selectedNode.childNodes[0].nodeValue;");	    
	    
	    // Set value of current control to highlighted value
	    html.append("curControl.value = selectedValue;");
	    
	    // Hide suggestion list	   	    
	    html.append("hideSelectionList(curControl, suggestionList);");		    
		
	    html.append("}");
		
		return html.toString();
	}
	
	/**
	 * The getHandleClickSuggestItem gets the javascript code to handle
	 * the mouse click of a item in the suggestionList
	 * @return Javascript code for onSelect Suggest Item handler
	 */
	public String getHandleClickSuggestItem() {
		StringBuffer html = new StringBuffer();
		
		html.append("function handleClickSuggestItem(controlName, selectedValue) {");
		html.append("alert('clicked');");
		
		// Get suggestion list by name
		html.append("var suggestionList = document.getElementById(controlName + '_suggest');");
		
		// Get control by name
		html.append("var curControl = document.getElementById(controlName);");
		
		// Set value of control
		html.append("curControl.value = selectedValue;");
		
		// Hide selection list
	    html.append("hideSelectionList(curControl, suggestionList);");

	    html.append("}");
	    
		return html.toString();
	}
	
	/**
	 * The getKeyUpFunction method returns the javascript code for key up event handler
	 * @return Javascript code for the onchange event for the drop down
	 */
	protected String getSuggestionKeyUpFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function onSuggestionKeyUp(curControl, ev, minChars, dataUrl) {");		
		
		// Get key code for key pressed
		html.append("var keyCode = (window.event) ? window.event.keyCode : ev.keyCode;");
		
		// Get value list for current field 
		html.append("var suggestionList= document.getElementById(curControl.id + '_suggest');");				
			
		//html.append("alert(keyCode);");
		// Process key accordingly
		html.append("switch(keyCode) {");			
			html.append("case 27: ");
				// Do nothing
			
		    // Handle up arrow is pressed
		    html.append("case 38: ");		
			    html.append("if (suggestionList.childNodes.length > 0 && cursor > 0)"); 
			    html.append("{");
		    	    
			    // Decrease selected node
			    html.append("var selectedNode = suggestionList.childNodes[--cursor];");
			    html.append("highlightSelectedNode(suggestionList, selectedNode);");		
			    // html.append("return false;");
			    html.append("}");
			    html.append("break;");
    
		    // Handle down arrow is pressed
		    html.append("case 40: ");
			    html.append("if (suggestionList.childNodes.length > 0 && cursor < suggestionList.childNodes.length-1)"); 
			    html.append("{");	    		    
			    
			    // Increase selected node
			    html.append("var selectedNode = suggestionList.childNodes[++cursor];");
				html.append("highlightSelectedNode(suggestionList, selectedNode);");
				
				//html.append("return false;");
			    html.append("}");
			    html.append("break;");

		    html.append("default: ");		
				// Render check to only autocomplete after minimum number of characters are entered..
				html.append("if (curControl == null || curControl.value.length < minChars) { ");
				html.append("hideSelectionList(curControl, suggestionList);");
				html.append("return;");
				html.append("}");	
							
				//html.append("alert('url=' + dataUrl);");			
				// Render server callback invocation
				html.append("initializeXmlHttpRequest();");
				html.append("if (req!=null) {");
				html.append("req.onreadystatechange=eval(curControl.id + '_onServerResponse');");
				
				html.append("req.open('GET',dataUrl,true);");
				html.append("req.send(null);");		
				html.append("}");
			
		// End of switch
	    html.append("}");
		
		html.append("}");
		
		return html.toString();
	}

	/**
	 * The getSuggestionKeyDownFunction method returns the javascript code for key down event handler
	 * @return Javascript code for the onchange event for the drop down
	 */
	protected String getSuggestionKeyDownFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function onSuggestionKeyDown(curControl, ev) {");		
		
		// Get value list for current field 
		html.append("var suggestionList= document.getElementById(curControl.id + '_suggest');");	
		
		html.append("var keyCode = (window.event) ? window.event.keyCode : ev.keyCode;");
		
		//html.append("alert(keyCode);");
		
		html.append("switch(keyCode) {");
			// Ignore certain characters
			html.append("case 16, 17, 18, 20: ");
			html.append("ev.cancelBubble = true;"); // IE
		    
		    html.append("if (window.event)");
		    html.append("{ ev.returnValue = false; }"); // IE
		    html.append("else { ev.preventDefault(); }");// Firefox
		    html.append("break;");
		    
			// Handle escape key pressed
			html.append("case 27: ");
			// Hide list
			html.append("hideSelectionList(curControl, suggestionList);");		
			
			html.append("ev.cancelBubble = true;"); 
		    
		    html.append("if (window.event)");
		    html.append("{ ev.returnValue = false; }"); // IE
		    html.append("else { ev.preventDefault(); }");// Firefox
			
			html.append("break;");
			
			// Handle enter key is pressed
		    html.append("case 13: ");	    	
		    
		    html.append("handleSelectSuggestItem(curControl, suggestionList);");
		    
		    html.append("ev.cancelBubble = true;");
		    
		    html.append("if (window.event)");
		    html.append("{ ev.returnValue = false; }"); // IE
		    html.append("else { ev.preventDefault(); }");// Firefox
		    
		    html.append("break;");
		    
		// End of switch		
		html.append("}");		
		
		// End of function
		html.append("}");
		
		return html.toString();
	}

	/**
	 * The getPopulateSuggestionListFunction returns the javascript function to
	 * parse JSON response and populate suggestion list for Ajax Auto-complete
	 * control 
	 * @return Javascript function to populate suggestion list
	 */
	public String getPopulateSuggestionListFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function populateSuggestionList(curControl, dataValues) {");
		// Get container for values
		html.append("var container = document.getElementById(curControl.id + '_suggest');");
		html.append("if (container == null) { return; }");
		
		// Clear parent container
		html.append("container.innerHTML = '';");
		
		html.append("if (dataValues.length < 1) { ");
			html.append("container.style.display='none';");
			html.append("return;");
		html.append("}");
		
		// Show suggestion list
		html.append("container.style.display='block';");		
		html.append("container.style.top = (curControl.offsetTop+curControl.offsetHeight) + 'px';");		
		html.append("container.style.left = curControl.offsetLeft + 'px';");		
		
		// Iterate through all data values and add DIV to container for each value
		html.append("for(var i=0;i<dataValues.length;i++) {");
			html.append("var curValue= dataValues[i];");
			html.append("if (curValue != null && curValue.length >0 ) {");
				// Create new item
				html.append("var newItem = document.createElement('div');");
				
				html.append("newItem.appendChild(document.createTextNode(curValue));");
				
				html.append("newItem.setAttribute('class', 'autoCompleteItem');");
								
				// Add item
				html.append("container.appendChild(newItem);");
			html.append("}");
		html.append("}");
		
		// For debugging purposes, show generated dynamic HTML 
		//html.append("alert(container.innerHTML);");
		
		// Set cursor location to first item 
		html.append("cursor = 0;");
		html.append("var selectedNode = container.childNodes[cursor];");
		
		// Check if first item is equal to the first item in the suggestion list, 
		// if so hide the suggestion list.
		html.append("if (selectedNode.childNodes[0].nodeValue == curControl.value) {");
			html.append("hideSelectionList(curControl, container);");
		html.append("}");
		// Else highlight the first item
		html.append("else {");
			html.append("highlightSelectedNode(container, selectedNode);");
		html.append("}");
		
		
		html.append("}");
		
		return html.toString();
	}
	
	/**
	 * The getHighlightSelectedNodeFunction returns the javascript function to
	 * highlight the selected node.
	 * @return Javascript to highligh selected node
	 */
	public String getHighlightSelectedNodeFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function highlightSelectedNode(suggestionList, selectedNode) {");
		
		// Iterate through all children and highlight the selected note
	    html.append("if (suggestionList == null || selectedNode == null) { return; }");
		html.append("for (var i=0; i < suggestionList.childNodes.length; i++)"); 
		html.append("{");		
			html.append("var curNode = suggestionList.childNodes[i];");
			html.append("if (curNode == selectedNode)");
			html.append("{");
				html.append("curNode.className = 'autoCompleteItemSelected'");
			html.append("} ");
			html.append("else {"); 		
				html.append("curNode.className = 'autoCompleteItem';");
				html.append("}");
		// End of loop
		html.append("}");
		
		// End of function
		html.append("}");
		
		return html.toString();
	}
	/**
	 * The hideSelectListFunction gets the javascript function that is used to hide a selection
	 * list for the autoocmplete function.
	 * @return Javavascript to hide selection list
	 */
	protected String getHideSelectionListFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function hideSelectionList(curControl, suggestionList) {");
			
		html.append("if (suggestionList == null || suggestionList == undefined) { return; }");
		html.append("suggestionList.innerHTML='';");			
		html.append("suggestionList.style.display='none';");
		html.append("curControl.focus();");
			
		html.append("}");
		
		return html.toString();
	}

	
	protected String getSuggestionLostFocus() {
		StringBuffer html = new StringBuffer();
		
		html.append("function onSuggestionFocusLost(curControl) {");
		html.append("var suggestionList = document.getElementById(curControl.id + '_suggest');");
		html.append("hideSelectionList(curControl, suggestionList);");
	
		html.append("}");
		
		return html.toString();
	}
	
	/**
	 * The getPopulateSelectControlFunction function returns the JavaScript 
	 * function to populate the specified control (curControl) with values 
	 * containing in the dataValues argument.
	 * @return JavaScript function to populate SELECT control
	 */
	protected String getPopulateSelectControlFunction() {
		StringBuffer html = new StringBuffer();
		
		html.append("function populateSelectControl(curControl, dataValues) {");
		// Add blank OPTION
		html.append("var blankOption= new Option('','',false,true);");
		html.append("curControl.options[curControl.options.length]=blankOption;");
		
		// Render new options			
 		html.append("for (var i=0;i<dataValues.length;i++) {");
 			// Create new OPTION
 			html.append("var newOption= new Option(");
 			html.append("dataValues[i],dataValues[i],false,false);");
 			html.append("curControl.options[curControl.options.length]=newOption;");		
		html.append("}");
		
		html.append("}");
		
		return html.toString();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		StringBuffer html = new StringBuffer();
		
		html.append("<script type='text/javascript' language='javascript'>");
		html.append("var req;");
		html.append("var cursor = -1;");
		// Generate functions to support Ajax
		html.append(this.getInitializeXmlHttpRequestFunction());
		
		// Ajax enabled Drop Down control functions
		html.append(this.getClearOptionsFunction());
		html.append(this.getPopulateSelectControlFunction());
		
		// Ajax enabled Auto Populate control functions
		html.append(this.getSelectedValueFunction());
		html.append(this.getDisableFieldFunction());
		
		// Ajax enabled Auto Suggestion control functions
		html.append(this.getPopulateSuggestionListFunction());
		html.append(this.getHideSelectionListFunction());
		html.append(this.getHighlightSelectedNodeFunction());
		html.append(this.getHandleClickSuggestItem());
		html.append(this.getHandleSelectSuggestItem());
		html.append(this.getSuggestionKeyDownFunction());
		html.append(this.getSuggestionKeyUpFunction());
		html.append(this.getSuggestionLostFocus());
		html.append("</script>");
		
		JspWriter out = pageContext.getOut();
		try {
			out.print(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.SKIP_BODY;
	}

	

}
