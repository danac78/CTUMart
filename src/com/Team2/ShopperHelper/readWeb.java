package com.Team2.ShopperHelper;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import android.widget.TextView;

public class readWeb
{
	ArrayList<NameValuePair> valueSearch = new ArrayList<NameValuePair>(); // to store values to pass to the parser for search
	HttpClient httpclient = new DefaultHttpClient(); // assigns an object for the HTTP Client
	HttpPost http = new HttpPost("Http://someurl"); // designates where we are going to post and retrieve the information.
	String ProductName;
	String ProductType;
	String UPC;
	
	public readWeb(String ProductName, String ProductType, String UPC, String StoreID, TextView tv)
	{
		this.ProductName = ProductName;
		this.ProductType = ProductType;
		this.UPC = UPC;
	}
	
	public void displayWeb(JSONArray storeArray)
	{
		
	}
}


