/**
 * 
 */
package com.team2.shopperhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
/**
 * @author Dana Haywood
 * @date 7/11/2012
 * @version 1.0
 * @IT482
 * @Karl Lloyd
 * 
 *       This class with the associated layout is responsible for collecting the
 *       information from the client and searching for the store in store.xml.
 *       The reason for using the xml file is that the information will be
 *       static and read-only. The store.xml will be changed with a future
 *       implementation of an updater. This prevents unnecessary database access
 *       on a wireless or cellular signal.
 */
public class searchstore extends Activity {
	
	String state;
	String city;
	String zip;
	boolean validInfo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchforstore);
		/**
		 * Creating the links to the buttons and the edit text that will be used
		 * to collect the information into Java so it can parse the information.
		 */
		ImageButton search = (ImageButton) findViewById(R.id.searchBTN);
		ImageButton clear = (ImageButton) findViewById(R.id.clearBTN);
		final EditText cityTXT = (EditText) findViewById(R.id.cityTXT);
		final EditText stateTXT = (EditText) findViewById(R.id.stateTXT);
		final EditText zipTXT = (EditText) findViewById(R.id.zipTXT);
		
		search.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				city=cityTXT.toString();
				state=stateTXT.toString();
				zip=zipTXT.toString();
				
				/*
				 * Going to send the zip to validation.
				 */
				Validate valid = new Validate();
				validInfo = valid.ValidZip(zip);
				
				/*
				 * If the zipcode is not greater than five characters, it is going to
				 * report back a problem. Otherwise, it will start the search.
				 */
				if(validInfo=false)
				{
					zipTXT.setText(R.string.zipCodeWrong);
				}
				
			}
		});
		
		
		/*
		 * When the Clear button is clicked, it will clear out the edittext boxes. 
		 * It will change the text to "" to clear them.
		 */
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cityTXT.setText("");
				stateTXT.setText("");
				zipTXT.setText("");
				
			}
		});
		
	}

}
