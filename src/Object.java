import java.awt.Color;
import java.awt.Graphics;

public class Object {
	
	int x;
	int y;
	int width;
	int height;
	
	int mouseX;
	int mouseY;
	
	Object() {
		
		width = 50;
		height = 50;
		
	}
	
	void update() {
		x = mouseX;
		y = mouseY;
	}
	
	void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
	
	void setMouseX(int mouseX){
		this.mouseX = mouseX;
	}
	
	void setMouseY(int mouseY){
		this.mouseY = mouseY;
	}
}
