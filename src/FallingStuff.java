import javax.swing.JFrame;

public class FallingStuff {

	JFrame frame;
	Panel panel;

	static final int WIDTH = 500;
	static final int HEIGHT = 800;

	FallingStuff() {

		frame = new JFrame();
		panel = new Panel();

		setup();

	}

	public static void main(String[] args) {
		FallingStuff FS = new FallingStuff();
	}

	void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(panel);

		panel.startGame();
	}
}
