package com.team2.shopperhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Dana Haywood
 * @version 0.1.0
 * @date 7/14/2012
 * @IT482
 * @Karl Lloyd
 * 
 * One of the main purposes of this class is to create and update the database. 
 * if the App opens and a database does not exist (first use), this class would
 * be responsible for creating it. If the version number of the database is not
 * the same as the one for the updated it app, it will be responsible for 
 * recreating it.
 */
public class CreateDatabase extends SQLiteOpenHelper {
	/*
	 * 
	 */
	static final String dbName="ctumart";
	static final String storeTbl = "StoreTBL";
	static final String storeID = "storeID";
	static final String address ="address";
	static final String secondAddress = "secondAddress";
	static final String city = "city";
	static final String state = "state";
	static final String zip = "zip";
	static final String viewStore="ViewStore";
	private String sql;

	public CreateDatabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, dbName, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		/*
		 * Retrieving the basic structure of the database from a get statement to
		 * populate the sql variable.
		 */
		sql = getSql();
		/*
		 * Running the SQL commands to create the database.
		 */
		db.execSQL(sql);
		
		/*
		 * Running the values into the database. This will not change unless we
		 * change the version for the database itself, which only occurs if we
		 * are adding new stores.
		 */
		writeValues();
	}
	

	private void writeValues() {
		ContentValues cv = new ContentValues();
		
		SQLiteDatabase dbEntry = this.getWritableDatabase();
		/*
		 * Record 1
		 */
		cv.put(storeID, 1);
		cv.put(address, "2707 W 14th Pl");
		cv.put(secondAddress, "");
		cv.put(city, "Apache Jct.");
		cv.put(state, "AZ");
		cv.put(zip, "85120");
		dbEntry.insert(storeTbl, storeID, cv);
		
		/*
		 * record 2
		 */
		
		cv.put(storeID, 2);
		cv.put(address, "385 E Cupertino Dr");
		cv.put(secondAddress, "Suite 1");
		cv.put(city, "Beverly Hills");
		cv.put(state, "CA");
		cv.put(zip, "90210");
		dbEntry.insert(storeTbl, storeID, cv);
		
		/*
		 * record 3
		 */
		
		cv.put(storeID, 3);
		cv.put(address, "111 Breckwood Blvd");
		cv.put(secondAddress, "");
		cv.put(city, "Springfield");
		cv.put(state, "MA");
		cv.put(zip, "01109");
		
		/*
		 * Closing the connection now that we are done.
		 */
		dbEntry.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * If the app notices that there is a difference between version of database
		 * (device has 1 and the app states it has 2), it will delete the tables and
		 * recreate them.
		 */
		db.execSQL("DROP TABLE IF EXISTS "+storeTbl);
		onCreate(db);

	}

	/**
	 * This method was design to create the SQL statement and return to the variable
	 * sql in order to simplify the process.
	 */
	public String getSql() {
		
		sql="CREATE TABLE "+storeTbl+" ("+storeID
				+" INTEGER PRIMARY KEY, "+
				address+" TEXT,"+secondAddress+" TEXT,"
				+city+" TEXT,"+state+" TEXT,"+zip+" TEXT)";
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

}
