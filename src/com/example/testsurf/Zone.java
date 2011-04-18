package com.example.testsurf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/*This is the class which is stored in the grid 
 * currently it just has the location of its 
 * boundaries and its color
 */
public class Zone {
	protected int[] sides;//left top right bottom
	protected int ID;
	protected Paint red = new Paint();
	protected Paint blue = new Paint();
	protected Paint green = new Paint();
	protected Paint black = new Paint();
	protected Paint white = new Paint();
	protected Paint shade = new Paint();
	protected boolean highlighted;
	protected int saleprice;
	protected int price;
	protected Context context;
	protected Drawable background;
	
	public Zone(int left,int top,int right,int bottom,Context gamecontext){
		sides = new int[4];
		highlighted = false;
		saleprice = 0;
		sides[0] = left;
		sides[1] = top;
		sides[2] = right;
		sides[3] = bottom;
		red.setARGB(255, 255, 0, 0);
		blue.setARGB(255, 0, 0, 255);
		green.setARGB(255, 0, 255, 0);
		black.setARGB(255, 0, 0, 0);
		white.setARGB(255, 255, 255, 255);
		shade.setARGB(100, 0, 0, 0);
		ID = 0;
		context = gamecontext;
	}
	public int[] getSides(){
		return sides;
	}
	public void setHighlight(){
		highlighted = true;
	}
	public void drawSelf(Canvas canvas){
		background.setBounds(sides[0], sides[1], sides[2], sides[3]);
		background.draw(canvas);
		if(highlighted == true){
			canvas.drawRect(sides[0],sides[1],sides[2],sides[3],shade);
		}
	}
	public void removeHighlight(){
		highlighted = false;
	}
	public int getID(){
		return ID;
	}
	public int getSalePrice(){
		return saleprice;
	}
}
