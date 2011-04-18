package com.example.testsurf;

import android.content.Context;

public class ZoneEmpty extends Zone{
	public ZoneEmpty(int left, int top, int right, int bottom, Context gamecontext) {
		super(left, top, right, bottom, gamecontext);
		ID = 2;
		background = context.getResources().getDrawable(R.drawable.emptyzone);
	}
}
