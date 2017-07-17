import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {

	int x;
	int y;
	int width;
	int height;

	Rectangle collisionBox;

	boolean isAlive;

	GameObject() {

		width = 50;
		height = 50;

		collisionBox = new Rectangle(x, y, width, height);

		isAlive = true;

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {

	}

}
