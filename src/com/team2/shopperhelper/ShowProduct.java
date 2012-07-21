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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author Dana Haywood
 * @date 7/19/2012
 * @version 0.1.0
 * @IT482
 * @Karl Lloyd
 * @Comment by:
 * @Source Cite: http://www.helloandroid.com/tutorials/connecting-mysql-database
 *         and http://geekswithblogs.net/bosuch/archive/2011/01
 *         /31/android---create-a- custom-multi-line-listview-bound-to-an.aspx
 * 
 *         The ShowProduct class will receive the information from the
 *         SearchProduct activity and attempt to pull the information from the
 *         PHP site. Once the information is received, it will list it similar
 *         to how ShowStore was done. At the moment, it is calling a test Method
 *         (temporary) to be able to test that functionality.
 * 
 */
public class ShowProduct extends Activity {
	Intent intent;
	Bundle bundle;
	private String queryProduct;
	private String queryType;
	private String queryUPC;
	private String productName;
	private double price;
	private int inventoryCount;
	private String section;
	private String aisle;
	private HttpPost httpPost;
	private InputStream input;
	private JSONObject json_data;
	private JSONArray productResults;
	private HttpEntity httpEntity;
	ArrayList<ProductResults> pResults = new ArrayList<ProductResults>();
	ProductResults pr = new ProductResults();

	private ArrayList<NameValuePair> valueSearch = new ArrayList<NameValuePair>();
	private HttpClient httpClient = new DefaultHttpClient();
	private String result;

	private HttpResponse response;
	private String storeID;

	ArrayList<ProductResults> results = new ArrayList<ProductResults>();
	ProductResults pr1 = new ProductResults();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readweb);
		bundle = getIntent().getExtras();
						
		
		/*
		 * Takes the information from the bundles and adds to add the search
		 * string. This may be changed in the future.
		 */
		this.storeID = bundle.getString("storeID");
		this.queryProduct = bundle.getString("productName");
		this.queryType = bundle.getString("productType");
		this.queryUPC = bundle.getString("UPC");

		/*
		 * Getting the information to list on the web. At the moment, it is only
		 * pointing to ParseWebTest so we can test the listing functionality.
		 */
		ArrayList<ProductResults> productResultArray = parseWebTest();
		/*
		 * Creating a listview to display the results.
		 */
		final ListView listView = (ListView) findViewById(R.id.webList);

		listView.setAdapter(new ProductCustomBaseAdapter(this,
				productResultArray));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				// unimplemented

			}

		});

	}

	
	/*
	 * Not called yet as we do not have a web server available to do any live
	 * testing. However, this is the process where it is sending the request to
	 * the internet and retrieving.
	 */
	@SuppressWarnings("unused")
	private void setupHTTP() {
		httpPost = new HttpPost("http://someurl");
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(valueSearch));
			response = httpClient.execute(httpPost);
			httpEntity = response.getEntity();
			input = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * So the information is understandable to Android, we are converting them
	 * to string.
	 */
	@SuppressWarnings("unused")
	private void convertResponse() throws UnsupportedEncodingException {
		BufferedReader bfReader = new BufferedReader(new InputStreamReader(
				input, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();

		String line = null;

		try {
			while ((line = bfReader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * The same as ShowStore, this is grabbing the values and adding them to an
	 * ArrayList.
	 */
	@SuppressWarnings("unused")
	private ArrayList<ProductResults> parseWeb() throws JSONException {

		for (int i = 0; i < productResults.length(); i++) {
			json_data = productResults.getJSONObject(i);

			productName = json_data.getString("productName");
			pr1.setProductName(productName);

			price = json_data.getDouble("price");
			String convertPrice = Double.toString(price);
			pr1.setPrice(convertPrice);

			inventoryCount = json_data.getInt("inventoryCount");
			String convertInventory = Integer.toString(inventoryCount);
			pr1.setInventoryCount(convertInventory);

			section = json_data.getString("section");
			pr1.setSection(section);

			aisle = json_data.getString("aisle");
			pr1.setAisle("aisle");

			pResults.add(pr1);
		}
		return pResults;

	}

	/*
	 * Same parseWeb, but this is for testing purposes.
	 */
	public ArrayList<ProductResults> parseWebTest() {
		pr1.setProductName("Twinkies");
		pr1.setPrice("2.99");
		pr1.setInventoryCount("100");
		pr1.setSection("Snack Food");
		pr1.setAisle("A1");

		pResults.add(pr1);
		return pResults;
	}

	public String getQueryProduct() {
		return queryProduct;
	}

	public void setQueryProduct(String queryProduct) {
		this.queryProduct = queryProduct;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryUPC() {
		return queryUPC;
	}

	public void setQueryUPC(String queryUPC) {
		this.queryUPC = queryUPC;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public HttpPost getHttpPost() {
		return httpPost;
	}

	public void setHttpPost(HttpPost httpPost) {
		this.httpPost = httpPost;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public JSONObject getJson_data() {
		return json_data;
	}

	public void setJson_data(JSONObject json_data) {
		this.json_data = json_data;
	}

	public JSONArray getProductResults() {
		return productResults;
	}

	
	public ArrayList<NameValuePair> getValueSearch() {
		return valueSearch;
	}

	public void setValueSearch(ArrayList<NameValuePair> valueSearch) {
		this.valueSearch = valueSearch;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public HttpEntity getHttpEntity() {
		return httpEntity;
	}

	public void setHttpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
	}

}
