package com.example.testsurf;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class DialogTower extends Dialog{
	protected GameView view;
	protected int x_pos;
	protected int y_pos;
	protected User user;
	protected Context context;
	protected ArrayList<Tower> towerlist;
	public DialogTower(Context gamecontext, GameView g, int xloc, int yloc, User gameuser,ArrayList<Tower> towers){
		super(gamecontext);
		towerlist = towers;
		context = gamecontext;
		user = gameuser;
		x_pos = xloc;
		y_pos = yloc;
		view = g;
		user = gameuser;
		/** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/** Design the dialog in main.xml file */
	}
}

