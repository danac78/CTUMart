package com.team2.shopperhelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.team2.shopperhelper.library.DialogBox;

/**
 * From a drop down box, the user will be able to select the store.
 * 
 * @author Dana Haywood
 * @since 7/10/2012
 * @version 0.5.2<br>
 *          Instructor: Karl Lloyd<br>
 *          Class: IT482<br>
 *          University: Colorado Technical University<br>
 *          Source Cite:http://developer.android.com/guide/components/index.html<br>
 * 
 *          <p>
 *          This activity will take the spinner information containing the store
 *          and store it in storeID to be placed in Internal Store. It shall use
 *          a +1 since position starts at 0. This value will then be used in
 *          ShowProducts to filter and items not in that store. 
 */

public class SearchForStore extends Activity {
	/**
	 * Setting up the Preference name to allow us to store the Store ID into
	 * internal memory.
	 */
	public static final String PREF_NAME = "shopPref";
	/**
	 * This is declaring the next activity we shall proceed to after this. In
	 * this case, it is the SearchProduct.class.
	 */
	private Intent intent;
	/**
	 * This is associating the class with the Search Image button in the UI.
	 * This will save the information into internal memory and continue 
	 * with the search.
	 */
	private ImageButton search;
	/**
	 * This is associating with the question mark image button in the UI. This
	 * will call for the help information.
	 */
	private ImageButton info;

	/**
	 * This is grabbing the settings for the Internal Storage to allow to
	 * read/write.
	 */
	private SharedPreferences settings;
	/**
	 * This will associate the spinner (dropdown) with the java.
	 */
	private Spinner locationTXT;
	
	protected DialogBox dialogS;

	/**
	 * Creating the Java logic for Search for Store.
	 * 
	 */
	@SuppressLint("CommitPrefEdits")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * calling the searchstore layout to have it display that screen.
		 */
		setContentView(R.layout.searchstore);
		new Intent(this, ShowProduct.class);
		intent = new Intent(this, SearchProduct.class);
		search = (ImageButton) findViewById(R.id.search);
		info = (ImageButton) findViewById(R.id.helpStoreBTN);
		settings = getSharedPreferences(PREF_NAME, 0);
		locationTXT = (Spinner) findViewById(R.id.locationTXT);
		


		/*
		 * Upon the Search Button being pressed, this will collect the
		 * information from the spinner and obtain the id.
		 */
		search.setOnClickListener(new View.OnClickListener() {
			/*
			 * declaring the storeID variable as that will work with getter and
			 * setters to push the information through.
			 */

			public void onClick(View v) {
				/*
				 * getting the location based on the position of the spinner.
				 * The store id will be: 1. Denver, CO, 2. Beverly Hill, CA, 3.
				 * Boise ID, 4. Chicago, IL, 5. Colorado Springs, CO, 6.
				 * Springfield, MA 7. Portland, OR, 8. Richland, WA . Adding a
				 * plus 1 since position starts at zero
				 */
				prefWrite("storeID", Integer.toString((locationTXT
						.getSelectedItemPosition()) + 1));

				/*
				 * starting the Search Product activity
				 */
				startActivity(intent);
				finish();

			}
		}

		);
		/*
		 * The Info button shall display information. It will call the DialogBox
		 * class from the library with the pertinent information.
		 */

		info.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				dialogS = new DialogBox();

				dialogS.postDialog(SearchForStore.this, "Search Store Help",
						R.string.search_store_help);

			}
		});

	}

	/**
	 * Saving the preferences.
	 */
	protected void prefWrite(String field, String value) {
		settings.edit().putString("storeID", value);
		settings.edit().commit();

	}
}
