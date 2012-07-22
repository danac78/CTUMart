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
import android.widget.TextView;

/**
 * @author Dana Haywood
 * @date 7/12/2012
 * @version 0.1.2
 * @IT482
 * @Karl Lloyd
 * @Source Cite http://developer.android.com/guide/components/index.html
 * 

 *       This activity is set to retrieve customer information from the user
 *       interface. Once this is done, it will go through a validation process,
 *       and get passed onto the web parsing and listing process.
=======
 *          This activity is set to retrieve customer information from the user
 *          interface. Once this is done, it will go through a validation
 *          process, and get passed onto the web parsing and listing process...

 * 
 */
public class SearchProduct extends Activity {

	public static final String PREF_NAME = "shopPref";
	
/*
 * Creating local variables to be used in ShowProducts
 */
	
	
	
		
	@SuppressWarnings("unused")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);
		/*
		 * Creating instances of Edit Text and Image Buttons to manipulate.
		 */
		final EditText productTXT = (EditText) findViewById(R.id.productTXT);
		final Spinner productType = (Spinner) findViewById(R.id.productTypes);
		final Spinner searchType = (Spinner) findViewById(R.id.typeID);
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
//		
//		/*
//		 * creating constants that will not change in this activity.
//		 * 
//		 * nextIntent: setting up the next intent so it is ready for when 
//		 * we go for the next activity.
//		 * 
//		 * thisIntent: creating the intent for this class.
//		 * 
//		 */
		final Intent thisIntent = new Intent();
		final int storeID = thisIntent.getIntExtra("storeID", 0);				
		final Intent intent = new Intent(this,ShowProduct.class);
		final TextView help = (TextView) findViewById(R.id.productHelpTXT);
		
		search.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				final int position = searchType.getSelectedItemPosition();
				/*
				 * Based on the position of typeSearch, it will decide the 
				 * query type and value to send to the web. It will also
				 * do a validation with If/Else statements prior to assigning
				 * the value.
				 */
				
				switch(position)
				{
				case 0:
					
					if(productTXT.getText().toString().length()==0)
					{
						productTXT.setError("Enter a Product Name");
					} else 
					{
						querySave("productName", productTXT.getText().toString());	
					}					
					break;
				case 1:
					querySave("productType", productType.getSelectedItem().toString());
					
					break;
				case 2:
					if(productTXT.getText().toString().length()==0)
					{
						productTXT.setError("Enter a UPC");
					} else if(productTXT.getText().toString().length()!=12)
					{
						productTXT.setError("UPC is 12 Digits");
					
					} else {
						
						querySave("UPC",productTXT.getText().toString());						
					}
					
				}			
				
				
			}
			private void querySave(String valuea,
					String valueb) {
				String fielda = "queryType";
				String fieldb = "queryValue";
				
				SharedPreferences settings = getSharedPreferences(PREF_NAME,0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(fielda, valuea);
				editor.putString(fieldb, valueb);
				editor.commit();
				startActivity(intent);
				
			}
						
			

			
		});
		
		/*
		 * resets the screen.
		 */
		clear.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				help.setVisibility(View.GONE);
				productTXT.setText(null);
				searchType.setSelection(1);
				
			}
		});

//		
//		/*
//		 * This will change the view between productTXT and productType.
//		 */
		searchType.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				switch(position)
				{
				case 0: productTXT.setVisibility(View.VISIBLE);
						productType.setVisibility(View.GONE);break;
				case 1: productTXT.setVisibility(View.GONE);
						productType.setVisibility(View.VISIBLE);break;
				case 2: productTXT.setVisibility(View.VISIBLE);
						productType.setVisibility(View.GONE);break;
				}
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	

}