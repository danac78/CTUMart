package com.team2.shopperhelper;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StoreCustomBaseAdapter extends BaseAdapter {

	static class ViewHolder {

		TextView storeTXT;
		TextView addressTXT;
		TextView secondAddressTXT;
		

	}

	private static ArrayList<SearchResults> searchArrayList;
	private LayoutInflater mInflater;
	
	public StoreCustomBaseAdapter(Context context, ArrayList<SearchResults> results)
	{
		searchArrayList=results;
		mInflater = LayoutInflater.from(context);
	}
	/**
	 * @return the searchArrayList
	 */
	public static ArrayList<SearchResults> getSearchArrayList() {
		return searchArrayList;
	}

	/**
	 * @param searchArrayList the searchArrayList to set
	 */
	public static void setSearchArrayList(ArrayList<SearchResults> searchArrayList) {
		StoreCustomBaseAdapter.searchArrayList = searchArrayList;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return searchArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return searchArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView==null)
		{
			convertView=mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.storeTXT = (TextView) convertView.findViewById(R.id.Store);
			holder.addressTXT=(TextView) convertView.findViewById(R.id.address);
			holder.secondAddressTXT = (TextView) convertView.findViewById(R.id.secondAddress);
			
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.storeTXT.setText(searchArrayList.get(position).getStore());
		holder.addressTXT.setText(searchArrayList.get(position).getAddress());
		holder.secondAddressTXT.setText(searchArrayList.get(position).getSecondAddress());
		
		return convertView;		
		
	}
}
