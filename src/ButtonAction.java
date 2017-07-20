import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonAction implements ActionListener{
	
	JButton rb;
	JButton bb;
	
	public ButtonAction(JButton button1, JButton button2) {
		
		rb = button1;
		bb = button2;
		
//		rb.addActionListener(this);
//		bb.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rb) {
			System.out.println("red car");
		} else if (e.getSource() == bb) {
			System.out.println("blue car");
		}		
	}
}
