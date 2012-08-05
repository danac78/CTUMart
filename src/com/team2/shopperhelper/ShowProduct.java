package com.team2.shopperhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.team2.shopperhelper.library.CustomBaseAdapter;
import com.team2.shopperhelper.library.SearchResults;

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
	static InputStream is = null;
	private int database_storeID;
	private String database_pName;
	private String database_price;
	private String database_inventoryCount;
	private String database_section;
	private String database_aisle;
	private String database_pType;
	private String database_UPC;
	private String database_Price;
	private String results;
	private JSONObject jsonObject;
	private JSONArray listObjects;
	private static final String url = "http://darkenvisuals.com/android/";

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
		final String storeID = setting.getString("storeID", null);
		final String queryType = setting.getString("queryType", null);
		final String queryValue = setting.getString("queryValue", null);
		
		
		/*
		 * This is where the android php page that be responsible for populating
		 * this page. The first thing we need to do is create name value pairs 
		 * that will hold the values we are passing to the web page.
		 */

		final HttpPost httppost = new HttpPost(
				"http://www.fuelradio.fm/ctumart/");


		List<NameValuePair> parms = new ArrayList<NameValuePair>();
		
		
		/*
		 * Adding the parameters that will be required to do the search.
		 */
		parms.add(new BasicNameValuePair("queryType", queryType));
		parms.add(new BasicNameValuePair("storeID", storeID));
		parms.add(new BasicNameValuePair("queryValue", queryValue));

		try {
			/*
			 *Creating a connection to the webpage. Once the connection
			 *is made, HttpResponse is going send out the request,
			 *and HttpEntity will be responsible for retrieving the
			 *feedback. Inputstream will be responsible to inputting
			 *the information into Java.
			 */
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(
					"http://www.darkenvisuals.com/android/");
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

		/*
		 * This is going to cycle through each set of data involved with the 
		 * array and turn them into individual values. Once this is done,
		 * it is going to assign it to the arraylist that will be posted to
		 * the ListView.
		 */
		
		ArrayList<SearchResults> arrayResults = new ArrayList<SearchResults>();

		try {

			SearchResults pr1 = new SearchResults();

			for (int i = 0; i < listObjects.length(); i++) {

				database_pName = listObjects.getJSONObject(i)
						.getString("productName").toString();
				database_Price = listObjects.getJSONObject(i)
						.getString("price").toString();
				database_inventoryCount = listObjects.getJSONObject(i)
						.getString("inventoryCount").toString();
				database_section = listObjects.getJSONObject(i)
						.getString("Sections").toString();
				database_aisle = listObjects.getJSONObject(i)
						.getString("Aisle").toString();

				pr1.setName(database_pName);
				pr1.setPrice(stringChange("Price: $",database_Price));
				pr1.setInventoryCount(stringChange("Inventory: ",database_inventoryCount));
				pr1.setSections(stringChange("Section: ",database_section));
				pr1.setAisle(stringChange("Aisle: ",database_aisle));
				arrayResults.add(pr1);

				pr1 = new SearchResults();
			}
			/*
			 * Posting the information to ListView that was obtained from 
			 * the arraylist.
			 */
			final ListView lv1 = (ListView) findViewById(R.id.ListView01);
			lv1.setAdapter(new CustomBaseAdapter(this, arrayResults));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			

		}
	}
/*
 * adding field names for each bit of information. (i.e. Price: $)
 */
	private String stringChange(String type, String database) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(type);
		sb.append(database);
		return sb.toString();
	}
}
