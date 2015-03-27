package Entity;

import java.awt.Graphics;
import java.util.Random;

public class Bombs {
	int bombSegWidth = 5;
	int bombSegHeight = 5;
	int numBombs = 0;
	int bombLocX = 0;
	int bombLocY = 0;
	int bombtimer=50;
	
	public Bombs(){
		makeBombCoordinate();
	}
    
    public void setBombCoordinate(int x, int y){
    	bombLocX = x;
    	bombLocY = y;
    }
    
    public void setBombTimer(){
    	bombtimer-=1;
    }
    
    public int getBombTimer(){
    	return bombtimer;
    }
    
    public int getBombCoordinateX(){
		return bombLocX;
    }
    
    public int getBombCoordinateY(){
		return bombLocY;
    }
    
    public void makeBombCoordinate(){
	    Random num = new Random();
	    int x1 = (int) (Math.floor(Math.random()*49)*10+3);
	    int y1 = (int) (Math.floor(Math.random()*49)*10+3);
	    setBombCoordinate(x1, y1);
    }
}
