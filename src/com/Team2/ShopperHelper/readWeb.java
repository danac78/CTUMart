package com.Team2.ShopperHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.TextView;

public class readWeb
{
		private JSONArray ProductResult;
		private ArrayList<NameValuePair> valueSearch = new ArrayList<NameValuePair>(); // to store values to pass to the parser for search
		private HttpClient httpclient = new DefaultHttpClient(); // assigns an object for the HTTP Client
		private HttpPost http; // designates where we are going to post and retrieve the information.
		private String result;
		private InputStream input;
		int valueType;
		private String[] ProductNameArray;
		private int[] inventoryCount;
		private Double[] Price;
		private String[] Section;
		private String[] Aisle;
		private int[] UPCID;
	
	public readWeb(String ProductName, String ProductType, String UPC, String StoreID, TextView tv, int valueType)
	{
		this.valueType = valueType;
		
		// puts the value pairs together by calling the correct method to do so
		switch(valueType)
		{
			case 1:setValueSearch("productName",ProductName);
			case 2:setValueSearch("ProductType",ProductType);
			case 3:setValueSearch("UPC",UPC);
		}
		// setups the HTTP connection
		input = setupHTTP(http,input,StoreID);
		//converts the response that the HTTP requests gets
		result = convertResponse(result,input);
		//getting the information into variables.
		parseWeb(result);
		// displaying the information on the screen
		displayWeb();
				
	}
	
	private void parseWeb(String result2) {
		try
		{
			ProductResult = new JSONArray(result2);
			
			for(int i=0;i<ProductResult.length();i++)
			{
				//calls methods to be able to parse the information. The reason for dividing them into "Get" methods
				// is that they sometimes repeat each other.
				JSONObject json_data = ProductResult.getJSONObject(i);
				UPCID[i]=getJsonInt("UPC",json_data);
				ProductNameArray[i]=getJsonString("productName",json_data);
				inventoryCount[i]=getJsonInt("inventoryCount",json_data);
				Price[i]=getJsonDouble("Price",json_data);
				Section[i]=getJsonString("Sections",json_data);
				Aisle[i]=getJsonString("aisle",json_data);
				
				
			}
		} catch (JSONException jsonException)
		{
			System.out.print(jsonException);
		}
		
	}
    private Double getJsonDouble(String category, JSONObject json_data) throws JSONException {
		
		return json_data.getDouble(category);
	}

	private String getJsonString(String category, JSONObject json_data) throws JSONException {
		
		return json_data.getString(category);
	}

	private int getJsonInt(String category, JSONObject json_data) throws JSONException
    {
    		
    	  	
    	return json_data.getInt(category);
    }
	private InputStream setupHTTP(HttpPost http2, InputStream input2, String storeID) {
		
		try{
			http = new HttpPost("Http://someurl");
			http.setEntity(new UrlEncodedFormEntity(valueSearch)); // attaches the named pairs into an entity.
			HttpResponse response = httpclient.execute(http); // sending the name pair to the PHP page and waiting a response
			HttpEntity entity = response.getEntity(); // forming a way to retrieve the response.
			input2 = entity.getContent(); // getting the response.
			
		} catch (Exception e){
			System.out.print(e);
		}
		
		return input2;
	}



	private String convertResponse(String result2, InputStream input2) 
	{
		try
		{
			// creating a buffered reader to go through the input. Additionally, setting it to the iso standard
			// it will be reading.
			BufferedReader buffed = new BufferedReader(new InputStreamReader(input,"iso-8859-1"),8);
			
			// creating an item create the strings. Naming it sb due to reserved names in Android
			StringBuilder sb=new StringBuilder();
			
			// creating a line variable to hold each line the value shall contain.
			String line = null;
			
			// while the line that being read is not blank, it is going to add it to the build variable
			while ((line= buffed.readLine()) != null)
			{
				sb.append(line+"\n");
			}
			result2=sb.toString();
			// closing the input stream.
			input.close();
		} catch (IOException ioException)
		{
			System.out.print(ioException);
		}
		
					 
		// returning the result set as a string.
		return result2;
	}




	public void setValueSearch(String category,String value)
	{
		// this is combining an added pair of between the column name and the value we are searching for in mysql.
		// this was done to prevent typing in this line multiple times.
		valueSearch.add(new BasicNameValuePair(category,value));
	}
	
	public void displayWeb()
	{
		for(int i=0;i<ProductResult.length();i++)
		{
			System.out.println("UPC: "+UPCID[i]+" Product Name: "+ProductNameArray[i]+" Price:"+Price[i]+"+" +
					"Inventory Count: "+inventoryCount[i]+" Section:"+Section[i]+" Aisle"+Aisle[i]);
		}
	}
}


