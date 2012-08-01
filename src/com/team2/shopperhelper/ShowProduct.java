package com.team2.shopperhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.team2.shopperhelper.library.ProductCustomBaseAdapter;
import com.team2.shopperhelper.library.SearchFunction;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * @author Dana Haywood
 * @date 7/19/2012
 * @version 0.1.5
 * @IT482
 * @Karl Lloyd
 * @Comment by:
 * @Source Cite: http://www.helloandroid.com/tutorials/connecting-mysql-database
 *         and http://geekswithblogs.net/bosuch/archive/2011/01/31/android---
 *         create-a-custom-multi-line-listview-bound-to-an.aspx
 * 
 *         The ShowProduct class will receive the information from the
 *         SearchProduct activity and attempt to pull the information from the
 *         PHP site. Once the information is received, it will post it into a
 *         custom list view. The list view will have customrow injected with
 *         text views to have a customized listing.
 * 
 */
@SuppressWarnings("unused")
public class ShowProduct extends Activity {
	public static final String PREF_NAME = "shopPref";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readweb);

		/*
		 * setting : Used to store information internally. queryType and
		 * queryValue should be seen like:
		 * 
		 * WHERE queryType=queryValue;
		 * 
		 * ListView where the custom rows will be inserted into.
		 * 
		 * httppost will be connecting the android to the webpage
		 * 
		 * ProductResult will create a string array to place into an arraylist.
		 * 
		 * nameValuePairs will contain the information required to do the search
		 * function.
		 * 
		 * input and sqlResults are for gaining and storing the results from the
		 * webpage.
		 */
		SharedPreferences setting = getSharedPreferences(PREF_NAME, 0);
		final String storeID = setting.getString("storeID", null);
		final String queryType = setting.getString("queryType", null);
		final String queryValue = setting.getString("queryValue", null);
		final ListView listView = (ListView) findViewById(R.id.webList);
		/*
		 * This is where the android php page that be responsible for populating
		 * this page.
		 */
		SearchFunction searchFunction = new SearchFunction();
		JSONObject json = searchFunction.searchProduct(storeID, queryType, queryValue);
		

//		listView.setAdapter(new ProductCustomBaseAdapter(this, results));

	}
}