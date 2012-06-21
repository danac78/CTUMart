package com.Team2.ShopperHelper;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.widget.TextView;

public class SearchforStore {
	String StoreID[];
	String StoreName[];
	String Address[];
	String secondAddress[];
	String city[];
	String state[];
	String zipCode[];
	String phoneNumber[];
	InputStream input;
	Context context = null; 
	public SearchforStore(TextView tv) throws IOException, XmlPullParserException{
		XMLParser parse = new XMLParser();
		input = context.getAssets().open("store.xml");
		getInput(tv,parse,input);
		
		displayInput(tv);
		
	}

	private void displayInput(TextView tv) {
		getStoreID();
		setStoreID(StoreID);
		
	}

	private void setStoreID(String[] storeID2) {
		// TODO Auto-generated method stub
		
	}

	private void getStoreID() {
		// TODO Auto-generated method stub
		
	}

	private void getInput(TextView tv, XMLParser parse, InputStream input2) throws XmlPullParserException, IOException {
		parse.parse(input2);
		
	}
	
	
}
