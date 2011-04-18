package com.example.testsurf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Bullet {
	protected Creep target;
	protected double pos_x;
	protected double pos_y;
	protected double speed;
	protected long old_time;
	protected boolean alive;
	protected Drawable bullet;
	protected Context context;
	protected double size;
	protected double xscale;
	protected double yscale;
	protected int xsize;
	protected int ysize;
	protected boolean x_side = false;
	protected boolean y_side = false;
	public Bullet(int x, int y, Creep creep_target,GameView view){
		context = view.getContext();
		xsize = view.getWidth();
		ysize = view.getHeight();
		pos_x = x;
		pos_y = y;
		target = creep_target;
		old_time = System.currentTimeMillis();
		if(pos_x>target.getPosX())
			x_side = true;
		if(pos_y>target.getPosY())
			y_side = true;
		alive = true;
	}
	public void move(long currenttime){ 
		if(alive){
			double tar_x;
			double tar_y;
			long diff = currenttime-old_time;
			tar_x = target.getPosX();
			tar_y = target.getPosY();
			if(((pos_x<tar_x)==x_side)||((pos_y<tar_y)==y_side)){
				alive = false;
				return;
			}
			else if(target.getAlive()==false){
				alive = false;
				return;
			}
			tar_x = (tar_x-pos_x);
			tar_y = (tar_y-pos_y);
			pos_x += speed*diff/1000*(tar_x/(Math.abs(tar_y)+Math.abs(tar_x)));
			pos_y += speed*diff/1000*(tar_y/(Math.abs(tar_y)+Math.abs(tar_x)));
		}
	}
	public void drawSelf(Canvas canvas){
		if(alive){
			double tar_x;
			double tar_y;
			tar_x = target.getPosX();
			tar_y = target.getPosY();
			if(((pos_x<tar_x)==x_side)||((pos_y<tar_y)==y_side)){
				alive = false;
				return;
			}
			int top = (int) (pos_y-yscale);
			int bottom = (int) (pos_y+yscale);
			int right = (int) (pos_x+xscale);
			int left = (int) (pos_x-xscale);
			bullet.setBounds(left, top, right, bottom);
			bullet.draw(canvas);
		}
	}
	public boolean getAlive(){
		return alive;
	}

}
