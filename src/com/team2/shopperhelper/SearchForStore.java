package com.team2.shopperhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @author Dana Haywood
 * @date 7/10/2012
 * @version 1.0
 * @IT482
 * @Karl Lloyd
 * 
 * This Activity is meant to gather information from the client. Once the information
 * is validated, it will pass the information onto the parser.
 */
@SuppressWarnings("unused")
public class SearchForStore extends Activity {

		
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchstore);
		
		
		
		
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
		final EditText cityTXT = (EditText) findViewById(R.id.cityTXT);
		final EditText stateTXT = (EditText) findViewById(R.id.stateTXT);
		final EditText zipTXT = (EditText) findViewById(R.id.zipTXTa);
		final Validate valid = new Validate();
		
		/*
		 * Upon the Search Button being pressed, this will collect the information from the 
		 * tabs and put them into strings that can be validated and sent to the parser.
		 */
		
		search.setOnClickListener(new View.OnClickListener() {
			
			 
			public void onClick(View v) {
				/*
				 * Gathering the text from the EditText boxes. To simplify the process,
				 * we are using the toString() method to convert the information from
				 * EditText to String.
				 */
				String city=cityTXT.toString();
				String state=stateTXT.toString();
				String zip=zipTXT.toString();
				boolean invalid = false;
				/*
				 * This passes the String city, state, and zip to the validation process
				 * in order to ensure the information is correct. It is waiting for a 
				 * true/false statement before it can proceed.
				 */
				boolean invalidData=searchValidate(city,state,zip);
				
				/*
				 * if none of the data has a problem with the verification process,
				 * it will send to the XML parse process.
				 */
				if (invalidData=false)
				{
					
				}
				
			}
/*
 * searchValidate will serve as a facilitate the Validate class to ensure the data
 * is correct prior to opening and parsing the XML file (optimizing the resources).
 * It will first check to see if the value does not equal null (meaning no value) 
 * prior to running the validation method.
 */
			private boolean searchValidate(String city, String state, String zip) {
				boolean invalid = false;
				
				if (zip!=null)
				{
					valid.ValidZip(zip,zipTXT,invalid);
				}
				
				if (city!=null)
				{
					valid.ValidCity(city,cityTXT);
				}
				
				if (state!=null)
				{
					valid.ValidState(state,stateTXT,invalid);
				}
				return invalid;
			}
		});
		
		/*
		 * Once the clear button is clicked, it will clear the information out of the 
		 * text fields.
		 */
		clear.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				cityTXT.setText("");
				stateTXT.setText("");
				zipTXT.setText("");				
				
			}
		});
		

	}
}