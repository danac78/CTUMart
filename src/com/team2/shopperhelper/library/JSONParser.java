package com.team2.shopperhelper.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	public int database_storeID;
	public String database_pName;
	public String database_price;
	public String database_inventoryCount;
	public String database_section;
	public String database_aisle;
	public String database_pType;
	public String database_UPC;
	public String database_Price;
	public String results;
	public JSONObject jsonObject;
	public JSONArray listObjects;
	private InputStream is = null;

	public JSONParser() {
	}
	
	public JSONArray getJSONInformation(List<NameValuePair> parms, String url)
	{

		try {
			/*
			 *Creating a connection to the webpage. Once the connection
			 *is made, HttpResponse is going send out the request,
			 *and HttpEntity will be responsible for retrieving the
			 *feedback. Inputstream will be responsible to inputting
			 *the information into Java.
			 */
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parms));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is  = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			Log.e("UnsupportedEncoding", e.toString());

		} catch (ClientProtocolException e) {
			Log.e("CLientProtocol", e.toString());

		} catch (IOException e) {
			Log.e("IOException", e.toString());

		}
		/*
		 * Converting the inputstream into a String. Additionally, we need
		 * to create two JSON types. The first one is obtaining the JSON
		 * information from the converted string. The second one is 
		 * gaining information from that object, and it is asking for
		 * a particular set of data.
		 */
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
			is.close();
			results = sb.toString();
			jsonObject = new JSONObject(results);

			listObjects = jsonObject.getJSONArray("productlist");

			
		} catch (UnsupportedEncodingException e) {
			Log.e("UnsupportedEncoding", e.toString());
		} catch (IOException e) {
			Log.e("IOException", e.toString());
		} catch (JSONException e) {
			Log.e("JSON", e.toString());
			e.printStackTrace();
		}
		
		return listObjects;
	}
	
}