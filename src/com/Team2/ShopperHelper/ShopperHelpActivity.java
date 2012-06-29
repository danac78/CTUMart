package com.Team2.ShopperHelper;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShopperHelpActivity extends Activity {
    private SearchforStore findStore;
    TextView tv;
	/** Called when the activity is first created. */
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
		 /* This is the initial activity that is formed. In the Use Case Diagrams, we had a Load App that went to Check Internet and Check Compatibility.
		  * What this class does is builds the app into memory with all the parameters. At this time, the parameters had not been coded, but this is 
		  * a basic look at what the code will look like. Additionally, Activity like this is nothing more than a subclass of the Activity class, which
		  * is Android way of creating the mobile environment. */
		 
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main); /* this creating the main viewing experience. This is a standard operation in all Android Apps..it is how it creates
        							   the screens for touching or displaying.*/
        systemCheck sysCheck = new systemCheck();
        
        sysCheck.onCreate(savedInstanceState);
        try {
			setFindStore(new SearchforStore());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /* Calling the two methods in the systemChecks class as well as passing the tv variable. As both of these method require only internal variables
         * and will do a System.exit(), this is the only variable that is required to be passed along.*/
        
        
        
        
    }

	public SearchforStore getFindStore() {
		return findStore;
	}

	public void setFindStore(SearchforStore findStore) {
		this.findStore = findStore;
	}
    
       
       
}