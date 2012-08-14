package com.team2.shopperhelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.team2.shopperhelper.library.DialogBox;

/**
 * Searching for a Product.
 * 
 * @author Dana Haywood
 * @version 0.5.2
 * @since 7/12/2012
 * 
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite:
 *        http://developer.android.com/guide/components/index.html</br>
 * 
 * 
 *        This activity is set to retrieve customer information from the user
 *        interface. Once this is done, it will go through a validation process,
 *        and get passed onto the web parsing and listing process.
 * 
 *        This activity is set to retrieve customer information from the user
 *        interface. Once this is done, it will go through a validation process,
 *        and get passed onto the web parsing and listing process...
 * 
 * 
 */
public class SearchProduct extends Activity {
	/**
	 * Setting up a value for Shared Preferences to store the query type and
	 * value.
	 */
	public static final String PREF_NAME = "shopPref";

	/**
	 * Creating instances of Edit Text and Image Buttons to manipulate.
	 * 
	 * productTXT is for collecting the Product Name. UPCTXT is set to only
	 * accept numbers, so only the UPC. productType is a spinner that will have
	 * the different productType searchType will tell the application which one
	 * is going to be used.
	 * 
	 * this activity has three buttons: search, info, and clear.
	 * 
	 * intent is setting up information for the next activity.
	 */
	@SuppressLint("CommitPrefEdits") @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);
		/*
		 *
		 */
		final TextView productLbl = (TextView) findViewById(R.id.productLbl);
		final TextView productTypeLbl = (TextView) findViewById(R.id.productTypeLbl);
		final EditText productTXT = (EditText) findViewById(R.id.productTXT);
		final EditText UPCTXT = (EditText) findViewById(R.id.UPCTXT);
		final Spinner productType = (Spinner) findViewById(R.id.productTypes);
		final Spinner searchType = (Spinner) findViewById(R.id.typeID);
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton info = (ImageButton) findViewById(R.id.searchProductHelp);
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
		final ImageButton back = (ImageButton) findViewById(R.id.productBack);
		final Intent intent = new Intent(this, ShowProduct.class);
		final Intent previous = new Intent(this, SearchForStore.class);
		final SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
		final SharedPreferences.Editor editor = settings.edit();
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
								.toString(),editor);
						newActivity(intent,productTXT,searchType,UPCTXT);
					}
					break;
				case 1:
					querySave("productType", productType.getSelectedItem()
							.toString(),editor);
					newActivity(intent,productTXT,searchType,UPCTXT);

					break;
				case 2:
					if (UPCTXT.getText().toString().length() == 0) {
						UPCTXT.setError("Enter a UPC");
					} else if (productTXT.getText().toString().length() > 12) {
						UPCTXT.setError("UPC is 12 Digits");

					} else {

						querySave("UPC", UPCTXT.getText().toString(),editor);
						newActivity(intent,productTXT,searchType,UPCTXT);
					}

				}

			}

			/*
			 * Instead of writing the code three different times, creating a
			 * method to accomplish all of this. The fields It will write the
			 * values passed into the preferences to open in
			 */
			
			

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
					productLbl.setVisibility(View.VISIBLE);
					productTXT.setVisibility(View.VISIBLE);
					productType.setVisibility(View.GONE);
					UPCTXT.setVisibility(View.GONE);
					productTypeLbl.setVisibility(View.GONE);
					break;
				case 1:
					productLbl.setVisibility(View.GONE);
					productTypeLbl.setVisibility(View.VISIBLE);
					productTXT.setVisibility(View.GONE);
					productType.setVisibility(View.VISIBLE);
					UPCTXT.setVisibility(View.GONE);
					break;
				case 2:
					productLbl.setVisibility(View.VISIBLE);
					productTypeLbl.setVisibility(View.GONE);
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
/**
 * Resetting the display as well as starting the new activity.
 * @param intent The Intent that will start the new activity
 * @param productTXT the edit box for product name
 * @param searchType the spinner for product type
 * @param uPCTXT the edit box for UPC.
 */
	protected void newActivity(Intent intent, EditText productTXT, Spinner searchType, EditText uPCTXT) {
		productTXT.setVisibility(View.VISIBLE);
		searchType.setVisibility(View.GONE);
		uPCTXT.setVisibility(View.GONE);
		startActivity(intent);
		finish();
		
	}
/**
 * Saving information to preference
 * @param valueA the value for queryType
 * @param valueB the value for queryValue
 * @param editor the editor to commit this to pref.
 */
	protected void querySave(String valueA, String valueB, Editor editor) {
		String fieldA = "queryType";
		String fieldB = "queryValue";
		editor.putString(fieldA, valueA);
		editor.putString(fieldB, valueB);
		editor.commit();
		
	}

}