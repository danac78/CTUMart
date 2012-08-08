package com.team2.shopperhelper.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team2.shopperhelper.R;

/**
 * @author Dana Haywood
 * @date 8/8/2012
 * @version 0.5.0
 * @Commented By:
 * @Karl Lloyd
 * @IT482
 * 
 *        As the dialog box is repeated in numerous activities, it was separated
 *        into a library to allow re-useability amongs the different activities
 *        and classes.
 * 
 */
public class DialogBox extends Activity {

	public DialogBox() {

	}

	public void postDialog(Context class1, final String msg, int stringXxlMsg) {
		/*
		 * Instantiate the dialog box with the information passed along.
		 */
		final Dialog dialog = new Dialog(class1);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle(msg);
		dialog.setCancelable(true);

		/*
		 * Creates a text view and passes the string references for the message
		 * needed to be given.
		 */
		TextView text = (TextView) dialog.findViewById(R.id.dialogTXT);

		text.setText(stringXxlMsg);
		Button button = (Button) dialog.findViewById(R.id.dialogCloseBTN);

		button.setOnClickListener(new View.OnClickListener() {
			/*
 */
			public void onClick(View v) {
				/*
				 * If the msg contains error because it is from the Launch page,
				 * it will close the application. Otherwise it will just close
				 * the dialog box.
				 */
				if (msg.contentEquals("Error")) {
					System.exit(0);
				} else {
					dialog.cancel();
				}

			}
		});

		dialog.show();

	}
}
