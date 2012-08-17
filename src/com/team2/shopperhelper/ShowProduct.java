package com.team2.shopperhelper;

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
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
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
	/**
	 * Using PREF_NAME to get the values back.
	 */
	public static final String PREF_NAME = "shopPref";

	/**
	 * Creating a jsonObject to contain the items received from JSONParser.
	 */
	private JSONObject productInfo;
	/**
	 * Creating an Array from productInfo.
	 */
	private JSONArray listObjects;
	/**
	 * A constant that will be the URL that will be used for gathering the
	 * information.
	 */
	private static final String URL = "http://darkenvisuals.com/android/";
	/**
	 * Used to store the section map values from the JSON parse.
	 */
	ArrayList<String> getSection = new ArrayList<String>();
	/**
	 * Used to store the aisle map values from the JSON parse.
	 */
	ArrayList<String> getAisle = new ArrayList<String>();
	/**
	 * Containing the items taken from the listObjects JSONArray and turning it
	 * into a list item.
	 */
	ArrayList<SearchResults> arrayResults = new ArrayList<SearchResults>();
	/**
	 * This will create the connection to JSONParser.
	 */
	private JSONParser jsonParser;
	/**
	 * Creating the setting to acquire the storeID, queryType, and queryValue to
	 * pass onto the JSONParser.
	 */
	private SharedPreferences setting;

	private ImageButton backButton;

	private Intent prevIntent;
	/**
	 * * intent shall be used to get us to the next activity, which will show
	 * the section map.
	 */
	private Intent intent;
	/**
	 * ListView where the custom rows will be inserted into.
	 */
	private ListView lv1;
	/**
	 * SearchResult will create a string array to place into an arraylist.
	 */
	private SearchResults pr1;
	/**
	 * Configuring the settings in protected mode to move into the listener.
	 * This will save the information for the Section and Aisle map.
	 */
	protected SharedPreferences settings;
	/**
	 * Saves the Information for the section and aisle map.
	 */
	protected Editor edit;
	/**
	 * parms : nameValuePairs will contain the information required to do the
	 * search function.
	 */
	private ArrayList<NameValuePair> parms;

	// private static final String url =
	// "http://http://www.fuelradio.fm/ctumart/android.php";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readweb);

		jsonParser = new JSONParser();
		setting = getSharedPreferences(PREF_NAME, 0);
		backButton = (ImageButton) findViewById(R.id.showProductBackBtn);
		prevIntent = new Intent(this, SearchProduct.class);
		intent = new Intent(this, ShowSection.class);
		lv1 = (ListView) findViewById(R.id.ListView01);
		/*
		 * This is where the android php page that be responsible for populating
		 * this page. The first thing we need to do is create name value pairs
		 * that will hold the values we are passing to the web page.
		 */

		parms = new ArrayList<NameValuePair>();

		/*
		 * Adding the parameters that will be required to do the search.
		 */
		addParms("storeID", parms, setting);
		addParms("queryType", parms, setting);
		addParms("queryValue", parms, setting);

		/*
		 * Calling the JSONParser to get the JSONObject with the results from
		 * the website.
		 */
		productInfo = jsonParser.getJSONInformation(parms, URL);
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
			pr1 = new SearchResults();
			try {
				listObjects = productInfo.getJSONArray("productlist");
			} catch (JSONException e1) {
				Log.e("JSON:", e1.toString());
			}
			for (int i = 0; i < listObjects.length(); i++) {
				arrayResults = getListProducts(listObjects, arrayResults, pr1,
						i);
				try {
					getSection = getSectionFromJson(listObjects, i);
				} catch (JSONException e) {
					Log.e("JSON:", e.toString());
				}
				try {
					getAisle = getAisleFromJson(listObjects, i);
				} catch (JSONException e) {
					Log.e("JSON:", e.toString());
				}
				pr1 = new SearchResults();
			}

		}
		/**
		 * Sending the results to the ListView.
		 */
		lv1.setAdapter(new CustomBaseAdapter(this, arrayResults));
		lv1.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg, View view,
					int position, long id) {

				settings = getSharedPreferences(PREF_NAME, 0);
				edit = settings.edit();
				/*
				 * Saving the Map reference as it is in Android R.Java so we can
				 * load the images up easily. We are saving them into internal
				 * store.
				 */
				edit.putString("sectionMap", getSection.get(position));

				edit.putString("aisleMap", getAisle.get(position));
				edit.commit();
				startActivity(intent);
				finish();

			}

		});

		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				startActivity(prevIntent);
				finish();

			}
		});
	}

	/**
	 * Retrieving the Aisle map from the JSON Array
	 * 
	 * @param listObjects2
	 *            JSON Array holding the information.
	 * @param i
	 *            The iteration the procedure call is on.
	 * @return Sending array back.
	 * @throws JSONException
	 *             In case there is a problem. Technically, there should not be
	 *             a problem because we catch this prior to the loop with
	 *             if/else statement. If the productlist is null, it gives a
	 *             dialog.
	 */
	private ArrayList<String> getAisleFromJson(JSONArray listObjects2, int i)
			throws JSONException {
		getAisle.add(getListObject("aisleMap", listObjects, i));
		return getAisle;
	}

	/**
	 * Getting the Section Map for the Array
	 * 
	 * @param listObjects2
	 *            The JSON Array we are pulling the information from
	 * @param i
	 *            The iteration the procedure call is on.
	 * @return returning the getSection arraylist.
	 * @throws JSONException
	 *             Not necessary, but done because Java will have a fit.
	 */
	private ArrayList<String> getSectionFromJson(JSONArray listObjects2, int i)
			throws JSONException {
		getSection.add(getListObject("sectionMap", listObjects, i));
		return getSection;
	}

	/**
	 * Pulling information from the JSON Array, and putting them into holders.
	 * They will then be pushed into an array that is following the format of
	 * SearchResults of the Shopper Helper Library. Additionally, adding the
	 * maps values to an array.
	 * 
	 * @param listObjects
	 *            The JSON Array carrying the information
	 * @param arrayResults
	 *            The results that will be feed to the screen.
	 * @param pr1
	 *            The format the screen will done in.
	 * @param i
	 *            The iteration the procedure call is on.
	 * @return returning the arraylist.
	 */
	private ArrayList<SearchResults> getListProducts(JSONArray listObjects,
			ArrayList<SearchResults> arrayResults, SearchResults pr1, int i) {

		try {

			pr1.setName(getListObject("productName", listObjects, i));

			pr1.setPrice(stringChange("Price: $",
					getListObject("price", listObjects, i)));

			pr1.setInventoryCount(stringChange("Inventory: ",
					getListObject("inventoryCount", listObjects, i)));

			pr1.setSections(getListObject("Sections", listObjects, i));

			pr1.setAisle(getListObject("Aisle", listObjects, i));

			arrayResults.add(pr1);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return arrayResults;
	}

	/**
	 * Obtaining the item from ListObject to be placed into an ArrayList.
	 * 
	 * @param value
	 *            The name of the value we are looking for (i.e. inventoryCount)
	 * @param listObjects2
	 *            The JSONArray that we are searching.
	 * @param i
	 *            The iteration that the procedure call is on.
	 * @return Returning the value to be placed in the list.
	 * @throws JSONException
	 */
	private String getListObject(String value, JSONArray listObjects, int i)
			throws JSONException {

		return listObjects.getJSONObject(i).getString(value).toString();

	}

	/**
	 * Reducing the amount of times this array is typed out
	 * 
	 * @param value
	 *            The type of value the item is.
	 * @param parms
	 *            The parms array that we are adding to.
	 * @param setting
	 *            The Preference setting we are using to get the value.
	 */
	private void addParms(String value, List<NameValuePair> parms,
			SharedPreferences setting) {

		parms.add(new BasicNameValuePair(value, setting.getString(value, null)
				.toString()));
	}

	/**
	 * Creating a string for the show product screen so it can show price and
	 * inventory on the screen.
	 * 
	 * @param type
	 *            Adding the type of item that it will be shown as (i.e. Price:
	 *            $)
	 * @param value
	 *            The value that will be the suffix of the type.
	 * @return returns the combined string.
	 */
	private String stringChange(String type, String value) {
		StringBuilder sb = new StringBuilder();

		sb.append(type);
		sb.append(value);
		return sb.toString();
	}

}
