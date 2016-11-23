package Camera;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class MaJFrame extends JFrame {
	public MaJFrame(int a, int b) {
		setTitle("IHM - Robafis - 2016");
		Dimension dimensionWebcam = new Dimension(a,b);
		
		setResizable(true);
		setPreferredSize(new Dimension(dimensionWebcam));
		

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(10, 10, 50, 50);
	}
}