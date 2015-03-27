package Main;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Game extends JFrame {
	 public Game() {
		 	Board board = new Board();
		 	JFrame jf = new JFrame();
			jf.setContentPane(board);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(499, 520);
			jf.setLocationRelativeTo(null);
			jf.setTitle("Snake Game");
			jf.setVisible(true);
			jf.setResizable(false);
	    }

	    public static void main(String[] args) {
	        new Game();
	    }
}