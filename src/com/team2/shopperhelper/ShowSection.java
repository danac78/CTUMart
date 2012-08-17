package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**Showing the Section Map for the Store.
 * @author Dana Haywood
 * @version 0.5.2
 * @since 8/10/2012 <br>
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite: http://developer.android.com/index.html
 * 
 *        This activity is simply calling the map that from the value grabbed
 *        from the database. It will display the correct map from drawable.
 * 
 */
public class ShowSection extends Activity {
	/**
	 * Constant to contain the value of the shopPref preference file name.
	 */
	private static final String PREF_NAME = "shopPref";
	/**
	 * The intent set up to go look at the Aisle.
	 */
	private Intent aisleIntent;
	/**
	 * This will be setup to take yo back to ShowProduct.class.
	 */
	private Intent backIntent;
	/**
	 * The back button will activate the intent.
	 */
	private ImageButton back;
	/**
	 * Settings to pull the Section information so we can display the picture.
	 */
	private SharedPreferences settings;
	/**
	 * the image view to display the image.
	 */
	private ImageView image;

	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showmap);
		
		aisleIntent = new Intent(this, ShowAisle.class);
		backIntent = new Intent(this, ShowProduct.class);
		back = (ImageButton) findViewById(R.id.mapBackBtn);
		settings = getSharedPreferences(PREF_NAME, 0);
		image = (ImageView) findViewById(R.id.mapView);

		/**
		 * Acquiring the sectionMap value from preference and assigning it to
		 * imageview
		 */
		image.setImageResource(Integer.valueOf(settings.getString("sectionMap",
				null)));
		/**
		 * Allows it to be clicked on and pushed into Aisle.
		 */
		image.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(aisleIntent);
				finish();

			}
		});

		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(backIntent);
				finish();

			}
		});

	}

}
