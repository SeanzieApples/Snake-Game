package Entity;

import java.awt.Graphics;
import java.util.Random;

public class Food {
	
	int fruitWidth = 5;
	int fruitHeight = 5;
	int numFruits = 5;
	int fruitLocX = 0;
	int fruitLocY = 0;
	int timer=50;
	
	public Food(){
		makeFruitCoordinate();
	}
    
    public void setFruitCoordinate(int x, int y){
	    fruitLocX = x;
	    fruitLocY = y;
    }
    
    public void setFruitTimer(){
    	if(timer==0){
    		timer=0;
    	}else{
    		timer-=1;
    	}
    }
    
    public int getFruitTimer(){
    	return timer;
    }
    
    public int getFruitCoordinateX(){
		return fruitLocX;
    }
    
    public int getFruitCoordinateY(){
		return fruitLocY;
    }
    
    public void makeFruitCoordinate(){
	    Random num = new Random();
	    int x = (int) (Math.floor(Math.random()*49)*10+3);
	    int y = (int) (Math.floor(Math.random()*49)*10+3);
	    setFruitCoordinate(x, y);
    }
    
    
}
