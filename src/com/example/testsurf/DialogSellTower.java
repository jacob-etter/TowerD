package com.example.testsurf;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*This is the customDialog that will pop up when 
 * a user clicks on a zone. This dialog will then ask
 * for a tower selection and call the view to update
 */
public class DialogSellTower extends DialogTower implements OnClickListener{
	private Button ButtonYes;
	private Button ButtonNo;
	private TextView tx;
	public DialogSellTower(Context gamecontext, GameView g, int xloc, int yloc, User gameuser, ArrayList<Tower> towers) {
		super(gamecontext, g, xloc, yloc, gameuser, towers);
		setContentView(R.layout.dialogselltower);
		ButtonYes = (Button) findViewById(R.id.ButtonSellYes);
		ButtonYes.setOnClickListener(this);
		ButtonNo = (Button) findViewById(R.id.ButtonSellNo);
		ButtonNo.setOnClickListener(this);
		tx = (TextView) findViewById(R.id.saleprice);
		int saleprice = view.tiles.getGridZone(x_pos,y_pos).getSalePrice();
		String text = Integer.toString(saleprice);
		tx.setText(text);
	}

	@Override
	public void onClick(View v) {
		int [] sides = view.tiles.getGridZone(x_pos,y_pos).getSides();
		Zone empty;
		int saleprice;
		if (v == ButtonYes){
			empty = new ZoneEmpty(sides[0],sides[1],sides[2],sides[3],context);
			saleprice = view.tiles.getGridZone(x_pos,y_pos).getSalePrice();
			user.incMoney(saleprice);
			view.tiles.setGridZone(x_pos,y_pos,empty);
			dismiss();
			for(int i=0;i<towerlist.size();++i){
				if(sides == towerlist.get(i).getSides()){
					towerlist.remove(i);
				}
			}
		}
		else if (v == ButtonNo){
			dismiss();
		}
	}
}