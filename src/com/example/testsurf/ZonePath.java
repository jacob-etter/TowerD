package com.example.testsurf;

import android.content.Context;

public class ZonePath extends Zone{
	protected ZonePath previous;
	protected ZonePath next;
	protected double d_next[] = {-1,-1};
	public ZonePath(int left, int top, int right, int bottom, Context gamecontext) {
		super(left, top, right, bottom, gamecontext);
		ID = 1;
		background = context.getResources().getDrawable(R.drawable.pathzone);
	}
	public void setPrev(ZonePath path){
		previous = path;
	}
	public void setNext(ZonePath path){
		next = path;
	}
	public ZonePath getPrev(){
		return previous;
	}
	public ZonePath getNext(){
		return next;
	}
	public double[] getDirecNext(){
		if(d_next[0] != -1)
			return d_next;
		if(next == null){
			d_next[0] = sides[2];
			d_next[1] = (sides[1]+sides[3])/2;
		}
		else if(sides[2] == next.getSides()[0]){
			d_next[0] = sides[2];
			d_next[1] = (sides[1]+sides[3])/2;
		}
		else if(sides[1] == next.getSides()[3]){
			d_next[0] = (sides[0]+sides[2])/2;
			d_next[1] = sides[1];
		}
		else if(sides[0] == next.getSides()[2]){
			d_next[0] = sides[0];
			d_next[1] = (sides[1]+sides[3])/2;
		}
		else if(sides[3] == next.getSides()[1]){
			d_next[0] = (sides[0]+sides[2])/2;
			d_next[1] = sides[3];
		}
		return d_next;
	}
}
