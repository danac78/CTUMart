package com.Team2.ShopperHelper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

public class systemChecks {
	/* This class is meant to check for compatibility. At this time, it is using System.out.print, but it will be able to print to the screen
	 * It is using two variables defined. One is to capture version as a String (it will get the API level for ease of use. Since API level is designated 
	 * each version level, we will be going with 8. versionCheck is to convert that string to int for easier comparsion*/
	
	String version;
	int versionCheck;
	
	/* We will be calling the ConnectivityManager API for the NetworkInfo method. ConnectivityManager is Android's Network API that allows viewing the 
		 * web, checking connections, etc.*/
	ConnectivityManager connMgr = new ConnectivityManager();
	
	/* NetworkInfo is a Method without the ConnectivityManager API that can be used to gain information about network settings and connectivity.
		 * We are going to use this to check to see if the mobile devices has a connection prior to load completion.*/
	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); 
	
	public systemChecks()
	{
	}
	
	public void versionCheck(TextView tv)
	{
		// captures the build of the API. In the future, we could hard value this for testing purposes.
		
		version = Build.VERSION.SDK;
		/* A hard code version that will be uncommented when we are testing. It was purposely set to 7 as this will check to ensure that the code will function properly. 
		 * To save on complication, we will have one simulation with a set of variables based on a common phone. We will build more simulation in the future, but for now
		 * we will use a hard code.*/
		//version = "7";
		
		/* This is in a Try Catch in order to ensure any exceptions can be meant. Probably going to change the exception as it was based on a Double.
		 * What is occurring is that it is converting the String version into an integer. If it runs into a problem, the catch will see it and report
		 * the exception*/
		try
		{
			versionCheck= Integer.parseInt(version);
			
		} catch(NumberFormatException numFormat)
		{
			System.out.print("Something wrong");
			
		}
		
		/* As the goal is to see if this is below 2.2, it is checking to see if the API is less than 8. If it is, it will report a problem (might come
		 * up with a more detailed message when we do more printing the UI, but this will tell the system to exit.*/
		if(versionCheck < 8)
		{
			System.out.print("Problem");
			System.exit(0);
		}
	}
	
	public void internetCheck(TextView tv)
	{
		/* What is occurring is we are checking for an Internet connection. If the Internet is available, it will just exit out of the method. If it not 
		 * able to sustain a connection, it will report a problem and exit the program.*/
		
		/* Checking to see if the network info is not null (blank settings) and that it is connected. If it is true, nothing will displayed. We want this
		 * to be as transparent as possible. If there are no problems, it will just go to the main menu without the customer noticing. The System.out.print() is
		 * a place holder for the dialog box once we get into more touchscreen. This is basic logic to be used for this function.*/
		if (networkInfo != null && networkInfo.isConnected())
		{
			// nothing will be said..unless we change our minds
		} else {
			System.out.print("Problem with the Internet");
			System.exit(0);
		}
	}

	
}			
		

	
	

