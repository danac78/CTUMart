package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.team2.shopperhelper.library.DialogBox;

/**
 * @author Dana Haywood
 * @date 7/12/2012
 * @version 0.5.2
 * @IT482
 * @Karl Lloyd
 * @Source Cite http://developer.android.com/guide/components/index.html
 * 
 * 
 *         This activity is set to retrieve customer information from the user
 *         interface. Once this is done, it will go through a validation
 *         process, and get passed onto the web parsing and listing process.
 * 
 *         This activity is set to retrieve customer information from the user
 *         interface. Once this is done, it will go through a validation
 *         process, and get passed onto the web parsing and listing process...
 * 
 * 
 */
public class SearchProduct extends Activity {

	public static final String PREF_NAME = "shopPref";

	/*
	 * Creating local variables to be used in ShowProducts
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);
		/*
		 * Creating instances of Edit Text and Image Buttons to manipulate.
		 * 
		 * productTXT is for collecting the Product Name. UPCTXT is set to only
		 * accept numbers, so only the UPC. productType is a spinner that will
		 * have the different productType searchType will tell the application
		 * which one is going to be used.
		 * 
		 * this activity has three buttons: search, info, and clear.
		 * 
		 * intent is setting up information for the next activity.
		 */
		final EditText productTXT = (EditText) findViewById(R.id.productTXT);
		final EditText UPCTXT = (EditText) findViewById(R.id.UPCTXT);
		final Spinner productType = (Spinner) findViewById(R.id.productTypes);
		final Spinner searchType = (Spinner) findViewById(R.id.typeID);
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton info = (ImageButton) findViewById(R.id.searchProductHelp);
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
		final ImageButton back = (ImageButton) findViewById(R.id.productBack);
		final Intent intent = new Intent(this, ShowProduct.class);
		final Intent previous = new Intent(this,SearchForStore.class);

		/*
		 * This sequence is listening to see if the info button (?) was pressed.
		 * If it was, it is going to trigger the dialog box, which will display
		 * the help menu.
		 */
		info.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*
				 * Calling the DialogBox in Library to show the help for this
				 * activity.
				 */
				DialogBox dialog = new DialogBox();

				dialog.postDialog(SearchProduct.this, "Product Search Help",
						R.string.helpProduct);

			}
		});
		search.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final int position = searchType.getSelectedItemPosition();
				/*
				 * Based on the position of typeSearch, it will decide the query
				 * type and value to send to the web. It will also do a
				 * validation with If/Else statements prior to assigning the
				 * value.
				 */

				switch (position) {
				case 0:

					if (productTXT.getText().toString().length() == 0) {
						productTXT.setError("Enter a Product Name");
					} else {
						querySave("productName", productTXT.getText()
								.toString());
					}
					break;
				case 1:
					querySave("productType", productType.getSelectedItem()
							.toString());

					break;
				case 2:
					if (UPCTXT.getText().toString().length() == 0) {
						UPCTXT.setError("Enter a UPC");
					} else if (productTXT.getText().toString().length() != 12) {
						UPCTXT.setError("UPC is 12 Digits");

					} else {

						querySave("UPC", UPCTXT.getText().toString());
					}

				}

			}

			/*
			 * Instead of writing the code three different times, creating a
			 * method to accomplish all of this. The fields It will write the
			 * values passed into the preferences to open in
			 */
			private void querySave(String valuea, String valueb) {
				String fielda = "queryType";
				String fieldb = "queryValue";

				SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(fielda, valuea);
				editor.putString(fieldb, valueb);
				editor.commit();
				UPCTXT.setVisibility(View.GONE);
				productTXT.setVisibility(View.VISIBLE);
				productType.setVisibility(View.GONE);
				startActivity(intent);
				finish();

			}

		});

		/*
		 * resets the screen.
		 */
		clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				productTXT.setText(null);
				UPCTXT.setText(null);
				searchType.setSelection(0);

			}
		});

		//
		// /*
		// * This will change the view between productTXT,productType, and
		// UPCTXT.
		// */
		searchType.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				switch (position) {
				case 0:
					productTXT.setVisibility(View.VISIBLE);
					productType.setVisibility(View.GONE);
					UPCTXT.setVisibility(View.GONE);
					break;
				case 1:
					productTXT.setVisibility(View.GONE);
					productType.setVisibility(View.VISIBLE);
					UPCTXT.setVisibility(View.GONE);
					break;
				case 2:
					productTXT.setVisibility(View.GONE);
					productType.setVisibility(View.GONE);
					UPCTXT.setVisibility(View.VISIBLE);
					break;
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		/*
		 * Returning to Search For Store.
		 */
		back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(previous);
				finish();
				
			}
		}); 
		
	}
	
}