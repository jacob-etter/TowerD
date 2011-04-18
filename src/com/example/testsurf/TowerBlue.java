package com.example.testsurf;

import android.content.Context;

public class TowerBlue extends Tower{

	public TowerBlue(int left, int top, int right, int bottom, Context gamecontext) {
		super(left, top, right, bottom, gamecontext);
		base = context.getResources().getDrawable(R.drawable.blue2);
		price = context.getResources().getInteger(R.integer.pricetowerblue);
		base.setBounds(sides[0], sides[1], sides[2], sides[3]);
		saleprice = (int) (price*.6);
	}
}
