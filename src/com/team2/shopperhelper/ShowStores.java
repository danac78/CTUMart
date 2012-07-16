/**
 * 
 */
package com.team2.shopperhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author oDesk
 *
 */
public class ShowStores extends Activity implements OnClickListener, OnItemClickListener {

	/**
	 * 
	 */
	
	private ListView storeListing;
	private ListAdapter storeListAdapter;
	private ArrayList<StoreListPojo> pojoArrayList;
	Bundle bundle = this.getIntent().getExtras();
	String parm1 = bundle.getString("1");
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.readdb);
		super.onCreate(savedInstanceState);
		
		storeListing = (ListView) findViewById(R.id.dbView);
		storeListing.setOnItemClickListener(this);
		pojoArrayList=new ArrayList<StoreListPojo>();
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
