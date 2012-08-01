package com.team2.shopperhelper;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author Dana Haywood
 * @date 7/10/2012
 * @version 0.1.5
 * @IT482
 * @Karl Lloyd
 * @Source Cite: http://developer.android.com/guide/components/index.html
 * @commented by:
 * 
 *            This activity will take the spinner input from the screen, and put
 *            it into a switch. Based on the position of the spinner, it will
 *            indicate the store id number (we are only using 6 for academic
 *            reasons). No validation will be needed as the spinner will only
 *            list the stores available.
 */

public class SearchForStore extends Activity {
	/*
	 * declaring private variables to be used for this Activity.
	 */
	public static final String PREF_NAME = "shopPref";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * calling the searchstore layout to have it display that screen.
		 */
		setContentView(R.layout.searchstore);

		/*
		 * Creating the image buttons and texts inside java to manipulate.
		 * Additionally, creating an instance of validate to send the
		 * information in. Additionally, creating a new Bundle and Intent. The
		 * bundle is what will hold information we need to pass, and declaring
		 * it as final for the onClickListener to be able to access it. Also
		 * declaring the next Intent (new Activity) so it can be called.
		 */

		final Intent intent = new Intent(this, SearchProduct.class);
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton info = (ImageButton) findViewById(R.id.info);
		final TextView help = (TextView) findViewById(R.id.storeHelpTXT);

		/*
		 * Upon the Search Button being pressed, this will collect the
		 * information from the spinner and obtain the id.
		 */

		search.setOnClickListener(new View.OnClickListener() {
			/*
			 * declaring the storeID variable as that will work with getter and
			 * setters to push the information through.
			 */
			private String storeID;

			public void onClick(View v) {

				/*
				 * creates the spinner within Java
				 */
				Spinner locationTXT = (Spinner) findViewById(R.id.locationTXT);

				/*
				 * getting the location based on the position of the spinner.
				 * The store id will be: 1. Apache Junction, AZ 2. Beverly
				 * Hills, CA 3. Colorado Springs, CO 4. Denver, CO 5. Chicago,
				 * IL 6. Springfield, MA. Adding a plus 1 since position starts
				 * at zero
				 */

				storeID = Integer.toString((locationTXT.getSelectedItemPosition())+1);

				

				/*
				 * if for any reason the help text view is visible, this will
				 * ensure the XML is set to be invisible.
				 */
				help.setVisibility(View.GONE);

				/*
				 * Creating a preference file that will be called upon from each
				 * activity.
				 */
				SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("storeID", storeID);
				editor.commit();

				/*
				 * stating the Search Product activity
				 */
				startActivity(intent);

			}

		}

		);
		/*
		 * The Info button shall display information
		 */

		info.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				help.setVisibility(View.VISIBLE);

			}
		});

	}

}
