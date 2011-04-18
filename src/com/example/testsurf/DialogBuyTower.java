package com.example.testsurf;

import java.util.ArrayList;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*This is the customDialog that will pop up when 
 * a user clicks on a zone. This dialog will then ask
 * for a tower selection and call the view to update
 */
public class DialogBuyTower extends DialogTower implements OnClickListener{
	private Button ButtonRed;
	private Button ButtonBlue;
	private Button ButtonGreen;
	private Button ButtonCancel;
	public DialogBuyTower(Context gamecontext, GameView g, int xloc, int yloc, User gameuser,ArrayList<Tower> towers){
		super(gamecontext, g, xloc, yloc, gameuser, towers);
		/** Design the dialog in main.xml file */
		setContentView(R.layout.dialogbuytower);
		ButtonRed = (Button) findViewById(R.id.ButtonRed);
		ButtonRed.setOnClickListener(this);
		ButtonBlue = (Button) findViewById(R.id.ButtonBlue);
		ButtonBlue.setOnClickListener(this);
		ButtonGreen = (Button) findViewById(R.id.ButtonGreen);
		ButtonGreen.setOnClickListener(this);
		ButtonCancel = (Button) findViewById(R.id.ButtonCancel);
		ButtonCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int [] sides = view.tiles.getGridZone(x_pos,y_pos).getSides();
		int usermoney = user.getMoney();
		Resources res = context.getResources();
		Tower tower;
		/** When OK Button is clicked, dismiss the dialog */
		if ((v == ButtonRed)&&(usermoney >= res.getInteger(R.integer.pricetowerred))){
			user.decMoney(res.getInteger(R.integer.pricetowerred));
			tower = new TowerRifle(sides[0],sides[1],sides[2],sides[3],context);
			towerlist.add((Tower) tower);
			view.tiles.setGridZone(x_pos,y_pos,tower);
			dismiss();
		}
		else if ((v == ButtonBlue)&&(usermoney >= res.getInteger(R.integer.pricetowerblue))){
			user.decMoney(res.getInteger(R.integer.pricetowerblue));
			tower = new TowerMachineGun(sides[0],sides[1],sides[2],sides[3],context);
			towerlist.add((Tower) tower);
			view.tiles.setGridZone(x_pos,y_pos,tower);
			dismiss();
		}
		else if ((v == ButtonGreen)&&(usermoney >= res.getInteger(R.integer.pricetowergreen))){
			user.decMoney(res.getInteger(R.integer.pricetowergreen));
			tower = new TowerGreen(sides[0],sides[1],sides[2],sides[3],context);
			towerlist.add((Tower) tower);
			view.tiles.setGridZone(x_pos,y_pos,tower);
			dismiss();
		}
		else if (v == ButtonCancel){
			dismiss();
		}
	}
}