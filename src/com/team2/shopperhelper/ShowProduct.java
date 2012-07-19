package com.team2.shopperhelper;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ShowProduct extends Activity {
	Intent intent;
	Bundle bundle;
	private String productName;
	private String price;
	private String inventoryCount;
	private String section;
	private String aisle;
	private HttpPost httpPost;
	private InputStream input;
	private JSONObject json_data;
	private JSONArray ProductResults;
	/**
	 * @return the intent
	 */
	public Intent getIntent() {
		return intent;
	}

	/**
	 * @param intent the intent to set
	 */
	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	/**
	 * @return the bundle
	 */
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * @param bundle the bundle to set
	 */
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the inventoryCount
	 */
	public String getInventoryCount() {
		return inventoryCount;
	}

	/**
	 * @param inventoryCount the inventoryCount to set
	 */
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the aisle
	 */
	public String getAisle() {
		return aisle;
	}

	/**
	 * @param aisle the aisle to set
	 */
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	/**
	 * @return the httpPost
	 */
	public HttpPost getHttpPost() {
		return httpPost;
	}

	/**
	 * @param httpPost the httpPost to set
	 */
	public void setHttpPost(HttpPost httpPost) {
		this.httpPost = httpPost;
	}

	/**
	 * @return the input
	 */
	public InputStream getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(InputStream input) {
		this.input = input;
	}

	/**
	 * @return the json_data
	 */
	public JSONObject getJson_data() {
		return json_data;
	}

	/**
	 * @param json_data the json_data to set
	 */
	public void setJson_data(JSONObject json_data) {
		this.json_data = json_data;
	}

	/**
	 * @return the productResults
	 */
	public JSONArray getProductResults() {
		return ProductResults;
	}

	/**
	 * @param productResults the productResults to set
	 */
	public void setProductResults(JSONArray productResults) {
		ProductResults = productResults;
	}

	/**
	 * @return the valueSearch
	 */
	public ArrayList<NameValuePair> getValueSearch() {
		return valueSearch;
	}

	/**
	 * @param valueSearch the valueSearch to set
	 */
	public void setValueSearch(ArrayList<NameValuePair> valueSearch) {
		this.valueSearch = valueSearch;
	}

	/**
	 * @return the httpClient
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * @param httpClient the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the valueType
	 */
	public int getValueType() {
		return valueType;
	}

	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(int valueType) {
		this.valueType = valueType;
	}

	/**
	 * @return the response
	 */
	public HttpResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	/**
	 * @return the storeID
	 */
	public String getStoreID() {
		return storeID;
	}

	/**
	 * @param storeID the storeID to set
	 */
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	/**
	 * @return the results
	 */
	public ArrayList<ProductResults> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(ArrayList<ProductResults> results) {
		this.results = results;
	}

	/**
	 * @return the pr1
	 */
	public ProductResults getPr1() {
		return pr1;
	}

	/**
	 * @param pr1 the pr1 to set
	 */
	public void setPr1(ProductResults pr1) {
		this.pr1 = pr1;
	}

	private ArrayList<NameValuePair> valueSearch = new ArrayList<NameValuePair>();
	private HttpClient httpClient = new DefaultHttpClient();
	private String result;
	private int valueType;
	private HttpResponse response;
	private String storeID;

	
	ArrayList<ProductResults> results = new ArrayList<ProductResults>();
	ProductResults pr1 = new ProductResults();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readweb);
		
		input = setupHTTP();
		result = convertResponses();
		parseWeb();
		displayWeb();
		
	}

	private void displayWeb() {
		// TODO Auto-generated method stub
		
	}

	private void parseWeb() {
		// TODO Auto-generated method stub
		
	}

	private String convertResponses() {
		// TODO Auto-generated method stub
		return null;
	}

	private InputStream setupHTTP() {
		// TODO Auto-generated method stub
		return null;
	}
}
