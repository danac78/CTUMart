package com.team2.shopperhelper.library;

import java.util.ArrayList;

import com.team2.shopperhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
	private static ArrayList<SearchResults> searchArrayList;
	 
	 private LayoutInflater mInflater;

	 public CustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
	  searchArrayList = results;
	  mInflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return searchArrayList.size();
	 }

	 public Object getItem(int position) {
	  return searchArrayList.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }
	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.custom_row_product, null);
	   holder = new ViewHolder();
	   holder.txtName = (TextView) convertView.findViewById(R.id.name);
	   holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
	   holder.txtInventoryCount = (TextView) convertView.findViewById(R.id.inventory);
	   holder.txtSection = (TextView) convertView.findViewById(R.id.sections);
	   holder.txtAisle = (TextView) convertView.findViewById(R.id.aisle);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtName.setText(searchArrayList.get(position).getName());
	  holder.txtPrice.setText(searchArrayList.get(position).getPrice());
	  holder.txtInventoryCount.setText(searchArrayList.get(position).getInventoryCount());
	  holder.txtSection.setText(searchArrayList.get(position).getSections());
	  holder.txtAisle.setText(searchArrayList.get(position).getAisle());

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtName;
	  TextView txtPrice;
	  TextView txtInventoryCount;
	  TextView txtSection;
	  TextView txtAisle;
	 }
	}
