package com.example.testsurf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Creep {
	protected double pos_x;
	protected double pos_y;
	protected int xsize;
	protected int ysize;
	protected double xscale;
	protected double yscale;
	protected int direction;
	protected double speed;
	protected long old_time;
	protected double size;
	protected Context context;
	protected Drawable creepicon;
	protected User user;
	protected boolean alive = true;
	protected int health;
	protected GameView gameview;
	protected int moneyval;
	public Creep(float xloc, float yloc,User gameuser, GameView view){
		gameview= view;
		context = view.getContext();
		xsize = view.getWidth();
		ysize = view.getHeight();
		direction = 1;
		alive = true;
		pos_x = xloc;
		pos_y = yloc;
		user = gameuser;
		old_time = System.currentTimeMillis();
	}
	public void move(long current_time){
		if(alive){
			int x= (int) (pos_x/(xsize/gameview.getGridSize()[0]));
			int y = (int) (pos_y/(ysize/gameview.getGridSize()[1]));
			if(x > gameview.xsize){
				alive = false;
				user.decLives(1);
				return;
			}
			ZonePath path = (ZonePath) gameview.getGrid().getGridZone(x, y);
			if(path.getID()!=1){
				alive = false;
				return;
			}
			double[] tar = path.getDirecNext();
			long diff = current_time - old_time;
			double tar_x = (tar[0]-pos_x);
			double tar_y = (tar[1]-pos_y);
			double pos_x1 = speed*diff/1000*(tar_x/(Math.abs(tar_y)+Math.abs(tar_x)));
			double pos_y1 = speed*diff/1000*(tar_y/(Math.abs(tar_y)+Math.abs(tar_x)));
			pos_x += pos_x1;
			pos_y += pos_y1;
			if(pos_x > xsize){
				alive = false;
				user.decLives(1);
				return;
			}
			old_time = current_time;
		}
	}
	public void drawSelf(Canvas canvas){
		if(alive){
			int top = (int) (pos_y-10*yscale);
			int bottom = (int) (pos_y+10*yscale);
			int right = (int) (pos_x+10*xscale);
			int left = (int) (pos_x-10*xscale);
			if(left > xsize){
				alive = false;
				user.decLives(1);
				return;
			}
			creepicon.setBounds(left, top, right, bottom);
			creepicon.draw(canvas);
		}
	}
	public void decHealth(int value){
		health -= value;
		if(health < 0){
			alive = false;
			user.incMoney(moneyval);
			user.incScore(1);
		}
	}
	public double getPosX(){
		return pos_x;
	}
	public double getPosY(){
		return pos_y;
	}
	public boolean getAlive(){
		return alive;
	}
}
