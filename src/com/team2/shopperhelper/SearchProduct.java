package com.team2.shopperhelper;

import android.app.Activity;
import android.os.Bundle;
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

	@SuppressWarnings("unused")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);
		
		Bundle bundle = getIntent().getExtras();
		
		String storeID = bundle.getString("storeID");
		
		
		/*
		 * Creating instances of Edit Text and Image Buttons to manipulate.
		 */
		final EditText productnameTXT = (EditText) findViewById(R.id.productnameTXT);
		final EditText producttypeTXT = (EditText) findViewById(R.id.producttypeTXT);
		final EditText UPCTXT = (EditText) findViewById(R.id.upcTXT);
		ImageButton search = (ImageButton) findViewById(R.id.search);
		ImageButton info = (ImageButton) findViewById(R.id.info);
		ImageButton clear = (ImageButton) findViewById(R.id.clear);
		
		productnameTXT.setText(storeID);

	}
}
