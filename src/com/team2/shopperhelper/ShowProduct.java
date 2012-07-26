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
 *         PHP site. Once the information is received, it will post it into a
 *         custom list view. The list view will have customrow injected with
 *         text views to have a customized listing.
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
		 * setting : Used to store information internally. queryType and
		 * queryValue should be seen like:
		 * 
		 * WHERE queryType=queryValue;
		 * 
		 * ListView where the custom rows will be inserted into.
		 * 
		 * httppost will be connecting the android to the webpage
		 * 
		 * ProductResult will create a string array to place into an arraylist.
		 * 
		 * nameValuePairs will contain the information required to do the search
		 * function.
		 * 
		 * input and sqlResults are for gaining and storing the results from the
		 * webpage.
		 */
		SharedPreferences setting = getSharedPreferences(PREF_NAME, 0);
		final int storeID = setting.getInt("storeID", 0);
		final String queryType = setting.getString("queryType", null);
		final String queryValue = setting.getString("queryValue", null);
		final ListView listView = (ListView) findViewById(R.id.webList);
		final HttpPost httppost = new HttpPost(
				"http://www.fuelradio.fm/ctumart/android.php");

		ProductResults pr1 = new ProductResults();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("queryStoreID",
				"tblStore_storeID"));

		nameValuePairs.add(new BasicNameValuePair("storeID", Integer
				.toString(storeID)));
		nameValuePairs.add(new BasicNameValuePair("queryType", queryType));
		nameValuePairs.add(new BasicNameValuePair("queryValue", queryValue));
		InputStream input = null;
		String sqlResults = null;
		ArrayList<ProductResults> results = new ArrayList<ProductResults>();

		/*
		 * This is sending and receiving the information established on the
		 * CTUMart website. Right now commented out due to the data/php site not
		 * put into the android yet.
		 */

		try {
			/*
			 * Establishes a client for Android to Use.
			 */

			HttpClient httpclient = new DefaultHttpClient();
			/*
			 * turns namevaluespairs into a format the PPH page will understand
			 */
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			/*
			 * Sending the information to the PHP page, as well as retrieving
			 * the response.
			 */

			HttpResponse httpResponse = httpclient.execute(httppost);

			/*
			 * Creating an entity within Android
			 */

			HttpEntity entity = httpResponse.getEntity();

			/*
			 * getting the contents into Java
			 */

			input = entity.getContent();
		} catch (UnsupportedEncodingException e) {
			// If there is an unsupported encoding type, it will trigger
			// this exception.
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// If there is a problem with connecting with the client, it
			//trigger this exception.
			e.printStackTrace();
		} catch (IOException e) {
			// If there is a problem with IO, it will post it here.
			e.printStackTrace();
		}

		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(
					input, "iso-8859-1"), 8);

			StringBuilder sb = new StringBuilder();

			String line = null;

			while ((line = read.readLine()) != null) {
				sb.append(line + "\n");
			}

			input.close();
			sqlResults = sb.toString();

		} catch (UnsupportedEncodingException e) {
			// If there is an unsupported encoding type, it will trigger
			// this exception.
			e.printStackTrace();
		} catch (IOException e) {
			// If there is a problem with IO, it will post it here.
			e.printStackTrace();
		}

		/*
		 * Changing the information into items formatted on the screen
		 */

		try {
			JSONArray jsonArray = new JSONArray(sqlResults);

			for (int i = 0; i < jsonArray.length(); i++) {
				/*
				 * Obtaining the values from jsonArray based on the index. It
				 * will also serve as part of a conversion point for setting it
				 * to product results (pr1) that will be stored in it own array.
				 */
				JSONObject jsonData = jsonArray.getJSONObject(i);

				/*
				 * Grabbing the product name from the DB.
				 */

				pr1.setName(jsonData.getString("productName"));

				/*
				 * grabbing the price. Grabs the decimal value as a double (java
				 * equivalent). It will use the toString to change it into the
				 * string required by the ArrayList.
				 */

				pr1.setPrice(Double.toString(jsonData.getDouble("productPrice")));

				/*
				 * Grabbing the inventory count. It is grabbing it as an
				 * integer, and using the toString() method to change it into a
				 * string for the ArrayList.
				 */

				pr1.setInventoryCount(Integer.toString(jsonData
						.getInt("inventoryCount")));

				/*
				 * Grabbing the section
				 */

				pr1.setSection(jsonData.getString("storeSection"));

				/*
				 * Grabbing the Aisle.
				 */
				pr1.setAisle(jsonData.getString("aisle"));

				results.add(pr1);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * creating an instance of ListView as well as setting an adapter to it
		 * to list the contents we have made.
		 */

		listView.setAdapter(new ProductCustomBaseAdapter(this, results));

	}
}