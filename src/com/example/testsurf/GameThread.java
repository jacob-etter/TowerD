package com.example.testsurf;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

class GameThread extends Thread {
    private SurfaceHolder _surfaceHolder;
    private GameView _view;
    private boolean _run = false;
    private boolean paused = false;
    private int xpress;
    private int ypress;
    public long click_time;
    private long creep_timer=0;

    public GameThread(SurfaceHolder surfaceHolder, GameView panel) {
        _surfaceHolder = surfaceHolder;
        _view = panel;
    }

    public void setRunning(boolean run) {
        _run = run;
    }

    public SurfaceHolder getSurfaceHolder() {
        return _surfaceHolder;
    }
    public void updateGame(){
    	long current_time = System.currentTimeMillis();
    	for(int i =0; i<_view.creeplist.size();++i){
    		_view.creeplist.get(i).move(current_time);
    	}
    	for(int i=0;i<_view.bulletlist.size();++i){
    		_view.bulletlist.get(i).move(current_time);
    	}
    	for(int i=0;i<_view.towerlist.size();++i){
    		_view.towerlist.get(i).fire(_view.creeplist,_view.bulletlist,_view);
    	}
    	if((current_time - creep_timer)>5000){
    		if(_view.pathlist.size()>0){
	    		int x = _view.pathlist.get(0).getSides()[0];
	    		int y = (_view.pathlist.get(0).getSides()[1]+_view.pathlist.get(0).getSides()[3])/2;
				_view.creeplist.add(new CreepSimple(x, y,_view.user, _view));
				creep_timer = current_time; 
    		}
    	}
    }
    public boolean doTouchEvent(MotionEvent event) {
        int old_xpress;
        int old_ypress;
    	synchronized (_surfaceHolder) {
    		if (event.getAction() == MotionEvent.ACTION_DOWN){
    			ypress  = (int) event.getY();
    			xpress = (int) event.getX();
    			xpress = xpress/(_view.getWidth()/_view.xsize);
    			ypress = ypress/(_view.getHeight()/_view.ysize);
    			_view.tiles.getGridZone(xpress,ypress).setHighlight();
    			click_time = System.currentTimeMillis();
    		} 
    		else if (event.getAction() == MotionEvent.ACTION_MOVE){
    			old_xpress = xpress;
    			old_ypress = ypress;
    			_view.tiles.getGridZone(xpress,ypress).removeHighlight();
    			ypress  = (int) event.getY();
    			xpress = (int) event.getX();
    			xpress = xpress/(_view.getWidth()/_view.xsize);
    			ypress = ypress/(_view.getHeight()/_view.ysize);
    			_view.tiles.getGridZone(xpress,ypress).setHighlight();
    			if((old_xpress != xpress)&&(old_ypress!=ypress)){
    				click_time = System.currentTimeMillis();
    			}
    		}
        	else if (event.getAction() == MotionEvent.ACTION_UP){
    			_view.tiles.getGridZone(xpress,ypress).removeHighlight();
    			if((System.currentTimeMillis()-click_time )> 250){
    				ypress  = (int) event.getY();
    				xpress = (int) event.getX();
    				xpress = xpress/(_view.getWidth()/_view.xsize);
    				ypress = ypress/(_view.getHeight()/_view.ysize);
    				if(_view.tiles.getGridZone(xpress,ypress).getID()==2){
    					DialogBuyTower customDialog = new DialogBuyTower(_view.getContext(), _view, xpress, ypress,_view.user,_view.towerlist);
    					customDialog.show();
    				}
    				else if(_view.tiles.getGridZone(xpress,ypress).getID()>2){
    					DialogSellTower dialogselltower = new DialogSellTower(_view.getContext(), _view,xpress,ypress,_view.user,_view.towerlist);
    					dialogselltower.show();
    				}
    			}
    		}
    		return true;
        }
    }
    @Override
    public void run() {
        Canvas c;
        while (_run) {
            c = null;
            try {
                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {
                	if(paused==false){
                		updateGame();
                	}
                    _view.onDraw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    _surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
