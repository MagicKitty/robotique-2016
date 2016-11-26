package Camera;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class MyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int width = 70;
	private int height = 40;
	public MyButton(String s, int r, int g, int b) {
		this.setText(s);
		this.setPreferredSize(new Dimension(this.width,this.height));
		this.setBackground(new Color(r, g, b));
		this.setFont(new Font("Tahoma", Font.BOLD, 10));
		this.setFocusPainted(false);
		this.setForeground(Color.WHITE);
	}
}
