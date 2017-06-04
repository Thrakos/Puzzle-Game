import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	Timer timer;
	
	Object O;
	
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	
	int current_state;

	Panel() {

		timer = new Timer(1000 / 60, this);
		
		O = new Object();
		
		current_state = MENU_STATE;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if(current_state == MENU_STATE){
			updateMenuState();
		}else if(current_state == GAME_STATE){
			updateGameState();
		}else if(current_state == END_STATE){
			updateEndState();
		}


	}

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if(current_state == MENU_STATE){
			drawMenuState(g);
		}else if(current_state == GAME_STATE){
			drawGameState(g);
		}else if(current_state == END_STATE){
			drawEndState(g);
		}


	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 0, FallingStuff.WIDTH, FallingStuff.HEIGHT);
	}
	
	void drawGameState(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, FallingStuff.WIDTH, FallingStuff.HEIGHT);
	}
	
	void drawEndState(Graphics g){
		g.setColor(Color.red);
		g.fillRect(0, 0, FallingStuff.WIDTH, FallingStuff.HEIGHT);
	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			current_state += 1;
		}
		if(current_state > END_STATE){
			current_state = MENU_STATE;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// O.setMouseX(e.getX());
		// O.setMouseY(e.getY());
		
	}

}
