package com.team2.shopperhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

/**
 * @author Dana Haywood
 * @date 7/19/2012
 * @version 0.1.5
 * @IT482
 * @Karl Lloyd
 * @Comment by:
 * @Source Cite: http://www.helloandroid.com/tutorials/connecting-mysql-database
 *         and http://geekswithblogs.net/bosuch/archive/2011/01/31/android---
 *         create-a-custom-multi-line-listview-bound-to-an.aspx
 * 
 *         The ShowProduct class will receive the information from the
 *         SearchProduct activity and attempt to pull the information from the
 *         PHP site. Once the information is received, it will list it similar
 *         to how ShowStore was done. At the moment, it is calling a test Method
 *         (temporary) to be able to test that functionality.
 * 
 */
@SuppressWarnings("unused")
public class ShowProduct extends Activity {
	public static final String PREF_NAME = "shopPref";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readweb);		
	    
		/*
		 * setting : Used to store information internally.
		 * queryType and queryValue should be seen like:
		 * 
		 * WHERE queryType=queryValue;
		 * 
		 * ListView where the custom rows will be inserted into.
		 * 
		 * httppost will be connecting the android to the webpage
		 * 
		 * ProductResult will create a string array to place into an
		 * arraylist.
		 * 
		 * nameValuePairs will contain the information required to 
		 * do the search function.
		 * 
		 * input and sqlResults are for gaining and storing the results from
		 * the webpage.
		 */
		SharedPreferences setting = getSharedPreferences(PREF_NAME,0);
		final int storeID = setting.getInt("storeID", 0);
		final String queryType = setting.getString("queryType", null);
		final String queryValue = setting.getString("queryValue", null);
		final ListView listView = (ListView) findViewById(R.id.webList);
		final HttpPost httppost = new HttpPost("someurl");

		ProductResults pr1 = new ProductResults();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		 nameValuePairs.add(new BasicNameValuePair("storeID", Integer
		 .toString(storeID)));
		 nameValuePairs.add(new BasicNameValuePair(queryType, queryValue));
		// InputStream input = null;
		// String sqlResults = null;

		/*
		 * Tempory test values.
		 */
		ArrayList<ProductResults> results = new ArrayList<ProductResults>();
		pr1.setName(Integer.toString(storeID));
		pr1.setPrice("2.99");
		pr1.setInventoryCount("200");
		pr1.setSection("Snack Food");
		pr1.setAisle("A1");

		results.add(pr1);

		listView.setAdapter(new ProductCustomBaseAdapter(this, results));
		/*
		 * This is sending and receiving the information established on the
		 * CTUMart website. Right now commented out due to the data/php site not
		 * put into the android yet.
		 */
		/*
		 * try {
		 * 
		 * Establishes a client for the Android to use.
		 * 
		 * HttpClient httpclient = new DefaultHttpClient();
		 * 
		 * turns the namevaluepairs into a format that the PHP page will
		 * understand.
		 * 
		 * httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 * 
		 * Sending the information to and receiving it back (this is Android
		 * portion of the SQL)
		 * 
		 * HttpResponse httpresponse = httpclient.execute(httppost);
		 * 
		 * Creating an entity within android
		 * 
		 * HttpEntity entity = httpresponse.getEntity();
		 * 
		 * getting the contents into Java.
		 * 
		 * input = entity.getContent(); } catch (UnsupportedEncodingException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (ClientProtocolException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * 
		 * Turning the response into a String for Java to understand.
		 * 
		 * 
		 * try { BufferedReader read = new BufferedReader(new InputStreamReader(
		 * input, "iso-8859-1"), 8); StringBuilder sb = new StringBuilder();
		 * String line = null; while ((line = read.readLine()) != null) {
		 * sb.append(line + "\n"); }
		 * 
		 * input.close(); sqlResults = sb.toString();
		 * 
		 * } catch (UnsupportedEncodingException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * Changing the information into items formatted for the screen.
		 * 
		 * 
		 * try { JSONArray jsonArray = new JSONArray(sqlResults);
		 * 
		 * for (int i = 0; i < jsonArray.length(); i++) { JSONObject jsonData =
		 * jsonArray.getJSONObject(i);
		 * 
		 * 
		 * grabbing the product name from the DB
		 * 
		 * String productName = jsonData.getString("productName");
		 * pr1.setProductName(productName);
		 * 
		 * 
		 * grabbing the price.
		 * 
		 * Double price = jsonData.getDouble("price");
		 * pr1.setPrice(Double.toHexString(price));
		 * 
		 * 
		 * Grabbing inventory count
		 * 
		 * 
		 * int inventoryCount = jsonData.getInt("inventoryCount");
		 * pr1.setPrice(Integer.toString(inventoryCount));
		 * 
		 * 
		 * Grabbing section
		 * 
		 * 
		 * String section = jsonData.getString("sections");
		 * 
		 * pr1.setSection(section);
		 * 
		 * 
		 * grabbing Aisle
		 * 
		 * 
		 * String aisle = jsonData.getString("aisle"); pr1.setAisle(aisle);
		 * 
		 * results.add(pr1);
		 * 
		 * } } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		/*
		 * creating an instance of ListView as well as setting an adapter to it
		 * to list the contents we have made.
		 */

	}

}