package Entity;

public class Player {
	int segWidth = 5;
	int segHeight = 5;
	int segLocX = 0;
	int segLocY = 0;
	int segPrevLocX = 0;
	int segPrevLocY = 0;
	
	public Player(int x, int y){
		setSegLocation(x, y);
	}
	
    public void setSegLocation(int x, int y){
    	segLocX = x;
    	segLocY = y;
    }
    
    public void setPrevLocation(int x, int y){
    	segPrevLocX = x;
    	segPrevLocY = y;
    }
    
    public int getSegCoordinatePrevX(){
    	return segPrevLocX;
    }
    
    public int getSegCoordinatePrevY(){
    	return segPrevLocY;
    }
    
    public int getSegCoordinateX(){
		return segLocX;
    }
    
    public int getSegCoordinateY(){
		return segLocY;
    }
    
    public void makeSegCoordinate(int x, int y){

    }
}
