package com.team2.shopperhelper;

import android.annotation.SuppressLint;
import android.widget.EditText;

@SuppressLint("ParserError")
public class Validate {

	


	public Validate() {
		// TODO Auto-generated constructor stub
	}

	
	public boolean ValidZip(String zip, EditText zipTXT, boolean invalid) {
		
		
		if (zip.length() < 5)
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


	public boolean ValidState(String state, EditText stateTXT, boolean invalid) {
		
		/*
		 * Ensures that the state variable does not contain any numbers.
		 */
		for(char c: state.toCharArray())
		{
			if(Character.isDigit(c))
			{
				stateTXT.setText(R.string.no_numbers_for_states);
				invalid=true;
			}
		}
		
		/*
		 * Ensures that the state variable only consists of two letters.
		 */
		if (state.length() > 2)
		{
			stateTXT.setText(R.string.stateb);
			invalid=true;
		}
		
		return invalid;
		
	}


	public boolean UPCValid(String uPC, EditText uPCTXT) {
		
		boolean invalid = false;

		if (uPC.length() < 12)
		{
			uPCTXT.setText(R.string.UPC);	
			invalid = true;
		} else if (uPC.length() > 12)
		{
			uPCTXT.setText(R.string.UPC);
			invalid=true;
		}
		return invalid;
	}

}
