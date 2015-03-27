package TileMap;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Grid {
	public int width=500;
	public int height=500;
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(int x=0;x<width;x+=10){
			g2d.drawLine(x, 0, x, 490);
		}
		for(int y=0;y<height;y+=10){
			g2d.drawLine(0, y, 490, y);
		}
	}

}
