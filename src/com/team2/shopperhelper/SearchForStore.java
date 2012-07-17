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
				 * setting up the InputStream with the xml stored in resources.
				 */
				InputStream raw = getResources().openRawResource(R.raw.store);
				
				/*
				 * If the validation process passed, the QueryXML(raw) method shall
				 * begin. The multiple catches are the result of numerous issues
				 * that can occur when reading and writing information.
				 */
				if(checkValid=true){
					try {
						QueryXML(raw);
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (XPathExpressionException e) {
						e.printStackTrace();
					}
					
					try {
						raw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*bundle.putStringArray("1", storeAddress);
					bundle.putStringArray("2", storeID);
					
					intent.putExtras(bundle);
					startActivity(intent);*/
				}
				
				
				
			}

/*
 * XPath is not really a Java, but an XML querying method that was designed by W3C.
 * This method is pulling up the means to do this, and adding only the items to the
 * array that matches the city, state, and zip.
 */
			private void QueryXML(InputStream is) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
				// Calling the items required for XPath reading.
				DocumentBuilder builder;
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				
				Document doc = null;
				XPathExpression expr=null;
				builder = factory.newDocumentBuilder();
				
				doc = builder.parse(is);
				
				// create a XPathFactory
				
				XPathFactory xFactory = XPathFactory.newInstance();
				
				//creat an XPath object
				
				XPath xpath = xFactory.newXPath();
				
				// creating an expression
				
				expr=xpath.compile("//store");
				
				// running query
				
				NodeList nodes=(NodeList)expr.evaluate(doc,XPathConstants.NODESET);
				
				/*
				 * Pulling the values from the XML query and placing them into an
				 * array list.
				 */
				for (int i=0; i< nodes.getLength();i++)
				{
					NodeList items = nodes.item(i).getChildNodes();
					String id= items.item(1).getTextContent();
					
					String address = items.item(3).getTextContent();
					String XMLcity = items.item(7).getTextContent();
					String XMLstate = items.item(9).getTextContent();
					String XMLzip = items.item(11).getTextContent();
					
					if ((city.length()>0) && (XMLcity==city))
					{
						storeList.add(address);
						idList.add(id);
					} else if ((state.length()>0) && (XMLstate==state)) 
					{
						storeList.add(address);
						idList.add(id);
					} else if ((zip.length()>0) && (XMLzip==zip))
					{
						storeList.add(address);
						idList.add(id);
					}
				}
				
				// At the moment, ignore this.
//				storeAddress=new String[storeList.size()];
//				storeID = new String[idList.size()];
				/*for (int i=1; i<= storeList.size();i++)
				{
					storeAddress[i]=storeList.get(i);
				}*/
			/*	
				for (int i=0;i<idList.size();i++)
				{
					storeID[i] = storeList.get(i);
				}*/
		
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