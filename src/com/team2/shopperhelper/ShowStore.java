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
 * @Source cite: http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
 */

public class ShowStore extends Activity {

	Intent intent;

	
	private String city;
	private String state;
	private String zip;
	ArrayList<SearchResults> results = new ArrayList<SearchResults>();
	SearchResults sr1 = new SearchResults();

	/**
	 * 
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readdb);
		intent = new Intent(this, SearchProduct.class);

		/*
		 * Getting the information passed from SearchForStore activity.
		 */
		Bundle bundle = getIntent().getExtras();
		String XMLcity = bundle.getString("city");
		String XMLstate = bundle.getString("state");
		String XMLzip = bundle.getString("zip");
		bundle.clear();
		this.city = XMLcity;
		this.state = XMLstate;
		this.zip = XMLzip;

		ArrayList<SearchResults> searchResults = GetSearchResults();
		/*
		 * setting up the InputStream with the xml stored in resources.
		 */
		final ListView listView = (ListView) findViewById(R.id.dbView);

		listView.setAdapter(new StoreCustomBaseAdapter(this, searchResults));

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o = listView.getItemAtPosition(position);
				SearchResults fullObjects = (SearchResults) o;
				String storeID = fullObjects.getStore();

				Bundle bundle = new Bundle();
				bundle.putString("storeID", storeID);
				intent.putExtras(bundle);
				startActivity(intent);

			}

		});

	}

	private ArrayList<SearchResults> GetSearchResults() {
		
		if((city.contentEquals("ApacheJunction"))
				^ (state.contentEquals("AZ")) 
				^ (zip.contentEquals("85120")))
		{
			if (zip.contentEquals("85120")) {
				sr1.setStore("1");
				sr1.setAddress("2707 W 14th Pl");
				sr1.setSecondAddress(" ");
				results.add(sr1);
		} else 
		{
				sr1.setStore("1");
				sr1.setAddress("2707 W 14th Pl");
				sr1.setSecondAddress(" ");
				results.add(sr1);
		}
	
		
		} else if ((city.contentEquals("Beverly Hills"))
				^ (state.contentEquals("CA"))
				^ (zip.contentEquals("90210")))
		{
			if(zip.contentEquals("90210"))
			{
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
				^ (state.contentEquals("MA"))
				^ (zip.contentEquals("01109")))
		{
			if(zip.contentEquals("01109"))
			{
				sr1.setStore("3");
				sr1.setAddress("275 Breckwood Blvd");
				sr1.setSecondAddress("");
			} else 
			{
				sr1.setStore("3");
				sr1.setAddress("275 Breckwood Blvd");
				sr1.setSecondAddress("");
			}
		}

		return results;
	}

	

}
