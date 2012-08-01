package com.team2.shopperhelper.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class SearchFunction {

	private JSONParser jsonParser;
	private static String lookUpUrl = "http://www.darkenvisuals.com/android/";
	
	
	
	
	public SearchFunction() {
		jsonParser = new JSONParser();
	}
	
	public JSONObject searchProduct(String storeID, String queryType,
			String queryValue)
	{
		List<NameValuePair> parms = new ArrayList<NameValuePair>();
		
		parms.add(new BasicNameValuePair("queryType", queryType));
		parms.add(new BasicNameValuePair("storeId",storeID));
		parms.add(new BasicNameValuePair("queryValue",queryValue));
		JSONObject json = jsonParser.getJSONFromURL(lookUpUrl, parms);
		return json;
		
	}

}
