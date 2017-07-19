import java.awt.Color;
import java.awt.Graphics;

public class Racecar extends GameObject {

	int speed;

	int mouseX;

	Racecar(int x, int y, int width, int height) {
		super();

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		speed = 5;

	}

	void update() {
		super.update();

		x = mouseX - 25;

		if (x < 70) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.drawImage(Panel.carImg, x, y, width, height, null);

	}

	void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

}
