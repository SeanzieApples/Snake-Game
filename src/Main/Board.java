package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.BombCluster;
import Entity.Food;
import Entity.Player;

public class Board extends JPanel implements ActionListener, KeyListener{
	//dimensions
	public int height=500;
	public int width=500;
	public int speed=300;
	Timer tm = new Timer(speed, this);
	
	int score=0;
	int x=3;
	int y=3;
	int plWidth = 5;
	int plHeight = 5;
	int numFruits=5;
	int prevX;
	int prevY;
	int newFoodTime=0;
	int prevScore;
	int die;
	int prevBombScore=0;
	String direction="notMoved";
	boolean segexists=false;
	boolean defeat=false;

	int velx =0;
	int vely =0;
	boolean isFruitInitialized=false;
	ArrayList<Food> food = new ArrayList<Food>();
	ArrayList<Player> segment = new ArrayList<Player>();
	ArrayList<BombCluster> bomb = new ArrayList<BombCluster>();
	
	public Board(){
		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

    public void paintComponent(Graphics g) {
    	if(!defeat){
	    	super.paintComponent(g);
			draw(g);
	    	initFruit(g);
	    	drawHead(g);
	    	paintSegment(g);
	    	drawScore(g);
	    	paintBombCluster(g);
    	}else if(defeat){
    		g.clearRect(0, 0, 500, 500);
    		retry(g);
    	}
    }
    
    public void retry(Graphics g){
    	g.drawString("You have been defeated bitch!", 150, 150);
    	g.drawString("Retry? Y/N", 150, 170);
    	g.drawString("Score:", 150, 190);
    }
    
    public void drawHead(Graphics g){
    	for(int i=0; i<segment.size(); i++){
    		if(x==segment.get(i).getSegCoordinateX() && y==segment.get(i).getSegCoordinateY()){
    			defeat=true;
    		}
    	}
    	for(int i=0; i<bomb.size(); i++){
    		if((x==bomb.get(i).getBombClusterX1() && y==bomb.get(i).getBombClusterY1()) || (x==bomb.get(i).getBombClusterX2() && y==bomb.get(i).getBombClusterY2()) || (x==bomb.get(i).getBombClusterX3() && y==bomb.get(i).getBombClusterY3()) || (x==bomb.get(i).getBombClusterX4() && y==bomb.get(i).getBombClusterY4())){
    			defeat=true;
    		}
    	}
    	g.setColor(Color.DARK_GRAY);
    	g.fillRect(x, y, plWidth, plHeight);
    }
    
    public void drawScore(Graphics g){
    	g.drawString("Score "+score, 10, 10);
    }
    
    public void initFruit(Graphics g){
    	if(isFruitInitialized == false){
	    	for(int i=0; i<numFruits; i++) { 
	    		food.add(i, new Food());
	    		System.out.println("Food generated at"+" "+ food.get(i).getFruitCoordinateX()+" "+food.get(i).getFruitCoordinateY());
	    		paintFruit(g, food.get(i).getFruitCoordinateX(), food.get(i).getFruitCoordinateY());
	    	}
    	}else{
    		for(int i=0; i<food.size(); i++){
    			food.get(i).setFruitTimer();
    			willFruitDie(i);
    			paintFruit(g, food.get(i).getFruitCoordinateX(), food.get(i).getFruitCoordinateY());
    		}
    	}
    	isFruitInitialized = true;
    }
    
    public void willFruitDie(int i){
    	if(food.get(i).getFruitTimer()==0){
    		rollFruitDice();
    		if(die==3){
    			food.remove(i);
    			die=0;
    		}
    	}
    }
    
    public void rollFruitDice(){
    	die = (int) (Math.floor(Math.random()*5));
    }
    
    public void addFruit(){
    	food.add(new Food());
    }
    
    public void addBombCluster(){
    	if (score%5==0 && prevBombScore != score){
    			bomb.add(new BombCluster());
    			prevBombScore=score;
        }
    }
    
    public void paintBombCluster(Graphics g){
    	g.setColor(Color.RED);
    	for(int i=0; i<bomb.size(); i++){
    		g.fillRect(bomb.get(i).getBombClusterX1(), bomb.get(i).getBombClusterY1(), 5, 5);
    		g.fillRect(bomb.get(i).getBombClusterX2(), bomb.get(i).getBombClusterY2(), 5, 5);
    		g.fillRect(bomb.get(i).getBombClusterX3(), bomb.get(i).getBombClusterY3(), 5, 5);
    		g.fillRect(bomb.get(i).getBombClusterX4(), bomb.get(i).getBombClusterY4(), 5, 5);
    	}
    }
    
    public void newFoodTimer(){
    	newFoodTime+=1;
    	if (newFoodTime==20){
    		addFruit();
    		newFoodTime=0;
    	}
		if (score % 5==0 && prevScore != score){
    		addFruit();
    		prevScore=score;
    	}
    }
    
    public void paintFruit(Graphics g, int x, int y){
    	g.setColor(Color.MAGENTA);
    	g.fillRect(x, y, 5, 5);
    }
    
    public void paintSegment(Graphics g){
    	for(int i=0; i<segment.size(); i++){
	    	g.setColor(Color.black);
	    	g.fillRect(segment.get(i).getSegCoordinateX(), segment.get(i).getSegCoordinateY(), 5, 5);
    	}
    }
    
    public void updateSegment(int x, int y){
    	for(int i=0; i<segment.size(); i++){
    		segment.get(i).setPrevLocation(segment.get(i).getSegCoordinateX(), segment.get(i).getSegCoordinateY());
    		if(i==0){
    			x=prevX;
    			y=prevY;
    		}else{
    			int k=i-1;
    			x=segment.get(k).getSegCoordinatePrevX();
    	    	y=segment.get(k).getSegCoordinatePrevY();
    		}
	    	segment.get(i).setSegLocation(x, y);	
    	}
    	if(!segexists){
    		segexists=true;
    	}
		newFoodTimer();
    }
    
    public void resetEverything(){
    	food.clear();
    	bomb.clear();
    	segment.clear();
    	score=0;
    	x=3;
    	y=3;
    	velx=0;
    	vely=0;
    	prevBombScore=0;
    	prevScore=0;
    	isFruitInitialized=false;
    	prevX=0;
    	prevY=0;
    	newFoodTime=0;
    	prevScore=0;
    	die=0;
    	prevBombScore=0;
    	direction="notMoved";
    	speed=300;
    	segexists=false;
    	defeat=false;
    	tm.setDelay(speed);
    }
    
    public void addSegment(int x, int y){
    	segment.add(new Player(x, y));
    	System.out.println("Adding segment at "+x+" "+y);
    }

	public void actionPerformed(ActionEvent e) {
		prevX = x;
		prevY = y;
		if (x<3){
			velx = 0;	
			x = 3;
		}
		if (x>483){
			velx = 0;
			x = 483;
		}
		if (y>483){
			vely = 0;
			y = 483;
		}
		if (y<3){
			vely = 0;
			y = 3;
		}
		x = x + velx;
		y = y + vely;
		playerEatingFruit(x, y);
		updateSegment(x, y);
		addBombCluster();
		System.out.println("Player at"+" "+x+" "+y);
		repaint();
	}
	
	public void playerEatingFruit(int x, int y){
		for(int i=0; i<food.size(); i++){
			if(food.get(i).getFruitCoordinateX()==x && food.get(i).getFruitCoordinateY()==y){
				food.remove(i);
				score++;
				addSegment(prevX, prevY);
				speed-=5;
				tm.setDelay(speed);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_LEFT){
			if(segexists){
				if(direction == "right"){
					direction="right";
				}else{
					velx = -10;
					vely = 0;
					direction="left";
				}
			}else{
				velx = -10;
				vely = 0;
				direction="left";
			}
		}
		if(c == KeyEvent.VK_UP){
			if(segexists){
				if(direction == "down"){
					direction="down";
				}else{
					velx = 0;
					vely = -10;
					direction="up";
				}
			}else{
				velx = 0;
				vely = -10;
				direction="up";
			}
		}
		if(c == KeyEvent.VK_RIGHT){
			if(segexists){
				if(direction == "left"){
					direction="left";
				}else{
					velx = 10;
					vely = 0;
					direction="right";
				}
			}else{
				velx = 10;
				vely = 0;
				direction="right";
			}
		}
		if(c == KeyEvent.VK_DOWN){
			if(segexists){
				if(direction == "up"){
					direction="up";
				}else{
					velx = 0;
					vely = 10;
					direction="down";
				}
			}else{
				velx = 0;
				vely = 10;
				direction="down";
			}
		}
		if(c == KeyEvent.VK_Y){
			if(defeat){
				defeat=false;
				resetEverything();
			}
		}
		if(c == KeyEvent.VK_N){
			if(defeat=true){
				System.exit(0);
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_LEFT){
			if(segexists){
				if(direction == "right"){
					direction="right";
				}else{
					velx = -10;
					vely = 0;
					direction="left";
				}
			}else{
				velx = -10;
				vely = 0;
				direction="left";
			}
		}
		if(c == KeyEvent.VK_UP){
			if(segexists){
				if(direction == "down"){
					direction="down";
				}else{
					velx = 0;
					vely = -10;
					direction="up";
				}
			}else{
				velx = 0;
				vely = -10;
				direction="up";
			}
		}
		if(c == KeyEvent.VK_RIGHT){
			if(segexists){
				if(direction == "left"){
					direction="left";
				}else{
					velx = 10;
					vely = 0;
					direction="right";
				}
			}else{
				velx = 10;
				vely = 0;
				direction="right";
			}
		}
		if(c == KeyEvent.VK_DOWN){
			if(segexists){
				if(direction == "up"){
					direction="up";
				}else{
					velx = 0;
					vely = 10;
					direction="down";
				}
			}else{
				velx = 0;
				vely = 10;
				direction="down";
			}
		}
		if(c == KeyEvent.VK_Y){
			if(defeat){
				defeat=false;
				resetEverything();
			}
		}
		if(c == KeyEvent.VK_N){
			if(defeat=true){
				System.exit(0);
			}
		}
	}

	public void keyTyped(KeyEvent e) {}
	
	public void draw(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		Graphics2D g2d = (Graphics2D) g;
		for(int x=0;x<width;x+=10){
			g2d.drawLine(x, 0, x, 490);
		}
		for(int y=0;y<height;y+=10){
			g2d.drawLine(0, y, 490, y);
		}
	}
}