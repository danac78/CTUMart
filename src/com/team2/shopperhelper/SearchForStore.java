package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @author Dana Haywood
 * @date 7/10/2012
 * @version 0.1.0
 * @IT482
 * @Karl Lloyd
 * @commented by:
 * 
 *       This Activity is designed to retrieve the information from the Edit Box on the
 *       screen and turn them into text values. Once that is complete, it will send
 *       the information to validate to ensure the values are correct (i.e. zip code is
 *       not going 8512 instead of 85120). After the validation is complete, it will
 *       pass those values over to ShowStore and start the StoreStore activity.
 *       
 */

public class SearchForStore extends Activity {
/*
 * declaring private variables to be used for this Activity.
 */
	private String zip;
	private String state;
	private String city;
	boolean checkValid;

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
		 * information in. Additionally, creating a new Bundle and Intent. 
		 * The bundle is what will hold information we need to pass, and declaring
		 * it as final for the onClickListener to be able to access it. Also
		 * declaring the next Intent (new Activity) so it can be called.
		 */
		final Bundle bundle = new Bundle();
		final Intent intent = new Intent(this, ShowStore.class);
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
		final EditText cityTXT = (EditText) findViewById(R.id.cityTXT);
		final EditText stateTXT = (EditText) findViewById(R.id.stateTXT);
		final EditText zipTXT = (EditText) findViewById(R.id.zipTXTa);

		/*
		 * Upon the Search Button being pressed, this will collect the
		 * information from the tabs and put them into strings that can be
		 * validated and sent to the parser.
		 */

		search.setOnClickListener(new View.OnClickListener() {
			/*
			 * Creates a private version of this string for the onclick method
			 */
			private String city;
			private String state;
			private String zip;

			@Override
			public void onClick(View v) {
				/*
				 * gainValues() is designed to obtain the values from EditText
				 * and place them into the String variables declared above.
				 * Validate() is being called to ensure these values are in the
				 * correct format to be checked against the XML.
				 */
				gainValues();
				validate();

				/*
				 * If the validation process passed, the QueryXML(raw) method
				 * shall begin. The multiple catches are the result of numerous
				 * issues that can occur when reading and writing information.
				 */
				if (checkValid = true) {
					
					/*
					 * putting the city into a bundle in order to pass the information to 
					 * the next activity.
					 */
					bundle.putString("city", city);
					bundle.putString("state", state);
					bundle.putString("zip", zip);

					intent.putExtras(bundle);
					startActivity(intent);
				}

			}

			/*
			 * Validate() is checking the values of city, state and zip code.
			 */

			private void validate() {

				/*
				 * If the string text length is greater than zero (and honestly,
				 * this was the only way it would work for some reason), it will
				 * check the values. The first two are checking to see if they
				 * contain a number. the third is checking to see if State is
				 * greater than 2. Zip is ensuring the zip code has five
				 * numbers.
				 */
				if (city.length() > 0) {
					for (char c : city.toCharArray()) {
						if (Character.isDigit(c)) {
							cityTXT.setText(R.string.cityValueInvalid);
							checkValid = false;

						}
					}
				} else if (state.length() > 0) {
					for (char c : state.toCharArray()) {
						if (Character.isDigit(c)) {
							stateTXT.setText(R.string.no_numbers_for_states);
							checkValid = false;
						}
					}

					if (state.length() > 2) {
						stateTXT.setText(R.string.stateb);
						checkValid = false;
					}

				} else if (zip.length() > 0) {
					if (zip.length() < 5) {
						zipTXT.setText(R.string.zipCodeWrong);
						checkValid = false;
					}
				}

			}

			private void gainValues() {
				/*
				 * converting the Edit Text to actual strings.
				 */
				city = cityTXT.getText().toString();
				state = stateTXT.getText().toString();
				zip = zipTXT.getText().toString();

			}
		});
		/*
		 * Once the clear button is clicked, it will clear the information out
		 * of the text fields.
		 */
		clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cityTXT.setText(null);
				stateTXT.setText(null);
				zipTXT.setText(null);

			}
		});

	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}