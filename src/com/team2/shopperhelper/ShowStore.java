package com.team2.shopperhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author Dana Haywood
 * @version 0.1.0
 * @IT482
 * @Karl Lloyd
 * @Source Cite: <DO NOT REMOVE>
 *         http://geekswithblogs.net/bosuch/archive/2011/01
 *         /31/android---create-a- custom-multi-line-listview-bound-to-an.aspx
 * 
 *         After having the values passed to this activity from SearchForStore,
 *         it will retrieve the values passed from bundle and put them through
 *         an Array List. Although this is not pulling from an XML file or a
 *         SQLite database, it was clearly the only way after researching to get
 *         it to do what was needed and not cause a force closed error. The
 *         problem with the other methods that were tried was that I kept
 *         getting a nullpointexception, despite programming it based on
 *         examples. This activity relies on a few helper classes for it to
 *         list, but it will list the store and obtain the store id..,
 * 
 * @Commented By:
 */

public class ShowStore extends Activity {
	/*
	 * declaring the intent earlier.
	 */
	Intent intent;

	/*
	 * Creating strings that will hold the values passed over by bundle.
	 * Additionally, creating a new array list that will go based on the POJO
	 * SearchResults. SearchResults will also instantiate as sr1 to create an
	 * array object.
	 */
	private String city;
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	private String state;
	private String zip;
	ArrayList<StoreResults> results = new ArrayList<StoreResults>();
	StoreResults sr1 = new StoreResults();

	/**
	 * 
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readdb);
		intent = new Intent(this, SearchProduct.class);

		/*
		 * Getting the information passed from SearchForStore activity and
		 * clearing the bundle for memory purposes.
		 */
		Bundle bundle = getIntent().getExtras();
		String XMLcity = bundle.getString("city");
		String XMLstate = bundle.getString("state");
		String XMLzip = bundle.getString("zip");
		bundle.clear();

		/*
		 * Assigning the values taken from the bundle to the Strings declared
		 * for this class.
		 */
		setCity(XMLcity);
		setState(XMLstate);
		setZip(XMLzip);
		

		/*
		 * setting up the ArrayList with information from the
		 * GetSearchResults(). Although this hardcoding information into the
		 * program itself, it was the option to get it to work without crashing.
		 * Despite numerous research hours spent, I could not get past the
		 * nullpointerexception, and was trying to get a prototype ready by
		 * friday 7/20/2012..
		 */

		ArrayList<StoreResults> storeResults = GetSearchResults();
		/*
		 * declaring the ListView item that the information will be set to.
		 */
		final ListView listView = (ListView) findViewById(R.id.dbView);

		/*
		 * Creating the adapter and the onItemClickListener. The Adapter serves
		 * as a way to pull information from an ArrayList and sends it to a list
		 * id. In this case, we are sending it to ListView id dbView.
		 */

		listView.setAdapter(new StoreCustomBaseAdapter(this, storeResults));

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				/*
				 * Once an item is clicked, it will call this method. The Object
				 * is pulling all the information that ListView at the position
				 * that was tapped. FullObjects is using SearchResults to format
				 * the information into store, address, and secondAddress. The
				 * method then creates a string storeID that will retrieve the
				 * storeID from the object that will be used in the Product
				 * Database search.
				 */
				Object o = listView.getItemAtPosition(position);
				StoreResults fullObjects = (StoreResults) o;
				String storeID = fullObjects.getStore();
				/*
				 * Creating a new bundle to hold the storeID and adding it to
				 * the intent. Starting the ProductSearch activity.
				 */
				Bundle bundle = new Bundle();
				bundle.putString("storeID", storeID);
				intent.putExtras(bundle);
				startActivity(intent);

			}

		});

	}

	private ArrayList<StoreResults> GetSearchResults() {
		/*
		 * Using the XOR operator (^), it is checking to see if city, state, or
		 * zip contain a certain piece of information. Since certain cities can
		 * have multiple zip codes, added one to check for zipcode. It is using
		 * sr1 to set the information to an object, and then places that in the
		 * ArrayList. It will collect all the information and return in. More
		 * values may be placed in after the prototype is working the way it
		 * should.
		 */
		if ((city.contentEquals("ApacheJunction"))
				^ (state.contentEquals("AZ")) ^ (zip.contentEquals("85120"))) {
			if (zip.contentEquals("85120")) {
				sr1.setStore("1");
				sr1.setAddress("2707 W 14th Pl");
				sr1.setSecondAddress(" ");
				results.add(sr1);
			} else {
				sr1.setStore("1");
				sr1.setAddress("2707 W 14th Pl");
				sr1.setSecondAddress(" ");
				results.add(sr1);
			}

		} else if ((city.contentEquals("Beverly Hills"))
				^ (state.contentEquals("CA")) ^ (zip.contentEquals("90210"))) {
			if (zip.contentEquals("90210")) {
				sr1.setStore("2");
				sr1.setAddress("135 Cupertino Way");
				sr1.setSecondAddress("Suite 1");
				results.add(sr1);
			} else {
				sr1.setStore("2");
				sr1.setAddress("135 Cupertino Way");
				sr1.setSecondAddress("Suite 1");
				results.add(sr1);
			}
		} else if ((city.contentEquals("Springfield"))
				^ (state.contentEquals("MA")) ^ (zip.contentEquals("01109"))) {
			if (zip.contentEquals("01109")) {
				sr1.setStore("3");
				sr1.setAddress("275 Breckwood Blvd");
				sr1.setSecondAddress("");
			} else {
				sr1.setStore("3");
				sr1.setAddress("275 Breckwood Blvd");
				sr1.setSecondAddress("");
			}
		}

		return results;
	}

}
