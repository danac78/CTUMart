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
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	static InputStream is=null;
	static JSONObject jObj = null;
	static String json = "";
	public JSONParser() {
		
	}
	
	public JSONObject getJSONFromURL(String url, List<NameValuePair> parms)
	{
		
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parms));
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is= httpEntity.getContent();
			
		} catch (UnsupportedEncodingException e) {
			Log.e("UnsupportedEncoding", e.toString());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.e("CLientProtocol",e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException",e.toString());
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new 
					BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine())!=null)
			{
				sb.append(line+"/n");
			}
			is.close();
			json = sb.toString();
			Log.e("JSON", json);
		} catch (UnsupportedEncodingException e) {
			Log.e("UnsupportedEncoding",e.toString());
		} catch (IOException e) {
			Log.e("IOException",e.toString());
		}
		
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON",e.toString());
		}
		
		return jObj;
		
	}

}
