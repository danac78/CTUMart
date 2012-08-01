package com.team2.shopperhelper.library;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME ="ctumart";
	private static final String TABLE_RESULTS = "tblResults";
	
	private static final String ID = "_id";
	private static final String col_storeID = "storeID";
	private static final String col_pName = "productName";
	private static final String col_pType = "productType";
	private static final String col_pUPC = "UPC";
	private static final String col_price = "price";
	private static final String col_inventoryCount = "inventoryCount";
	private static final String col_sections = "Sections";
	private static final String col_aisles = "Aisle";
	private static final String col_date = "Date";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_RESULT_TABLE = "CREATE TABLE "+TABLE_RESULTS+"("
				+ID+" INTEGER PRIMARY KEY,"
				+col_storeID+" Integer,"
				+col_pName+" TEXT,"
				+col_pType+" TEXT,"
				+col_pUPC+" TEXT,"
				+col_price+" TEXT,"
				+col_inventoryCount+" INTEGER,"
				+col_sections+" TEXT,"
				+col_aisles+" TEXT," 
				+col_date+" TEXT)";
		
		db.execSQL(CREATE_RESULT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_RESULTS);		
	}
	
	public void addStoreInfo(int storeID, String productName, String productType,
			String UPC, String price, int inventoryCount, String sections, String aisle,
			String date)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(col_storeID, storeID);
		values.put(col_pName, productName);
		values.put(col_pType, productType);
		values.put(col_pUPC, UPC);
		values.put(col_price, price);
		values.put(col_inventoryCount, inventoryCount);
		values.put(col_sections, sections);
		values.put(col_aisles, aisle);
		values.put(col_date, date);
		db.insert(TABLE_RESULTS, null, values);
		db.close();
		
	}
	
	public ArrayList<ProductResults> readDB(String queryValue, String queryType, String storeID)
	{
		String query = "SELECT ProductName, Price, Inventory, Sections, Aisle,Date" +
				"FROM "+TABLE_RESULTS+
				"WHERE storeID="+storeID+" AND "+queryType+"='"+queryValue+"";
		
		SQLiteDatabase db = this.getReadableDatabase();
		ProductResults pr1 = new ProductResults();
		ArrayList<ProductResults> array = new ArrayList<ProductResults>();
		Cursor cursor = db.rawQuery(query, null);
		
		
		cursor.moveToFirst();
		
		if(cursor.getCount() > 0)
		{
			while (cursor.moveToNext())
			{
				pr1.setName(cursor.getString(1));
				pr1.setPrice(cursor.getString(4));
				pr1.setInventoryCount(Integer.toString(cursor.getInt(5)));
				pr1.setSection(cursor.getString(6));
				pr1.setAisle(cursor.getString(7));
				array.add(pr1);
			}
			
			return array;
		}
		
		return null;
		
	}
	public void resetTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.delete(TABLE_RESULTS, null, null);
		db.close();
	}

}
