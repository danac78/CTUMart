package com.Team2.ShopperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/* This class will serve as the parser for Shopper Helper. It is based on Android Developer guide, but there were some modification based on our needs. */
public class XMLParser {

	// We are not going to be using namespaces for efficiency purposes.
	
	private static final String ns = null; // used to compare with namespace and give nulls.
	
	/* This method will be using the List return and functions to initiate the parser for Android.*/
	public List parse(InputStream in) throws XmlPullParserException, IOException {
	
		try {
			XmlPullParser parse = Xml.newPullParser(); //creates the parse object.
			parse.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false); //setting the namespace feature to off.
			parse.setInput(in, null); // Sets the input the parser is going to process..
			parse.nextTag(); // Will call next if it see the start or end tag being seen, or will force an exception.
			return readFeed(parse); //This will call readFeed to process the information we need from store.xml.
			
		} finally {
			in.close(); // closes the input. Without closing, this stream will be in memory throughout the app running, even if it is not being utilized;
		}
		
	}
	
	/* All that is occuring here is that it is reading the feed.*/
	
	public String LoadXML() // may change, but based on http://stackoverflow.com/questions/7821636/android-load-local-xml-file.
	{
		return FileLocation;
	}
	
	private List readXML (XmlPullParser parse) throws XmlPullParserException, IOException
	{
		@SuppressWarnings("rawtypes")
		
		List entries = new ArrayList(); // creating an array to see multiple values as XML is capable of storing and transporting.
		parse.require(XmlPullParser.START_TAG, ns, "CTUMart"); // indicating the Start tag for the XML file.
		
		while (parse.next() !=parse.END_TAG) // as long as it does not read the end tag, the loop will continue.
		{
			if (parse.getEventType() != parse.START_TAG) {
				/* if the event type is not equal to the start tag, it will break out of this loop*/
				continue;			
			}
			
			String name = parse.getName(); // gets the name of a tag.
			
			if (name.equals("Store"))
			{
				entries.add(readEntry(parse));
			} else {
				skip(parse);
			}
			
		}
		
		return entries; // returns the entries, which will return the data to the class that called XMLParser
		
		
		
	}

	private void skip(XmlPullParser parse) {
		// TODO Auto-generated method stub
		
	}
	
	private Entry readEntry(XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return new Entry(StoreID, Address,secondAddres, City, State, Zip);
	}
	
	private String readStoreID (XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return StoreID;
	}
	
	private String readAddress(XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return Address;
	}
	
	private String readSecondAddress(XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return secondAddress;
	}
	
	private String readCity(XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return City;
	}
	
	private String readState(XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return State;
	}
	
	private String readZip(XmlPullParser parse) throws XmlPullParserException, IOException
	{
		return Zip;
	}
/* This class is building the attributes and methods required to continue with the parser for this app.*/
	public static class Entry 
	{
		public final String StoreID;
		public final String Address;
		public final String secondAddress;
		public final String City;
		public final String State;
		public final String Zip;
		
		private Entry(String StoreID,String Address,String secondAddress, String City, String State, String Zip)
		{
			this.StoreID = StoreID;
			this.Address = Address;
			this.secondAddress = secondAddress;
			this.City = City;
			this.State = State;
			this.Zip = Zip;
		}
	}
	
	
	
	
}
