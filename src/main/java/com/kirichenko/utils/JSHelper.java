package com.kirichenko.utils;

import java.util.StringTokenizer;

/**
 * @model
 * @author Brian J. Stewart (Aqua Data Technologies, Inc. http://www.aquadatatech.com)
 *
 * The JSHelper class contains helper functions for working with javascript.
 */
public class JSHelper {
	
	/**
	 * The convertStringToJsArray method converts the string (inputStr parameter) 
	 * separated by the specified delimitter (delimitter parameter) into a string
	 * contain a javascript string array.
	 * @param inputStr String to parse
	 * @param delimitter Value separator
	 * @return String containing a Javascript array
	 */
	public static String convertStringToJsArray(String inputStr, String delimitter) {
		if (inputStr == null || inputStr.length() < 1) return "null";
		
		boolean isFirst = true; 
		StringBuffer array = new StringBuffer();
		array.append("[");
		
		// Tokenize string and append each element as a quoted javascript array element
		StringTokenizer tokenizer = new StringTokenizer(inputStr, delimitter);
		while (tokenizer.hasMoreTokens()) {
			String curValue = tokenizer.nextToken();

			// Append comma if not first element
			if (!isFirst) {
				array.append(",");
			}
			
			// Append quoted string
			array.append("'");
			array.append(curValue);
			array.append("'");
			isFirst = false;
		}

		array.append("]");
		
		return array.toString();
	}
}
