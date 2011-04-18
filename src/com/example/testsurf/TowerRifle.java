package com.example.testsurf;

import android.content.Context;

public class TowerRifle extends Tower 
{
	public TowerRifle(int left, int top, int right, int bottom,	Context gamecontext) 
	{
		super(left, top, right, bottom, gamecontext);
		rng = 300;
		cooldown = 1.5;
		dmg = 50; 
		base = gamecontext.getResources().getDrawable(R.drawable.riflebase);
		base.setBounds(sides[0], sides[1], sides[2], sides[3]);
		barrel = gamecontext.getResources().getDrawable(R.drawable.riflebarrel);
		barrel.setBounds(sides[0], sides[1], sides[2], sides[3]);
		price = context.getResources().getInteger(R.integer.pricetowerrifle);
		saleprice = (int) (price*.6);
	}
}
