package com.example.testsurf;

import java.util.ArrayList;

import android.content.Context;

public class TowerTesla extends Tower 
{
	
	public Creep targets[];
	

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
	protected void findTarget(ArrayList<Creep> creeplist) 
	{
		// Internal Vars
		
		Creep creep;
		double dist = 0.0;
		double creep_x = 0;
		double creep_y = 0;
		double dx = 0;
		double dy = 0;
		for(int i=0;i<creeplist.size();++i) 
		{
			creep = creeplist.get(i);
			creep_x = creep.getPosX();
			creep_y = creep.getPosY();
			dx = (pos_x - creep_x);
			dy = (pos_y - creep_y);
			dist = Math.sqrt( (dx*dx) + (dy*dy) );
			if (dist < rng) 
			{
				targets[targets.length] = creep;
				if(targets.length >= 1)
				{
					return;
				}
			}
		}
		return;
	} 
	public void fire(ArrayList<Creep> creeplist, ArrayList<Bullet> bulletlist, GameView view) 
	{
		long currenttime=System.currentTimeMillis();
		if(targets == null || targets.length < 1)
		{
			findTarget(creeplist);
		}
		if(((currenttime-last_fire)) >= cooldown*1000)
		{
			for(int i = 0; i < targets.length-1; i++)
			{
				bulletlist.add(new BulletSimple(pos_x, pos_y, cur_target,view));
				if(targets[i] != null)
				{	
					targets[i].decHealth(dmg);
				}
			}
			last_fire = currenttime;
		}
		for(int i = 0; i < targets.length-1; i++)
		{
			if(targets[i] != null && targets[i].getAlive() == false)
				targets[i] = null;
		}
		
		return;
	}
	
	

}
