package com.example.testsurf;

import android.content.Context;

public class TowerRed extends Tower{

	public TowerRed(int left, int top, int right, int bottom, Context gamecontext) {
		super(left, top, right, bottom, gamecontext);
		price = context.getResources().getInteger(R.integer.pricetowerred);
		base = context.getResources().getDrawable(R.drawable.red2);
		base.setBounds(sides[0], sides[1], sides[2], sides[3]);
		saleprice = (int) (price*.6);
	}
}
