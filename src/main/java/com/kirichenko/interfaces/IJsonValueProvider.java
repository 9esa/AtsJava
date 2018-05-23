package com.kirichenko.interfaces;

import org.json.JSONArray;

/**
 * @model
 * @author Brian J. Stewart (Aqua Data Technologies, Inc. http://www.aquadatatech.com)
 *
 */
public interface IJsonValueProvider {
	/**
	 * Returns a JSONArray of the values based on the specified criteria. The maximum
	 * (maxCount) number of values is retrieved.
	 * @param criteria Criteria for which to search
	 * @param maxCount Maximum number of records to retrieve
	 * @return JSONArray of values matching criteria
	 */
	JSONArray getValues(String criteria, Integer maxCount);
}
