package com.example.testsurf;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private GameThread _thread;
	private Paint text;
	private int[][] paths = {{0,1,2,3,3,3,4,5,5,5,6,7,7,7,7,7,8,9,10,11,12,13,14,15},{5,5,5,5,6,7,7,7,6,5,5,5,4,3,2,2,2,2,2,2,2,2,2,2}};
    public int xsize = 16;//size of grid in x
    public int ysize= 10;//size of grid in y
    public Grid tiles; //grid of zones
    private int initiate=0;
	public User user;
	public ArrayList<Creep> creeplist = new ArrayList<Creep>();
	public ArrayList<Tower> towerlist = new ArrayList<Tower>();
	public ArrayList<ZonePath> pathlist = new ArrayList<ZonePath>();
	public ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();

    public GameView(Context context) {
        super(context); 
        getHolder().addCallback(this);
        _thread = new GameThread(getHolder(), this);
        setFocusable(true);
		user = new User(5000, 0, 10, "Sean");
		text = new Paint();
		text.setARGB(255, 0, 0, 0);
		text.setTextSize(40);
		text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	return _thread.doTouchEvent(event);
    }

    @Override
    public void onDraw(Canvas canvas) {
		if(initiate == 0){
				initilize();
		}
		Drawable background;
		background = getContext().getResources().getDrawable(R.drawable.simplebackground);
		background.setBounds(0, 0, getWidth(), getHeight());
		background.draw(canvas);
        drawZones(canvas);
        drawBullets(canvas);
        drawCreeps(canvas);
        drawUserInfo(canvas);
    }
    protected void initilize(){
		tiles = new Grid(xsize,ysize,getWidth(),getHeight(),getContext());
		for(int i=0;i<paths[0].length;++i){
			int[] sides = tiles.getGridZone(paths[0][i], paths[1][i]).getSides();
			ZonePath path = new ZonePath(sides[0],sides[1],sides[2],sides[3],getContext());
			tiles.setGridZone(paths[0][i], paths[1][i], path);
			pathlist.add(path);
		}
		for(int i = 0; i<pathlist.size();++i){
			if(i == 0){
				pathlist.get(i).setPrev(null);
				pathlist.get(i).setNext(pathlist.get(i+1));
			}
			else if (i==pathlist.size()-1){
				pathlist.get(i).setPrev(pathlist.get(i-1));
				pathlist.get(i).setNext(null);
			}
			else{
				pathlist.get(i).setPrev(pathlist.get(i-1));
				pathlist.get(i).setNext(pathlist.get(i+1));
			}
		}
		initiate = 1;
    }
	protected void drawZones(Canvas canvas){
		for(int i = 0;i<xsize;++i){ 
			for(int j=0;j<ysize;++j){
				tiles.getGridZone(i,j).drawSelf(canvas);
			}
		}

	}
	protected void drawCreeps(Canvas canvas){
    	for(int i =0; i<creeplist.size();++i){
    		creeplist.get(i).drawSelf(canvas);
    	}
    	for(int i = 0;i<creeplist.size();++i){
    		if(creeplist.get(i).getAlive()==false){
    			creeplist.remove(i);
    		}
    	}
	}
	protected void drawBullets(Canvas canvas){
		for(int i=0; i<bulletlist.size();++i){
			bulletlist.get(i).drawSelf(canvas);
		}
		for(int i=0; i<bulletlist.size();++i){
			if(bulletlist.get(i).getAlive()==false){
				bulletlist.remove(i);
			}
		}
	}
	protected void drawUserInfo(Canvas canvas){
		String userscore = Integer.toString(user.getScore());
		String money = Integer.toString(user.getMoney());
		String lives = Integer.toString(user.getLives());
		canvas.drawText("  Score = " + userscore + " Money = " + money+" Lives = "+lives,0, getHeight()/ysize-15,text);
	}
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread.setRunning(true);
        _thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // simply copied from sample application LunarLander:
        // we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }
    }
    
    public GameThread getThread(){
    	return _thread;
    }
    public Grid getGrid(){
    	return tiles;
    }
    public int[] getGridSize(){
    	int[] size = {xsize,ysize};
    	return size;
    }
}

