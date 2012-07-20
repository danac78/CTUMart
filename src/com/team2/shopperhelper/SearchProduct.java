package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Intent;
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
 * @Source Cite<DO NOT
 *         REMOVE>:http://developer.android.com/guide/components/index.html
 * @Comment By:
 * 
 *          This activity is set to retrieve customer information from the user
 *          interface. Once this is done, it will go through a validation
 *          process, and get passed onto the web parsing and listing process...
 * 
 */
public class SearchProduct extends Activity {

	private String productName;
	private String productType;
	private String UPC;
	private int valueType;
	private boolean invalid;
	private String storeID;
	Intent intent;
	private Bundle bundle = new Bundle();
	@SuppressWarnings("unused")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);

		 bundle = getIntent().getExtras();

		storeID=bundle.getString("storeID");
		
		bundle.clear();
		
		intent = new Intent(this, ShowProduct.class);

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

			private String key;
			private String value;

			public void onClick(View v) {
				gainValues();
				if (UPC.length() > 0) {
					validateValues();
				}

				if (invalid = false) {
					decideLookup();
					bundle.putInt("valueType", valueType);
					switch(valueType)
					{
					case 1: valueSaved("productName",productName);break;
					case 2: valueSaved("productType",productType);break;
					case 3: valueSaved("UPC",UPC);break;
					}
					bundle.putString("storeID", storeID);
					intent.putExtras(bundle);
					startActivity(intent); 
				}
			}

			private void valueSaved(String key, String value) {
				this.key = key;
				this.value = value;
								
				bundle.putString(key, value);
				
			}

			private void decideLookup() {
				
				if(UPC.length()>0)
				{
					setValueType(3);
				} else if (productType.length()>0) 
				{
					setValueType(2);
				} else if (productName.length()>0)
				{
					setValueType(1);
				}
				
				
			}

			private void validateValues() {
				if ((UPC.length() > 12) ^ (UPC.length() > 12)) {
					UPCTXT.setText(R.string.wrongUPC);
					setInvalid(true);

				}

			}

			/*
			 * Getting the values from the text fields.
			 */
			private void gainValues() {
				productName = productNameTXT.getText().toString();
				productType = productTypeTXT.getText().toString();
				UPC = UPCTXT.getText().toString();

			}
		});

		/*
		 * When the clear button is clicked, the value will be erased.
		 */
		clear.setOnClickListener(new View.OnClickListener() {

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
	 * @param productName
	 *            the productName to set
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
	 * @param uPC
	 *            the uPC to set
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
	 * @param productType
	 *            the productType to set
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
	 * @param invalid
	 *            the invalid to set
	 */
	public boolean setInvalid(boolean invalid) {
		this.invalid = invalid;
		return invalid;
	}

	public int getValueType() {
		return valueType;
	}

	public void setValueType(int valueType) {
		this.valueType = valueType;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
}
