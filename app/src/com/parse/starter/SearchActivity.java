package com.parse.starter;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        
        TextView butt = (TextView)findViewById(R.id.searchbar);
        butt.setText("booty");
    }
    
    // searches for information in the parse db
    public void searchColumn(String c, String q) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(q);
    	query.whereEqualTo("playerName", "Dan Stemkoski");
    	/*query.findInBackground(new FindCallback<ParseObject>() {
    	    public void done(List<ParseObject> scoreList, ParseException e) {
    	        if (e == null) {
    	            Log.d("score", "Retrieved " + scoreList.size() + " scores");
    	        } else {
    	            Log.d("score", "Error: " + e.getMessage());
    	        }
    	    }
    	});*/
    }
}
