package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Showing the Aisle Map
 * 
 * @author Dana Haywood
 * @version 0.5.2
 * @since 8/10/2012 Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite: http://developer.android.com/index.html</b>
 * 
 *        This activity is simply calling the map that from the value grabbed
 *        from the database. It will display the correct map from drawable.
 * 
 */
public class ShowAisle extends Activity {
	/**
	 * Storing the preference file name.
	 */
	private static final String PREF_NAME = "shopPref";
	/**
	 * Setting up an intent to go back to ShowProduct
	 */
	private Intent intent;
	/**
	 * The button that will use the Intent to send back to ShowProduct
	 */
	private ImageButton back;
	/**
	 * The imageview that will display the aisle image.
	 */
	private ImageView image;
	/**
	 * The settings for SharedPreferences used to grab the value for showing the
	 * correct Aisle Map.
	 */
	private SharedPreferences settings;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showmap);
		intent = new Intent(this, ShowProduct.class);
		back = (ImageButton) findViewById(R.id.mapBackBtn);
		image = (ImageView) findViewById(R.id.mapView);
		settings = getSharedPreferences(PREF_NAME, 0);

		/*
		 * Grabbing aisle map and putting it on the screen.
		 */

		image.setImageResource(Integer.valueOf(settings.getString("aisleMap",
				null)));

		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(intent);
				finish();

			}
		});

	}

}
