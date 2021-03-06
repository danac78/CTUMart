package com.team2.shopperhelper;

import java.io.IOException;
import java.io.InputStream;

import com.team2.shopperhelper.library.DialogBox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Showing the Section Map for the Store.
 * 
 * @author Dana Haywood
 * @version 1.0.0
 * @since 9/10/2012 <br>
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite: http://developer.android.com/index.html
 * 
 *        This activity is simply calling the map that from the value grabbed
 *        from the database. It will display the correct map from assets.
 * 
 */
public class ShowSection extends Activity {
	/**
	 * Constant to contain the value of the shopPref preference file name.
	 */
	private static final String PREF_NAME = "shopPref";
	/**
	 * Setting it up so the sections folder is a constant to avoid hardcoded
	 * values in the statements below.
	 */
	private static final String SECTIONS = "/sections/";
	/**
	 * The intent set up to go look at the Aisle.
	 */
	private Intent aisleIntent;
	/**
	 * This will be setup to take you back to ShowProduct.class.
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
	/**
	 * Use to build the file name to pull from Assets.
	 */
	private String fileName;
	/**
	 * opening a stream to read the file into memory.
	 */
	private InputStream stream;
	/**
	 * converting the file from a stream into a bitmap to display.
	 */
	private Bitmap sectionBitmap;
	/**
	 * The button for the help menu.
	 */
	private ImageButton info;
	/**
	 * The dialog box that will pop up when the info is pressed.
	 */
	protected DialogBox dialogBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showmap);

		aisleIntent = new Intent(this, ShowAisle.class);
		backIntent = new Intent(this, ShowProduct.class);
		back = (ImageButton) findViewById(R.id.mapBackBtn);
		settings = getSharedPreferences(PREF_NAME, 0);
		image = (ImageView) findViewById(R.id.mapView);
		info = (ImageButton) findViewById(R.id.helpBtnMap);

		/**
		 * Creating the file location based on Store ID and Section name. This
		 * will allow for it to check the correct location. This was change from
		 * using res because it depended on R.Java NOT changing, which is
		 * impossible to predict. Using a switch to minimize the amount of
		 * repetitive maps. If there is a map different than default, it will be
		 * listed as a case. However, it will use the default if it the same as
		 * Denver.
		 */

		switch (Integer.parseInt(settings.getString("storeID", null))) {

		default:
			fileName = "1" + SECTIONS
					+ settings.getString("section", null).toLowerCase()
					+ ".png";
			break;
		}

		try {
			stream = getAssets().open(fileName);
			sectionBitmap = BitmapFactory.decodeStream(stream);
			image.setImageBitmap(sectionBitmap);

		} catch (IOException e1) {
			Log.e("IO:", e1.toString());
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				Log.e("IO:", e.toString());
			}
		}

		/**
		 * Allows it to be clicked on and pushed into Aisle.
		 */
		image.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(aisleIntent);
				finish();

			}
		});
		/**
		 * Being sent back to ShowProduct as well as assigning
		 * the true flag stating to ShowProduct it is from the map,
		 * therefore will attempt to load the results again rather 
		 * than call the database.
		 */
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				settings.edit().putBoolean("mapReturnFrom", true);
				settings.edit().commit();
				startActivity(backIntent);
				finish();

			}
		});
		/**
		 * Displaying the help section for the Show Section screen.
		 */
		info.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				dialogBox = new DialogBox();
				dialogBox.postDialog(ShowSection.this, "Show Section Help",
						R.string.Show_Map_Section);

			}
		});

	}

}
