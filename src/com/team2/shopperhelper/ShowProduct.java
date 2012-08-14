package com.team2.shopperhelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.team2.shopperhelper.library.CustomBaseAdapter;
import com.team2.shopperhelper.library.DialogBox;
import com.team2.shopperhelper.library.JSONParser;
import com.team2.shopperhelper.library.SearchResults;

/**
 * Showing the Product in a List
 * 
 * @author Dana Haywood
 * @since 7/19/2012
 * @version 0.5.2 Instructor: Karl Lloyd<br>
 *          Class: IT482<br>
 *          University: Colorado Technical University<br>
 *          Source Cite:
 *          http://www.helloandroid.com/tutorials/connecting-mysql-database and
 *          http://geekswithblogs.net/bosuch/archive/2011/01/31/android---
 *          create-a-custom-multi-line-listview-bound-to-an.aspx
 * 
 *          The ShowProduct class will receive the information from the
 *          SearchProduct activity and attempt to pull the information from the
 *          PHP site. Once the information is received, it will post it into a
 *          custom list view. The list view will have customrow injected with
 *          text views to have a customized listing.
 * 
 */

public class ShowProduct extends Activity {
	/*
	 * Using PREF_NAME to get the values back.
	 */
	public static final String PREF_NAME = "shopPref";
	static InputStream is = null;
	private String results;
	private JSONObject jsonObject;
	private JSONObject productInfo;
	private JSONArray listObjects;
	private static final String url = "http://darkenvisuals.com/android/";

	// private static final String url =
	// "http://http://www.fuelradio.fm/ctumart/android.php";
	/**
	 * Parsing the web and showing the results. setting : Used to store
	 * information internally. queryType and queryValue should be seen like:
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
	 * 
	 * intent shall be used to get us to the next activity, which will show the
	 * section map.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readweb);

		JSONParser jsonParser = new JSONParser();
		SharedPreferences setting = getSharedPreferences(PREF_NAME, 0);
		final ImageButton backButton = (ImageButton) findViewById(R.id.showProductBackBtn);
		final Intent prevIntent = new Intent(this, SearchProduct.class);
		final Intent intent = new Intent(this, ShowSection.class);
		/*
		 * This is where the android php page that be responsible for populating
		 * this page. The first thing we need to do is create name value pairs
		 * that will hold the values we are passing to the web page.
		 */

		List<NameValuePair> parms = new ArrayList<NameValuePair>();

		/*
		 * Adding the parameters that will be required to do the search.
		 */
		parms.add(new BasicNameValuePair("queryType", setting.getString(
				"queryType", null).toString()));
		parms.add(new BasicNameValuePair("storeID", setting.getString(
				"storeID", null).toString()));
		parms.add(new BasicNameValuePair("queryValue", setting.getString(
				"queryValue", null).toString()));
		/*
		 * Calling the JSONParser to get the JSONObject with the results from
		 * the website.
		 */
		productInfo = jsonParser.getJSONInformation(parms, url);
		/*
		 * It is checking to see if productInfo is null at the productlist.
		 * Without this, trying to put this object into an array will cause an
		 * exception. Instead, we will put this object through a test. If it is
		 * null, it will produce a dialog box saying no products found. If not,
		 * it will produce the list.
		 */
		if (productInfo.isNull("productlist")) {

			DialogBox dialog = new DialogBox();
			dialog.postDialog(ShowProduct.this, "No Product Found",
					R.string.no_product_found);

		} else {

			/*
			 * Instantiating arrayResults that will be used to populate the list
			 */
			ArrayList<SearchResults> arrayResults = new ArrayList<SearchResults>();

			try {
				listObjects = productInfo.getJSONArray("productlist");
				final ArrayList<String> getSection = new ArrayList<String>();
				final ArrayList<String> getAisle = new ArrayList<String>();
				SearchResults pr1 = new SearchResults();
				/*
				 * Pulling information from the JSON Array, and putting them
				 * into holders. They will then be pushed into an array that is
				 * following the format of SearchResults of the Shopper Helper
				 * Library. Additionally, adding the maps values to an array.
				 */
				for (int i = 0; i < listObjects.length(); i++) {

					pr1.setName(listObjects.getJSONObject(i)
							.getString("productName").toString());

					pr1.setPrice(stringChange("Price: $", listObjects
							.getJSONObject(i).getString("price").toString()));

					pr1.setInventoryCount(stringChange(
							"Inventory: ",
							listObjects.getJSONObject(i)
									.getString("inventoryCount").toString()));

					pr1.setSections(listObjects.getJSONObject(i)
							.getString("Sections").toString());

					pr1.setAisle(listObjects.getJSONObject(i)
							.getString("Aisle").toString());

					listObjects.getJSONObject(i).getString("Aisle").toString();

					arrayResults.add(pr1);
					getSection.add(listObjects.getJSONObject(i).getString(
							"sectionMap"));
					getAisle.add(listObjects.getJSONObject(i).getString(
							"aisleMap"));

					pr1 = new SearchResults();
				}
				/*
				 * Posting the information to ListView that was obtained from
				 * the arraylist.
				 */
				final ListView lv1 = (ListView) findViewById(R.id.ListView01);
				lv1.setAdapter(new CustomBaseAdapter(this, arrayResults));

				lv1.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg, View view,
							int position, long id) {

						SharedPreferences settings = getSharedPreferences(
								PREF_NAME, 0);
						SharedPreferences.Editor edit = settings.edit();
						/*
						 * Saving the Map reference as it is in Android R.Java
						 * so we can load the images up easily. We are saving
						 * them into internal store.
						 */
						edit.putString("sectionMap", getSection.get(position));

						edit.putString("aisleMap", getAisle.get(position));
						edit.commit();
						startActivity(intent);
						finish();

					}

				});

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				startActivity(prevIntent);
				finish();

			}
		});
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

	
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}
