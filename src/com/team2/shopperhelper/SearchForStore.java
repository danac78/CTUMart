package com.team2.shopperhelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @author Dana Haywood
 * @date 7/10/2012
 * @version 0.1.0
 * @IT482
 * @Karl Lloyd
 * 
 *       This Activity is meant to gather information from the client. Once the
 *       information is validated, it will pass the information onto the parser.
 */
@SuppressWarnings("unused")
public class SearchForStore extends Activity {

	private String zip;
	private String state;
	private String city;
	private String[] storeAddress;
	private String[] storeID;
	boolean checkValid;
	private ArrayList<String> storeList;
	private ArrayList<String> idList;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchstore);
		

		/*
		 * Creating the image buttons and texts inside java to manipulate.
		 * Additionally, creating an instance of validate to send the
		 * information in.
		 */
		final Bundle bundle = new Bundle();
		final Intent intent = new Intent(this, ShowStore.class);
		final ImageButton search = (ImageButton) findViewById(R.id.search);
		final ImageButton clear = (ImageButton) findViewById(R.id.clear);
		final EditText cityTXT = (EditText) findViewById(R.id.cityTXT);
		final EditText stateTXT = (EditText) findViewById(R.id.stateTXT);
		final EditText zipTXT = (EditText) findViewById(R.id.zipTXTa);
		
		
		
		

		/*
		 * Upon the Search Button being pressed, this will collect the
		 * information from the tabs and put them into strings that can be
		 * validated and sent to the parser.
		 */

		search.setOnClickListener(new View.OnClickListener() {
			/*
			 * Creates a private version of this string for the onclick method
			 */
			private String city;
			private String state;
			private String zip;

			@Override
			public void onClick(View v) {
				/*
				 * gainValues() is designed to obtain the values from EditText and 
				 * place them into the String variables declared above. Validate()
				 * is being called to ensure these values are in the correct format
				 * to be checked against the XML.
				 */
				gainValues();	
				validate();
				
				
				
				/*
				 * If the validation process passed, the QueryXML(raw) method shall
				 * begin. The multiple catches are the result of numerous issues
				 * that can occur when reading and writing information.
				 */
				if(checkValid=true){
					
					bundle.putString("city", city);
					bundle.putString("state", state);
					bundle.putString("zip", zip);
					
					
					intent.putExtras(bundle);
					startActivity(intent);
				}
				
				
				
			}



						
			/*
			 * Validate() is checking the values of city, state and zip code.
			 */

			private void validate() {
				
				/*
				 * If the string text length is greater than zero (and honestly, this
				 * was the only way it would work for some reason), it will check the 
				 * values. The first two are checking to see if they contain a number. 
				 * the third is checking to see if State is greater than 2. Zip is 
				 * ensuring the zip code has five numbers.
				 */
				if(city.length()>0)
				{
					for(char c: city.toCharArray())
					{
						if(Character.isDigit(c))
						{
							cityTXT.setText(R.string.cityValueInvalid);
							checkValid=false;
							
						}
					}
				} else if (state.length()>0)
				{
					for(char c: state.toCharArray())
					{
						if(Character.isDigit(c))
						{
							stateTXT.setText(R.string.no_numbers_for_states);
							checkValid=false;
						}
					}
					
					if(state.length()>2)
					{
						stateTXT.setText(R.string.stateb);
						checkValid=false;
					}
					
				} else if (zip.length() > 0)
				{
					if(zip.length() < 5)
					{
						zipTXT.setText(R.string.zipCodeWrong);
						checkValid=false;
					}
				}
				

				
			}


			private void gainValues() {
				/*
				 * converting the Edit Text to actual strings.
				 */
				city=cityTXT.getText().toString();
				state=stateTXT.getText().toString();
				zip=zipTXT.getText().toString();
				
				
			}
		});
		/*
		 * Once the clear button is clicked, it will clear the information out
		 * of the text fields.
		 */
		clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				cityTXT.setText(null);
				stateTXT.setText(null);
				zipTXT.setText(null);
		
			}
		});

	}

	/**
	 * @return the storeList
	 */
	public ArrayList<String> getStoreList() {
		return storeList;
	}

	/**
	 * @param storeList the storeList to set
	 */
	public void setStoreList(ArrayList<String> storeList) {
		this.storeList = storeList;
	}
}