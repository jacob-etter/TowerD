package com.example.testsurf;

public class CreepSimple extends Creep{
	public CreepSimple(float xloc, float yloc, User gameuser,GameView view) {
		super(xloc, yloc, gameuser, view);
		health = 100;
		speed = 25;
		size = 1;
		xscale = (float) (xsize/800.0 * size);
		yscale = (float) (ysize/480.0 * size);
		creepicon = context.getResources().getDrawable(R.drawable.icon);
		moneyval = 100;
	}

}
