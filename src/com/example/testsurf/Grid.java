package com.example.testsurf;

import android.content.Context;

public class Grid {
	private Zone[][] grid;
	public Grid(int xnum,int ynum,int xsize, int ysize,Context gamecontext){
		grid = new Zone[xnum][ynum];
		for(double i = 0; i < xnum;++i){
			for(double j = 0; j < ynum; ++j){
				int left = (int)((i*xsize)/xnum);
				if(left < 0)
					left = 0;
				int top = (int)(((j)*ysize)/ynum);
				if (top > ysize)
					top = ysize;
				int right = (int) ((i+1)*xsize/xnum);
				if(right > xsize)
					right = xsize;
				int bottom = (int) (((j+1)*ysize)/ynum);
				if(bottom < 0)
					bottom = 0;
				grid[(int)i][(int)j] = new ZoneEmpty(left,top,right,bottom,gamecontext);
			}
		}
		return;
	}
	public Zone getGridZone(int x, int y){
		return grid[x][y];
	}
	public void setGridZone(int x, int y, Zone object){
		grid[x][y] = object;
	}

}
