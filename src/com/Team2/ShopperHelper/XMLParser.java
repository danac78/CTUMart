package com.Team2.ShopperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XMLParser {

	public static class Entry {
		public final String storeID;
		public final String address;
		public final String secondAddress;
		public final String city;
		public final String state;
		public final String zip;

		private Entry(String storeID, String address, String secondAddress,
				String city, String state, String zip) {
			this.storeID = storeID;
			this.address = address;
			this.secondAddress = secondAddress;
			this.city = city;
			this.state = state;
			this.zip = zip;
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
	}
	private static final String ns = null;

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

	public Object parse;

	@SuppressWarnings("rawtypes")
	public List parse(InputStream read) throws IOException,
			XmlPullParserException {
		try {
			XmlPullParser parse = Xml.newPullParser();
			parse.setInput(read, null);
			parse.nextTag();
			return readFeed(parse);

		} finally {
			read.close();
		}

	}

	private Object readEntry(XmlPullParser parse) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List readFeed(XmlPullParser parse) throws XmlPullParserException,
			IOException {

		List stores = new ArrayList();

		parse.require(XmlPullParser.START_TAG, ns, "CTUMart");

		while (parse.next() != XmlPullParser.END_TAG) {
			if (parse.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parse.getName();
			if (name.equals("Store")) {
				stores.add(readEntry(parse));
			} else {
				skip(parse);
			}
		}

		return stores;
	}
}
