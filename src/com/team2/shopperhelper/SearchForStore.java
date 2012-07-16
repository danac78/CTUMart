package com.team2.shopperhelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

		
	
	@SuppressLint({ "ParserError", "ParserError", "ParserError" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchstore);
		
		
		/*
		 * Creating the image buttons and texts inside java to manipulate. Additionally, creating an instance
		 * of validate to send the information in.
		 */
		
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
		final EditText cityTXT = (EditText) findViewById(R.id.cityTXT);
		final EditText stateTXT = (EditText) findViewById(R.id.stateTXT);
		final EditText zipTXT = (EditText) findViewById(R.id.zipTXTa);
		final Validate valid = new Validate();
		final Intent intent = new Intent(this,ShowStores.class);
		final Bundle bundle= new Bundle();
		/*
		 * Upon the Search Button being pressed, this will collect the information from the 
		 * tabs and put them into strings that can be validated and sent to the parser.
		 */
		
		search.setOnClickListener(/**
		 * @author oDesk
		 *
		 */
		new View.OnClickListener() {
			
			 
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
					String sql=createQuery(city,state,zip);
					bundle.putString("1",sql);
					intent.putExtras(bundle);
					startActivity(intent);
					
					
				}
				
			}
	/*
	 * createQuery will be responsible for creating the query that will be sent to the database.
	 * It will take the values from the screen and place them into an SQL query in the appropriate 
	 * structure and pass it onto the database class.	
	 */
private String createQuery(String city, String state, String zip) {
	
	/*
	 * 
	 */
	final StringBuilder SQL = new StringBuilder();
	SQL.append("SELECT * ");
	SQL.append("FROM storeTbl ");
	SQL.append("WHERE ");
	
	
	if(city!=null)
	{
		SQL.append(wherebuilder("city=",city," AND state=",state," AND zip=",zip));
		
	} else if (state!=null)
	{
		SQL.append(wherebuilder("state=",state," AND city=",city," AND zip=", zip));
	} else {
		SQL.append(wherebuilder("zip=",zip," AND city=",city," AND state=",state));
	}
	return SQL.toString();
		
	

				
			}
			/*
			 * Rather than repeating the lines of code in createQuery, this helper will put together the query based
			 * on the information passed along. It will send the query line back for createQuery to make a ling.
			 */
			private String wherebuilder(String fielda, String valuea, 
					String fieldb, String valueb, String fieldc, String valuec) {
				StringBuilder SQLine = new StringBuilder();
				if (valueb!=null)
				{
					SQLine.append(fielda);
					SQLine.append(valuea);
					SQLine.append(fieldb);
					SQLine.append(valueb);
					
				} else if ((valueb!=null) && (valuec!=null))
				{
					SQLine.append(fielda);
					SQLine.append(valuea);
					SQLine.append(fieldb);
					SQLine.append(valueb);
					SQLine.append(fieldc);
				} else {
					SQLine.append(fielda);
					SQLine.append(valuea);
				}
						return SQLine.toString();
	
	
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
					valid.ValidState(state,stateTXT);
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
				cityTXT.setText(null);
				stateTXT.setText(null);
				zipTXT.setText(null);				
				
			}
		});
		

	}
}
