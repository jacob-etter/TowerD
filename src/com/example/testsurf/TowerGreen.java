package com.example.testsurf;

import android.content.Context;

public class TowerGreen extends Tower{

	public TowerGreen(int left, int top, int right, int bottom, Context gamecontext) {
		super(left, top, right, bottom, gamecontext);
		base = context.getResources().getDrawable(R.drawable.green2);
		base.setBounds(sides[0], sides[1], sides[2], sides[3]);
		price = context.getResources().getInteger(R.integer.pricetowergreen);
		saleprice = (int) (price*.6);
	}
}
