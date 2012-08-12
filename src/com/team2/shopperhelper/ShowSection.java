package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * @author Dana Haywood
 * @version 0.5.2
 * @since 8/10/2012
 * <br>
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite: http://developer.android.com/index.html
 * 
 *         This activity is simply calling the map that from the value grabbed
 *         from the database. It will display the correct map from drawable.
 * 
 */
public class ShowSection extends Activity {

	private static final String PREF_NAME = "shopPref";
	
	
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showmap);
		
		/*
		 * Declaring two intents: One is to return to ShowProduct and
		 * one is to show the aisle
		 */
		final Intent aisleIntent = new Intent(this,ShowAisle.class);
		final Intent backIntent = new Intent(this,ShowProduct.class);
		ImageButton back = (ImageButton) findViewById(R.id.mapBackBtn);
		SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
		ImageView image = (ImageView) findViewById(R.id.mapView);
		image.setImageResource(Integer.valueOf(settings.getString("sectionMap", null)));
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
