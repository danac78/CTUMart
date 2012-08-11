package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author Dana Haywood
 * @version 0.5.2
 * @date 8/10/2012
 * @Karl Lloyd
 * @IT482
 * @Source Cite: http://developer.android.com/index.html
 * 
 *         This activity is simply calling the map that from the value grabbed
 *         from the database. It will display the correct map from drawable.
 * 
 */
public class ShowAisle extends Activity {

	private static final String PREF_NAME = "shopPref";
	private int drawable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.showmap);
		final Intent intent = new Intent(this,ShowProduct.class);
	//	Button back = (Button) findViewById(R.id.mapBTN);
		/*
		 * Setting up the imageview to set an image onto the screen
		 */
	//	ImageView image = (ImageView) findViewById(R.id.mapView);
		/*
		 * Setting up sharedpreferences to be able to read values from internal
		 * storage.
		 */
		SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);

		/*
		 * Grabbing aisle map and putting it on the screen.
		 */
		
		drawable = Integer.valueOf(settings.getString("aisleMap", null));
	//	image.setImageResource(drawable);
		
	/*	back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(intent);
				finish();
				
			}
		});*/
		

	}

}
