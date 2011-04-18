package com.example.testsurf;

import android.content.Context;

public class TowerMachineGun extends Tower 
{
	public TowerMachineGun(int left, int top, int right, int bottom, Context gamecontext) 
	{
		super(left, top, right, bottom, gamecontext);
		rng = 150;
		cooldown = 0.2; // This is the number of seconds between shots
		dmg = 5;
		base = gamecontext.getResources().getDrawable(R.drawable.machinegunbase);
		base.setBounds(sides[0], sides[1], sides[2], sides[3]);
		barrel = gamecontext.getResources().getDrawable(R.drawable.machinegunbarrel);
		barrel.setBounds(sides[0], sides[1], sides[2], sides[3]);
		price = context.getResources().getInteger(R.integer.pricetowermachinegun);
		saleprice = (int) (price*.6);
	}
}
