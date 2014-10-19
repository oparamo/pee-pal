package com.parse.starter;

import com.parse.starter.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

public class UploadActivity extends Activity
{
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);
	}
	//hello
	/** Creates and intent to take a picture and return control to calling 
	  * application. Then starts the intent.
	  * @param v - view, used by layout */
	public void onClk_TakePicture(View v) {
		// create intent to take a picture and return control to calling application.
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// start the image capture intent
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	@Override
	/** Gets the resulting image after the picture has been taking. Called
	  * after the image is taken. */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) { 
				// Changes name of label for now
				TextView txtvw = (TextView)findViewById(R.id.Location);
				txtvw.setText("Image captured!");
			}
		}
	}
	
	public void onClk_SubmitPicture(View v) {
		
	}
	
	
}
