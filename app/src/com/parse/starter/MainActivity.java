package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	public static Typeface font_helvetica, font_pacifico;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		font_helvetica = Typeface.createFromAsset(getAssets(),"helvetica-neue-light.ttf");
		font_pacifico = Typeface.createFromAsset(getAssets(),"Pacifico.ttf");
	}

	/** Navigation methods. */
	public void onClk_Nearby (View v) {
		Intent intent = new Intent(getApplicationContext(), NearbyActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.slideleftin, R.anim.slideleftout);// LEFT
	}
	public void onClk_Search (View v) {
		Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.slideupin, R.anim.slideupout);
		
	}
	public void onClk_Catalog (View v) {
		Intent intent = new Intent(getApplicationContext(), CatalogActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.sliderightin, R.anim.sliderightout);
		
	}
	public void onClk_Add (View v) {
		Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.slidedownin, R.anim.slidedownout);
	}
}
