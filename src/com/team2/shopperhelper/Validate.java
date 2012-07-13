package com.team2.shopperhelper;

import android.annotation.SuppressLint;
import android.widget.EditText;

/**
 * @author Dana Haywood
 * @date 7/12/2012
 * @version 1.0
 * @IT482
 * @Karl Lloyd
 * 
 * The Validate class will take input from SearchForStore and searchProduct, and determine if they are
 * valid values.
 *
 */
@SuppressLint("ParserError")
public class Validate {

	


	protected boolean invalid = false;;

	public Validate() {
		/*
		 * Just a constructor.
		 */
	}

	
	public boolean ValidZip(String zip, EditText zipTXT, boolean invalid) {
		/*
		 * Checking to see if zip code equals five. Sends an error and tells the app
		 * the value is invalid.
		 */
		
		if (zip.length() != 5)
		{
			zipTXT.setText(R.string.zipCodeWrong);
			invalid = true;
		}
			
		return invalid;
	}


	public boolean ValidCity(String city, EditText cityTXT) {
		
		/*
		 * This method checks to see if there is a number in the city string. 
		 * As no city is known for having a number in the text, this will declare 
		 * this string as invalid.
		 */
		for (char c: city.toCharArray())
		{
			if(Character.isDigit(c))
			{
				cityTXT.setText(R.string.cityValueInvalid);
				return true;
			}
		}
		
		return false;
	}

	/*
	 * Receives the UPC and checks to see if there are 12 numbers in it. If there is 
	 * not, it will report an error.
	 */
	public boolean UPCValid(String uPC, EditText uPCTXT) {
		
		
		if(uPC.length()!=12)
		{
			uPCTXT.setText(R.string.wrongUPC);
			invalid=true;
		}
		return invalid;
	}

	/*
	 * checks to see if the State is a valid input.
	 */
	public boolean ValidState(String state, EditText stateTXT) {
		
		for(char c: state.toCharArray())
		{
			if(Character.isDigit(c))
			{
				stateTXT.setText(R.string.no_numbers_for_states);
				invalid = true;
			}
					
		}
		
		if(state.length() !=2)
		{
			stateTXT.setText(R.string.stateb);
			invalid = true;
		}
		return invalid;
	}

}
