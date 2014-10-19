package com.parse.starter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/** Navigation methods. */
	public void onClk_Nearby (View v) {
		Intent intent = new Intent(getApplicationContext(), NearbyActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slideup, R.anim.slideup);
	}
	public void onClk_Search (View v) {
		Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
		startActivity(intent);
		
	}
	public void onClk_Catalog (View v) {
		Intent intent = new Intent(getApplicationContext(), CatalogActivity.class);
		startActivity(intent);
		
	}
	public void onClk_Add (View v) {
		Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
		startActivity(intent);
	}
}
