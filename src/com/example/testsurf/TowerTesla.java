package com.example.testsurf;

import android.content.Context;

public class TowerTesla extends Tower {

	public TowerTesla(int left, int top, int right, int bottom, Context gamecontext) 
	{
		super(left, top, right, bottom, gamecontext);
		rng = 150;
		cooldown = 2.0;
		dmg = 60; 
		base = gamecontext.getResources().getDrawable(R.drawable.teslabase);
		base.setBounds(sides[0], sides[1], sides[2], sides[3]);
		barrel = gamecontext.getResources().getDrawable(R.drawable.teslabarrel);
		barrel.setBounds(sides[0], sides[1], sides[2], sides[3]);
		price = context.getResources().getInteger(R.integer.pricetowertesla);
		saleprice = (int) (price*.6);
	}

}
