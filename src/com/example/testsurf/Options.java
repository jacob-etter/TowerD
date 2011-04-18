package com.example.testsurf;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Options extends Menu {

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        //force landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
    }
}