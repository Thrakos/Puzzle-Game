import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {
	
	Color brick;

	Wall(int x, int y, int width, int height){
		super();

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		brick = new Color(200, 0, 0);
	}

	void update() {
		super.update();

		y += 10;
		
	}

	void draw(Graphics g) {
		g.setColor(brick);
		g.fillRect(x, y, width, height);
		if(x <= 250){
			g.setColor(Color.gray);
			g.fillRect(x + 50, y, 150, height);
		}
	}

}
