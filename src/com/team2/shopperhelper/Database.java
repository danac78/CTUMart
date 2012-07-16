package com.team2.shopperhelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	
	private static String DB_PATH = "/data/data/com.team2.shopperhelper/databases/";
	private static String DB_NAME = "store";
	String thePath = DB_PATH+DB_NAME;
	private SQLiteDatabase storeDatabase;
	private final Context thisContext;
    protected SQLiteDatabase checkDB=null;
    String tbl="storeTbl";
    String id;
    String[] address;
    String secondAddress;
    String city;
    String state;
    String zip;
	public Database(Context context) {
		super(context, DB_NAME, null, 1);
		this.thisContext=context;
		
	}
	
	public void createDataBase() throws IOException
	{
		boolean dbExist = checkDataBase();
		
		if(dbExist)
		{
			
		} else {
			
			this.getReadableDatabase();
			
			copyDataBase();
		}
	}

	@SuppressLint({ "ParserError", "ParserError" })
	private boolean checkDataBase() {
		
		try
		{
			checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			
		}
		
		if(checkDB !=null)
		{
			checkDB.close();
		}
		return checkDB != null ?true:false;
	}

	private void copyDataBase() throws IOException{
		
		InputStream myInput = thisContext.getAssets().open(DB_NAME);
		
		String outFileName = thePath;
		
		OutputStream theOutput = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		
		int length;
		
		while ((length=myInput.read(buffer))>0)
		{
			theOutput.write(buffer,0,length);
		}
		
		theOutput.flush();
		theOutput.close();
		myInput.close();
	}
	
	
	public SQLiteDatabase openDB() throws SQLException
	{
		storeDatabase=SQLiteDatabase.openDatabase(thePath, null, SQLiteDatabase.OPEN_READONLY);
		return storeDatabase;
	}
	
	
	
	@Override
	public synchronized void close()
	{
		if(storeDatabase != null)
		{
			storeDatabase.close();
		}
		
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion==0 && newVersion==1)
		{
			onCreate(db);
		}
		
	}
	
	

}
