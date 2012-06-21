package com.Team2.ShopperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XMLParser {

	private static final String ns = null;
	
		@SuppressWarnings("rawtypes")
		
		public List parse(InputStream input) throws XmlPullParserException, IOException 
		{
			/* METHOD parse will be responsible for initiating the parse process and preparing
			 * it to be able to read and print the XML to the screen.*/
			try
			{
				/*This allows for a parse to be the object for a XmlPullParser class, which will
				 * read the XML file and parse the data. This object will be passed along to
				 * other methods to help decipher the xml file we have in assets */
				
				XmlPullParser parse = Xml.newPullParser();
				
				/* This may be removed if it causes a conflict. This is essentially telling
				 * the parser we do not want to use NameSpaces*/
				parse.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
				
				/* The XML file will be read through the InputStream. Instead of having the Input read the file 
				 * and send it to the parse, the Input object shall become part of the parse object and will
				 * all it to read the information. */
				
				parse.setInput(input,null);
				
				/* This is going to the next tag available in  */
				parse.nextTag();
				return readXML(parse);
				
			} finally {
				input.close();
			}
		}
		
		private String getInfo(XmlPullParser parse) throws XmlPullParserException, IOException {
			String results = "";
			if (parse.next()==XmlPullParser.TEXT)
			{
				results = parse.getText();
				parse.nextTag();
			}
			return results;
		}

		private String readAddress(XmlPullParser parse) throws XmlPullParserException, IOException {
			parse.require(XmlPullParser.START_TAG, ns, "Address");
			String address = getInfo(parse);
			parse.require(XmlPullParser.END_TAG, ns, "Address");
			
			return address;
		}

		private String readCity(XmlPullParser parse) throws XmlPullParserException, IOException {
			parse.require(XmlPullParser.START_TAG, ns, "City");
			String city = getInfo(parse);
			parse.require(XmlPullParser.END_TAG, ns, "City");
			
			return city;
		}

		private String readSecondAddress(XmlPullParser parse) throws XmlPullParserException, IOException {
			parse.require(XmlPullParser.START_TAG, ns, "Address2");
			String secondAddress = getInfo(parse);
			parse.require(XmlPullParser.END_TAG, ns, "Address2");
			
			return secondAddress;
		}

		private String readState(XmlPullParser parse) throws XmlPullParserException, IOException {
			parse.require(XmlPullParser.START_TAG, ns, "State");
			String state = getInfo(parse);
			parse.require(XmlPullParser.END_TAG, ns, "State");
			
			return state;
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private List readXML(XmlPullParser parse) throws XmlPullParserException,
		IOException {

			List stores = new ArrayList();

			parse.require(XmlPullParser.START_TAG, ns, "CTUMart");

			while (parse.next() != XmlPullParser.END_TAG) {
				if (parse.getEventType() != XmlPullParser.START_TAG) {
					continue;
				}
				String name = parse.getName();
				if (name.equals("Store")) {
					stores.add(readStores(parse));
				} else {
					skip(parse);
				}
			}

			return stores;
		}
		
		@SuppressWarnings("unused")
		private Entry readStores(XmlPullParser parse)
				throws XmlPullParserException, IOException {
			parse.require(XmlPullParser.START_TAG, ns, "Store");
			String storeID = null;
			String address = null;
			String secondAddress = null;
			String city = null;
			String state = null;
			String zip = null;

			while (parse.next() != XmlPullParser.END_TAG) {
				if (parse.getEventType() != XmlPullParser.START_TAG) {
					continue;
				}

				String name = parse.getName();
				if (name.equals("StoreID"))
				{
					storeID = readStoreID(parse);
				} else if (name.equals("Address")) {
					address = readAddress(parse);
				} else if (name.equals("Address2")) {
					secondAddress = readSecondAddress(parse);
				} else if (name.equals("City")) {
					city = readCity(parse);
				} else if (name.equals("State")) {
					state = readState(parse);
				} else if (name.equals("Zip")) {
					zip = readZip(parse);
				} else {
					skip(parse);
				}

			}

			return new Entry(address, city, state, zip, zip, zip);
		}

		private String readStoreID(XmlPullParser parse) throws XmlPullParserException, IOException {
			parse.require(XmlPullParser.START_TAG, ns, "StoreID");
			String StoreID = getInfo(parse);
			parse.require(XmlPullParser.END_TAG, ns, "StoreID");
			
			return StoreID;
		}

		private String readZip(XmlPullParser parse)
				throws XmlPullParserException, IOException {

			parse.require(XmlPullParser.START_TAG, ns, "Zip");
			String zip = getInfo(parse);
			parse.require(XmlPullParser.END_TAG, ns, "Zip");
			return zip;
		}
	
	

	private static void skip(XmlPullParser parse) throws XmlPullParserException, IOException {
		if (parse.getEventType() != XmlPullParser.START_TAG)
		{
			throw new IllegalStateException();
		}
		
		int depth=1;
		
		while (depth !=0)
		{
			switch (parse.next())
					{
				case XmlPullParser.END_TAG:
					depth--;
					break;
				case XmlPullParser.START_TAG:
					depth++;
					break;
					}
		}

	}

	
	

	
	
}
