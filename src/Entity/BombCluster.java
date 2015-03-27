package Entity;

public class BombCluster extends Bombs{
	Bombs bomb = new Bombs();
	
	int x1;
	int y1; 
	int x2; 
	int y2; 
	int x3; 
	int y3; 
	int x4; 
	int y4;
	
	public BombCluster(){
		makeBombCluster();
	}
	
	public void makeBombCluster(){
		x1=bomb.getBombCoordinateX();
		y1=bomb.getBombCoordinateY();
		x2=bomb.getBombCoordinateX()+10;
		y2=bomb.getBombCoordinateY();
		x3=bomb.getBombCoordinateX()+10;
		y3=bomb.getBombCoordinateY()+10;
		x4=bomb.getBombCoordinateX();
		y4=bomb.getBombCoordinateY()+10;
	}
	
	public int getBombClusterX1(){
		return x1;
	}
	public int getBombClusterY1(){
		return y1;
	}
	public int getBombClusterX2(){
		return x2;
	}
	public int getBombClusterY2(){
		return y2;
	}
	public int getBombClusterX3(){
		return x3;
	}
	public int getBombClusterY3(){
		return y3;
	}
	public int getBombClusterX4(){
		return x4;
	}
	public int getBombClusterY4(){
		return y4;
	}
	

}
