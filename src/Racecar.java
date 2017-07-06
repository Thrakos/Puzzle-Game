import java.awt.Color;
import java.awt.Graphics;

public class Racecar extends GameObject {

	int speed;
	
	int mouseX;
	
	Racecar(int x, int y, int width, int height){
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
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}
	
	void setMouseX(int mouseX){
		this.mouseX = mouseX;
	}
	
}

