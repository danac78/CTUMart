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

/**
 * Gathering the information from the web..
 * 
 * @author Dana Haywood
 * @version 0.5.2
 * @since 8/4/2012 <br>
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite::
 *        http://www.androidhive.info/2012/01/android-login-and-registration
 *        -with-php-mysql-and-sqlite/ @ Commented By:
 * 
 *        The purpose of this class is to provide a parser for the JSON encoded
 *        data that will be provided by the website. Additionally, this was made
 *        into a separate class to provide portability by declaring parms and
 *        url as items that will be passed in from other methods and used to
 *        obtain information and sent back to the calling procedure.
 * 
 */
public class JSONParser {
	/**
	 * Going to contain the values retrieve from the web.
	 */
	public String results;

	/**
	 * Going to contain the values we will be returning to Show Product.
	 */
	public JSONObject jsonObject;

	private InputStream is = null;

	/**
	 * A simple JSONParser constructor.
	 */
	public JSONParser() {
	}

	/**
	 * This method is going to do the following:
	 * <p>
	 * Creating a connection to the webpage. Once the connection is made,
	 * HttpResponse is going send out the request,and HttpEntity will be
	 * responsible for retrieving the feedback. Inputstream will be responsible
	 * to inputting the information into Java.
	 * </p>
	 * <p>
	 * Converting the inputstream into a String. Additionally, we need to create
	 * two JSON types. The first one is obtaining the JSON information from the
	 * converted string. The second one is gaining information from that object,
	 * and it is asking for a particular set of data.
	 * </p>
	 * Converting the inputstream into a String. Additionally, we need to create
	 * two JSON types. The first one is obtaining the JSON information from the
	 * converted string. The second one is gaining information from that object,
	 * and it is asking for a particular set of data.
	 * <p>
	 * 
	 * 
	 * @param parms
	 *            This is an list with the name value pairs (storeID, queryType,
	 *            QueryValue) to be used by the HttpPost process.
	 * 
	 * @param url
	 *            This is the url that the parser will be looking at for
	 *            information.
	 * @return Returning the JSON OBject for further parsing in ShowProduct.
	 */
	public JSONObject getJSONInformation(List<NameValuePair> parms, String url) {
		/*
		 * Connect to the web and gather the information
		 */
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parms));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			Log.e("UnsupportedEncoding", e.toString());

		} catch (ClientProtocolException e) {
			Log.e("CLientProtocol", e.toString());

		} catch (IOException e) {
			Log.e("IOException", e.toString());

		}
		/*
		 * Convert it to a string and into a JSON object.
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

		} catch (UnsupportedEncodingException e) {
			Log.e("UnsupportedEncoding", e.toString());
		} catch (IOException e) {
			Log.e("IOException", e.toString());
		} catch (JSONException e) {
			Log.e("JSON", e.toString());

		}

		/*
		 * Returning the JSON object to the procedure caller.
		 */
		return jsonObject;
	}

}