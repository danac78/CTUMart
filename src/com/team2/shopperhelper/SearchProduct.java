package com.team2.shopperhelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @author Dana Haywood
 * @date 7/12/2012
 * @version 1.0
 * @IT482
 * @Karl Lloyd
 * 
 *       This activity is set to retrieve customer information from the user
 *       interface. Once this is done, it will go through a validation process,
 *       and get passed onto the web parsing and listing process.
 * 
 */
public class SearchProduct extends Activity {

	protected String productName;
	protected String producType;
	protected String UPC;
	boolean invalid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);

		/*
		 * Creating instances of Edit Text and Image Buttons to manipulate.
		 */
		final EditText productnameTXT = (EditText) findViewById(R.id.productnameTXT);
		final EditText producttypeTXT = (EditText) findViewById(R.id.producttypeTXT);
		final EditText UPCTXT = (EditText) findViewById(R.id.upcTXT);
		ImageButton search = (ImageButton) findViewById(R.id.search);
		ImageButton info = (ImageButton) findViewById(R.id.info);
		ImageButton clear = (ImageButton) findViewById(R.id.clear);

		search.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*
				 * Gathering the information
				 */
				productName = productnameTXT.toString();
				producType = producttypeTXT.toString();
				UPC = UPCTXT.toString();

				/*
				 * Sending the UPC to the validate process. Validate will only
				 * be setup and called if the UPC is not null to save on the
				 * number of instances open.
				 */
				if (UPC != null) {
					Validate valid = new Validate(UPC);
					invalid = valid.UPCValid(UPC, UPCTXT);
				}

				if (invalid = false) {

				}

			}
		});

		info.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("ParserError")
			public void onClick(View v) {
				// future implementation

			}
		});
		/*
		 * if the clear button is clicked, it will send null values to the text
		 * boxes.
		 */
		clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				productnameTXT.setText(null);
				producttypeTXT.setText(null);
				UPCTXT.setText(null);

			}
		});

	}

}
