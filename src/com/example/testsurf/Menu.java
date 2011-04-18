package com.example.testsurf;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class Menu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //force landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    Button options = (Button) findViewById(R.id.optionsButton);
    options.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent myIntent = new Intent(view.getContext(), Options.class);
            startActivityForResult(myIntent, 0);
        }
    });
    
    Button play = (Button) findViewById(R.id.playButton);
    play.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent myIntent = new Intent(view.getContext(), Main.class);
            startActivityForResult(myIntent, 0);
        }
    });
    }
}
