package com.Team2.ShopperHelper;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;


/**
 * @author Dana Haywood
 * This activity was designed to check both the version of the Android (needs to be API 8 or above) and that it has an internet 
 * connection. This will be utilizing internal Android calls to manipulate. 
 * 
 * 6/29/2012: Recreated as an Activity and found examples of how to implement Connectivity Manager correctly, which was the API on 
 * Developer site for Android (at least their example). It is not creating any errors thus far. Once the GUI is formalized, the 
 * display shall be implemented.
 *
 */
public class systemCheck extends Activity {
	
	ConnectivityManager connectivity;
	NetworkInfo wifiInfo, mobileInfo;
	private String version;

	private int versionCheck;
	private boolean isWiFiConnect;
	private boolean isMobileConnect;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		
		/*Creating the variables. The first one (connectivity) will be responsible for communicating with the Android
		Connectivity API, as using methods getNetworkInfo to obtain information for wifi and mobile. */
		
		connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		mobileInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); 
		
		// calls the checkVersion method to find out if this is compatible.
		checkVersion();
		
		//checks to ensure there is an Internet connection.
		checkInternet();
	}
	private void checkVersion() {
		// capturing the version of the build.
		version = Build.VERSION.SDK;
		//version = 7; //hardcoded variable to test.
		
		/* After obtaining it, we are converting version into an integer for numerical comparison. The NumberFormatException
		 * The NumberFormatException will throw an error if the information we provide to versionCheck is not a number inside the 
		 * string. */
		try {
			versionCheck = Integer.parseInt(version);
		} catch(NumberFormatException numFormat)
		{
			System.out.print(numFormat);
		}
		
		// If the versionCheck is below API 8, it will report wrong version and exit the App.
		if (versionCheck < 8)
		{
			System.out.print("Wrong version"); // shall be further developed.
			System.exit(0);				
		}
		
	}
	private void checkInternet() {
		
		/* These two setters will configure wifiInfo and mobileInfo with the information needed to complete the task. In this case,
		 * it will gain if the wifi and mobile are connected.
		 */
		
		setWiFiConnect(wifiInfo.isConnected());
		setMobileConnect(mobileInfo.isConnected());
		
		/* if either isWifiConnect or isMobileConnect come back false (not connected), the system will report it is not connected.
		 * It will report this to the user and exist the application.
		 */
		if ((isWiFiConnect()) || (isMobileConnect()))
		{
			System.out.print("Internet Not Connected.");
			System.exit(0);
		} 		
		
	}
	public boolean isWiFiConnect() {
		return isWiFiConnect;
	}
	public boolean setWiFiConnect(boolean isWiFiConnect) {
		/*
		 * This will get the value of the connection of wifi and set it to the boolean variable. It will return that value
		 * to the procedure call.
		 */
		this.isWiFiConnect = isWiFiConnect;
		
		isWiFiConnect=wifiInfo.isConnected();
		
		return isWiFiConnect;
	}
	public boolean isMobileConnect() {
			
		
		return isMobileConnect;
	}
	public boolean setMobileConnect(boolean isMobileConnect) {
		/*
		 * This will get the value of the connection of mobile and set it to the boolean variable. It will return that value
		 * to the procedure call.
		 */
		this.isMobileConnect = isMobileConnect;
		
		isMobileConnect = mobileInfo.isConnected();
		return isMobileConnect;
	}
	

}
