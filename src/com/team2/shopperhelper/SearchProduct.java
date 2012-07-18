package com.team2.shopperhelper;

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

	private String productName;
	private String productType;
	private String UPC;
	private boolean invalid;

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
		final EditText productNameTXT = (EditText) findViewById(R.id.productnameTXT);
		final EditText productTypeTXT = (EditText) findViewById(R.id.producttypeTXT);
		final EditText UPCTXT = (EditText) findViewById(R.id.upcTXT);
		ImageButton search = (ImageButton) findViewById(R.id.search);
		ImageButton info = (ImageButton) findViewById(R.id.info);
		ImageButton clear = (ImageButton) findViewById(R.id.clear);
		
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gainValues();
				if (UPC.length()>0)
				{
					validateValues();
				}
				
				if(setInvalid(false))
				{
					UPCTXT.setText("YAY");
				}
			}
private void validateValues() {
				if((UPC.length()>12)^(UPC.length()>12))
				{
					UPCTXT.setText(R.string.wrongUPC);
					setInvalid(true);
										
				}
				
			}
			/*
 * Getting the values from the text fields.
 */
			private void gainValues() {
				productName=productNameTXT.getText().toString();
				productType=productTypeTXT.getText().toString();
				UPC = UPCTXT.getText().toString();
				
			}
		});
		
		/*
		 * When the clear button is clicked, the value will be erased.
		 */
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				productNameTXT.setText(null);
				productTypeTXT.setText(null);
				UPCTXT.setText(null);
				
			}
		});

	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	/**
	 * @return the uPC
	 */
	public String getUPC() {
		return UPC;
	}

	/**
	 * @param uPC the uPC to set
	 */
	public void setUPC(String uPC) {
		UPC = uPC;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the invalid
	 */
	public boolean isInvalid() {
		return invalid;
	}

	/**
	 * @param invalid the invalid to set
	 */
	public boolean setInvalid(boolean invalid) {
		this.invalid = invalid;
		return invalid;
	}
}
