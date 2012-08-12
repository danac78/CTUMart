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

	private static final String PREF_NAME = "shopPref";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showmap);
		final Intent intent = new Intent(this, ShowProduct.class);
		ImageButton back = (ImageButton) findViewById(R.id.mapBackBtn);
		/*
		 * Setting up the imageview to set an image onto the screen
		 */
		ImageView image = (ImageView) findViewById(R.id.mapView);
		/*
		 * Setting up sharedpreferences to be able to read values from internal
		 * storage.
		 */
		SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);

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
