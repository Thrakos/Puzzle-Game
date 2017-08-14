import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	Timer timer;

	GameObject O;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int PAUSE_STATE = 2;
	final int END_STATE = 3;

	int current_state;

	int grass1y;
	int grass2y;

	int number;

	int highscore;

	int character;

	int notUnlocked;
	
	static int speed;

	public static int gamesPlayed;

	boolean instructions;

	boolean character_select;

	boolean secret_unlocked;

	boolean countdown;
	boolean countdown2;

	Font titleFont;
	Font otherFont;
	Font smallerFont;

	Racecar car;

	ObjectManager manager;

	public static BufferedImage carImg;
	public static BufferedImage lockImg;
	public static BufferedImage grassImg;
	public static BufferedImage redCarImg;
	public static BufferedImage blueCarImg;
	public static BufferedImage secretCarImg;
	public static BufferedImage stripeCarImg;
	public static BufferedImage cookieCarImg;
	public static BufferedImage rocketshipImg;

	Panel() {

		timer = new Timer(1000 / 60, this);

		O = new GameObject();

		current_state = MENU_STATE;

		titleFont = new Font("Arial", Font.PLAIN, 48);
		otherFont = new Font("Arial", Font.PLAIN, 25);
		smallerFont = new Font("Arial", Font.PLAIN, 16);

		car = new Racecar(250, 700, 50, 50);

		manager = new ObjectManager();

		manager.addObject(car);

		instructions = false;

		character_select = false;

		secret_unlocked = false;

		countdown = false;
		countdown2 = false;

		grass1y = 0;
		grass2y = -FallingStuff.HEIGHT;

		number = 3;

		highscore = 0;

		character = 1;

		gamesPlayed = 0;

		notUnlocked = 0;
		
		speed = 10;

		try {
			carImg = ImageIO.read(this.getClass().getResourceAsStream("racecar.gif"));
			grassImg = ImageIO.read(this.getClass().getResourceAsStream("grass.png"));
			redCarImg = ImageIO.read(this.getClass().getResourceAsStream("racecar.gif"));
			blueCarImg = ImageIO.read(this.getClass().getResourceAsStream("bluecar.gif"));
			secretCarImg = ImageIO.read(this.getClass().getResourceAsStream("secretcar.gif"));
			stripeCarImg = ImageIO.read(this.getClass().getResourceAsStream("stripecar.gif"));
			cookieCarImg = ImageIO.read(this.getClass().getResourceAsStream("cookiecar.gif"));
			rocketshipImg = ImageIO.read(this.getClass().getResourceAsStream("rocketship.gif"));
			lockImg = ImageIO.read(this.getClass().getResourceAsStream("locked.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 16; i++) {
			Wall wall = new Wall(125, i * 50 - 10, 50, 50);
			Wall wall2 = new Wall(125 + 200, i * 50 - 10, 50, 50);
			manager.addObject(wall);
			manager.addObject(wall2);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (current_state == MENU_STATE) {
			updateMenuState();
		} else if (current_state == GAME_STATE) {
			updateGameState();
		} else if (current_state == PAUSE_STATE) {
			updatePauseState();
		} else if (current_state == END_STATE) {
			updateEndState();
		}
	}

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (current_state == MENU_STATE) {
			drawMenuState(g);
		} else if (current_state == GAME_STATE) {
			drawGameState(g);
		} else if (current_state == PAUSE_STATE) {
			drawPauseState(g);
		} else if (current_state == END_STATE) {
			drawEndState(g);
		}

	}

	void updateMenuState() {
		manager.score = 0;
		speed = 10;
		manager.enemySpawnTime = 80;
	}

	void updateGameState() {
		manager.update();
		manager.manageEnemies();
		manager.checkCollision();
		grass1y += speed;
		grass2y += speed;
		if (grass1y >= FallingStuff.HEIGHT) {
			grass1y = -FallingStuff.HEIGHT;
		}
		if (grass2y >= FallingStuff.HEIGHT) {
			grass2y = -FallingStuff.HEIGHT;
		}
		if (car.isAlive == false) {
			current_state = END_STATE;
			manager.reset();
			car = new Racecar(250, 700, 50, 50);
			manager.addObject(car);
			manager.ran = 125;
			for (int i = 0; i < 16; i++) {
				Wall wall = new Wall(125, i * 50 - 10, 50, 50);
				Wall wall2 = new Wall(125 + 200, i * 50 - 10, 50, 50);
				manager.addObject(wall);
				manager.addObject(wall2);
			}
		}

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, FallingStuff.WIDTH, FallingStuff.HEIGHT);
		g.setColor(Color.YELLOW);
		if (instructions == false && character_select == false) {
			g.setFont(titleFont);
			g.drawString("Don't Crash", 120, 200);
			g.setFont(otherFont);
			g.drawString("Press ENTER to start", 125, 300);
			g.drawString("Press SPACE for instructions", 80, 400);
			g.drawString("Press 1 for character selection", 70, 500);
		}
		if (instructions == true && character_select == false) {
			g.setFont(otherFont);
			g.drawString("Move the car with the mouse and", 80, 350);
			g.setFont(titleFont);
			g.drawString("Don't Crash", 120, 410);
		}
		if (character_select == true && instructions == false) {
			g.drawImage(Panel.redCarImg, 0, 0, 50, 50, null);
			g.drawImage(Panel.blueCarImg, 55, 0, 50, 50, null);
			if (highscore >= 100) {
				g.drawImage(stripeCarImg, 110, 0, 50, 50, null);
			} else {
				g.drawImage(lockImg, 110, 10, 40, 40, null);
			}
			if (gamesPlayed >= 10) {
				g.drawImage(cookieCarImg, 165, 0, 50, 50, null);
			} else {
				g.drawImage(lockImg, 165, 10, 40, 40, null);
			}
			if (highscore >= 1000) {
				g.drawImage(rocketshipImg, 220, 0, 50, 50, null);
			} else {
				g.drawImage(lockImg, 220, 10, 40, 40, null);
			}
			if (secret_unlocked == true) {
				g.drawImage(Panel.secretCarImg, 450, 720, 50, 50, null);
			}
			g.setColor(Color.yellow);
			g.setFont(otherFont);
			g.drawString("Click a car to select it", 125, 700);
			g.setFont(smallerFont);
			if (notUnlocked == 1) {
				g.drawString("Get a score of 100 or more to unlock this car!", 80, 650);
			} else if (notUnlocked == 2) {
				g.drawString("Play " + (10 - gamesPlayed) + " more games with a score of 100 or more to unlock this car!", 13, 650);
			} else if (notUnlocked == 3) {
				g.drawString("Get a score of 1000 or more to unlock this car!", 75, 650);
			}
			if (carImg == redCarImg) {
				g.drawRect(0, 0, 50, 50);
			}
			if (carImg == blueCarImg) {
				g.drawRect(55, 0, 50, 50);
			}
			if (carImg == stripeCarImg) {
				g.drawRect(110, 0, 50, 50);
			}
			if (carImg == cookieCarImg) {
				g.drawRect(165, 0, 50, 50);
			}
			if (carImg == rocketshipImg) {
				g.drawRect(220, 0, 50, 50);
			}
			if (carImg == secretCarImg) {
				g.drawRect(449, 720, 50, 50);
			}
		}
	}

	void drawGameState(Graphics g) {
		g.drawImage(grassImg, 0, grass1y, FallingStuff.WIDTH, FallingStuff.HEIGHT, null);
		g.drawImage(grassImg, 0, grass2y, FallingStuff.WIDTH, FallingStuff.HEIGHT, null);
		manager.draw(g);
		car.draw(g);
		g.setColor(Color.yellow);
		g.setFont(otherFont);
		g.drawString("score: " + manager.score, 10, 30);
	}

	void updatePauseState() {
		if (countdown2 == true) {
			number -= 1;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (number == 0) {
			countdown = false;
			countdown2 = false;
			number = 3;
			try {
				int nx = getLocationOnScreen().x;
				int ny = getLocationOnScreen().y;
				new Robot().mouseMove(nx + car.x + 25, ny + car.y);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			current_state = GAME_STATE;
		}
	}

	void drawPauseState(Graphics g) {
		drawGameState(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new Color(255, 255, 255, 200));
		g2.fillRect(0, 0, FallingStuff.WIDTH, FallingStuff.HEIGHT);
		if (countdown == true) {
			g.setFont(titleFont);
			g.setColor(Color.yellow);
			g.drawString("" + number, 240, 300);
			countdown2 = true;
		} else {
			g.setFont(titleFont);
			g.setColor(Color.yellow);
			g.drawString("Game Paused", 100, 300);
			g.setFont(otherFont);
			g.drawString("Click to continue", 170, 350);
		}

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, FallingStuff.WIDTH, FallingStuff.HEIGHT);
		g.setColor(Color.blue);
		g.setFont(titleFont);
		g.drawString("Your Score: " + manager.score, 100, 400);
		getInsets();
		g.setFont(otherFont);
		if (manager.score > highscore) {
			highscore = manager.score;
		}
		g.drawString("High Score: " + highscore, 175, 450);
		g.drawString("Press ENTER to continue", 100, 500);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && current_state != GAME_STATE && current_state != PAUSE_STATE) {
			current_state += 1;
		}
		if (current_state > END_STATE) {
			current_state = MENU_STATE;
		}
		if (current_state == GAME_STATE) {
			try {
				int nx = getLocationOnScreen().x;
				int ny = getLocationOnScreen().y;
				new Robot().mouseMove(nx + 250, ny + 725);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && current_state == MENU_STATE && instructions == false) {
			instructions = true;
		} else if (instructions == true) {
			instructions = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_1 && current_state == MENU_STATE && character_select == false) {
			character_select = true;
		} else if (character_select == true) {
			character_select = false;
			notUnlocked = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_G && current_state == MENU_STATE) {
			gamesPlayed += 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && current_state == GAME_STATE) {
			manager.score += 100;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (current_state == PAUSE_STATE) {
			countdown = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (current_state == MENU_STATE && character_select == true) {
			if (e.getX() >= 0 && e.getX() <= 50 && e.getY() >= 0 && e.getY() <= 70) {
				carImg = redCarImg;
				notUnlocked = 0;
			}
			if (e.getX() >= 55 && e.getX() <= 105 && e.getY() >= 0 && e.getY() <= 70) {
				carImg = blueCarImg;
				notUnlocked = 0;
			}
			if (e.getX() >= 110 && e.getX() <= 160 && e.getY() >= 0 && e.getY() <= 700) {
				if (highscore >= 100) {
					carImg = stripeCarImg;
					notUnlocked = 0;
				} else {
					notUnlocked = 1;
				}
			}
			if (e.getX() >= 165 && e.getX() <= 220 && e.getY() >= 0 && e.getY() <= 70) {
				if (gamesPlayed >= 10) {
					carImg = cookieCarImg;
					notUnlocked = 0;
				} else {
					notUnlocked = 2;
				}
			}
			if (e.getX() >= 210 && e.getX() <= 270 && e.getY() >= 0 && e.getY() <= 70) {
				if (highscore >= 1000) {
					carImg = rocketshipImg;
					notUnlocked = 0;
				} else {
					notUnlocked = 3;
				}
			}
			if (e.getX() >= 440 && e.getX() <= 500 && e.getY() >= 730 && e.getY() <= 800) {
				secret_unlocked = true;
				carImg = secretCarImg;
			}
		}

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
		if (current_state == GAME_STATE) {
			current_state = PAUSE_STATE;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		car.setMouseX(e.getX());

	}

}
