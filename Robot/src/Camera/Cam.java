package Camera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

//import Manette.BluetoothManager;
//import Manette.XBoxCtrlListener;
//import ch.aplu.xboxcontroller.XboxController;

public class Cam {

	public static MaJFrame f;
	
	private static WebcamPanel video;
	private static JPanel testPanel;

	private static int speed;
	private static int jetons;
	private static int batteryLevel;
	private static int widthFrame, heightFrame;
	
	private static JLayeredPane layeredPane = new JLayeredPane();
	
	private static BorderLayout bl = new BorderLayout();
	private static BorderLayout bl2 = new BorderLayout();
	private static BorderLayout bl3 = new BorderLayout();
	private static BorderLayout bl4 = new BorderLayout();
	
	private static ImageIcon imgRed = new ImageIcon("src/images/rouge.png");
	private static ImageIcon imgGreen = new ImageIcon("src/images/vert.png");
	
	private static JLabel jlSpeed = new JLabel();
	private static JLabel jlJetons = new JLabel();
	private static JLabel jlChrono = new JLabel();
	private static JLabel jlWebcamSize = new JLabel();
	private static JLabel imageRed = new JLabel(imgRed);
	private static JLabel imageGreen = new JLabel(imgGreen);
	private static JLabel jlBatteryLevel = new JLabel();

	private static JTextField jtfChat = new JTextField();
	
	private static JPanel jpInformations = new JPanel();
	private static JPanel jpInformations2 = new JPanel();
	private static JPanel jpForChat = new JPanel();
	private static JPanel jpForDoubleBipAndStartChrono = new JPanel();
	private static JPanel jpInformationsRight = new JPanel();
	
	private static Font font = new Font("Arial",Font.PLAIN,20);
	private static Font font2 = new Font("Arial",Font.BOLD,15);
	
	private static JButton jbSend;
	private static JButton jbDoubleBip;
	private static JButton jbStartChrono;
	private static JButton jbPauseChrono;
	private static JButton jbResumeChrono;
	private static JButton jbStopChrono;
	
	private static String message = "";
	private static String bip = ":BB:";
	
	private static Boolean color = false;
	private static Boolean startChrono = false;
	private static Boolean pauseChrono = false;
	private static Boolean resumeChrono = false;
	private static Boolean stopChrono = false;

	static {
		Webcam.setDriver(new IpCamDriver());
	}
	public static void main(String[] args) {
		System.out.println("Started IHM");
		startIHM();
	}
	public static void startIHM() {
		setWebcamIPAsVideo();
		
//		setXBoxController();
		
		imageRed.setHorizontalAlignment(JLabel.LEFT);
		imageGreen.setHorizontalAlignment(JLabel.LEFT);
		
		jtfChat.setPreferredSize(new Dimension(widthFrame - 210,30));
		jtfChat.setHorizontalAlignment(JTextField.LEFT);
		jbSend = new MyButton("Envoi", 59, 89, 182);
		jbSend.addActionListener(new ActionSend());
		jbDoubleBip = new MyButton("Bip!", 255, 102, 102);
		jbDoubleBip.addActionListener(new ActionBip());
		jbStartChrono = new MyButton("Start",178,255,102);
//		jbStartChrono.setPreferredSize(new Dimension(100,40));
		jbStartChrono.addActionListener(new ActionStartChrono());
		jbPauseChrono = new MyButton("Pause",178,255,102);
		jbPauseChrono.setPreferredSize(new Dimension(100,40));
		jbPauseChrono.addActionListener(new ActionPauseChrono());
		jbResumeChrono = new MyButton("Resume",178,255,102);
		jbResumeChrono.setPreferredSize(new Dimension(100,40));
		jbResumeChrono.addActionListener(new ActionResumeChrono());
		jbStopChrono = new MyButton("Stop",178,255,102);
		jbStopChrono.setPreferredSize(new Dimension(100,40));
		jbStopChrono.addActionListener(new ActionStopChrono());
		
		f = new MaJFrame(widthFrame,heightFrame);
		
		f.add(layeredPane, BorderLayout.CENTER);

//		layeredPane.add(video, new Integer(1));
		layeredPane.add(testPanel, new Integer(1));

		setJPanelInformations();
		
		layeredPane.add(jpInformations, new Integer(2));
		
		layeredPane.add(jpInformations2, new Integer(3));
		
		layeredPane.add(jpForChat, new Integer(4));
		
		layeredPane.add(jpForDoubleBipAndStartChrono, new Integer(5));
		
		layeredPane.add(jpInformationsRight, new Integer(6));
	}
	private static void setWebcamIPAsVideo() {
//		try {
//			IpCamDeviceRegistry.register("Robot!", "http://192.168.43.1:8080/video", IpCamMode.PUSH);
//		} catch (MalformedURLException e1) {
//			e1.printStackTrace();
//		}

//		new CameraDetection();
		
		new RepetAction();
		
		new RepetChrono();

//		Webcam webcam = (Webcam) Webcam.getWebcams().get(0);
//		webcam.setViewSize(WebcamResolution.VGA.getSize());
//		video = new WebcamPanel(webcam);
		testPanel = new JPanel();
		testPanel.setSize(640, 480);
		widthFrame = testPanel.getWidth();
		heightFrame = testPanel.getHeight();
		testPanel.setBounds(0, 0, widthFrame, heightFrame);
//		widthFrame = (int)webcam.getViewSize().getWidth();
//		heightFrame = (int)webcam.getViewSize().getHeight();
//		video.setBounds(0, 0, widthFrame, heightFrame);
		jpInformations.setBounds(0, 0, widthFrame, heightFrame);
		jpInformations2.setBounds(0, 0, widthFrame, heightFrame);
		jpForChat.setBounds(0, heightFrame, widthFrame - 140, 40);
		jpForDoubleBipAndStartChrono.setBounds(widthFrame - 140, heightFrame, 140, 40);
//		jpInformationsRight.setBounds(widthFrame,0,100,heightFrame+40);
	}
	private static void setJPanelInformations() {
		jpInformations.setLayout(bl);
		jpInformations2.setLayout(bl2);
		jpForChat.setLayout(bl3);
		jpForDoubleBipAndStartChrono.setLayout(bl4);
		jpInformations.setOpaque(false);
		jpInformations2.setOpaque(false);
		
		/* element 1 --------------------------------- */
		jpInformations.add(jlSpeed, BorderLayout.EAST);
		/* ------------------------------------------- */
		
		/* element 2 --------------------------------- */
		jpInformations.add(jlJetons, BorderLayout.WEST);
		/* ------------------------------------------- */
		
		/* element 3 --------------------------------- */
		setWebcamSize();
		jpInformations.add(jlWebcamSize, BorderLayout.NORTH);
		/* ------------------------------------------- */
		
		/* element 4 --------------------------------- */
		jpInformations2.add(jlChrono, BorderLayout.SOUTH);
		/* ------------------------------------------- */
		
		/* element 5 --------------------------------- */
		jpInformations2.add(imageRed, BorderLayout.NORTH);
		/* ------------------------------------------- */
		
		/* element 6 --------------------------------- */
		jpInformations2.add(jlBatteryLevel, BorderLayout.WEST);
		/* ------------------------------------------- */
		
		/* element 7 --------------------------------- */
		jpForDoubleBipAndStartChrono.add(jbDoubleBip, BorderLayout.WEST);
		jpForDoubleBipAndStartChrono.add(jbStartChrono, BorderLayout.EAST);
		/* ------------------------------------------- */
		
		/* element 8 --------------------------------- */
		jpForChat.add(jtfChat, BorderLayout.WEST);
		jpForChat.add(jbSend, BorderLayout.EAST);
		/* ------------------------------------------- */
		
		/* element 9 --------------------------------- */
//		jpInformationsRight.add(jbStartChrono,null);
//		jpInformationsRight.add(jbPauseChrono,null);
//		jpInformationsRight.add(jbResumeChrono,null);
//		jpInformationsRight.add(jbStopChrono,null);
		/* ------------------------------------------- */
	}
	private static void setWebcamSize() {
		jlWebcamSize.setText((int)WebcamResolution.VGA.getSize().getWidth() + "x" + (int)WebcamResolution.VGA.getSize().getHeight());
		jlWebcamSize.setHorizontalAlignment(JLabel.RIGHT);
		jlWebcamSize.setFont(font2);
		jlWebcamSize.setForeground(Color.red);
	}
	public static void setSpeed(int i) {
		speed = i;
		showSpeed();
	}
	private static void showSpeed() {
		jlSpeed.setText(speed + " : tr/min");
		jlSpeed.setFont(font);
		jlSpeed.setForeground(Color.red);
		jlSpeed.setVerticalAlignment(JLabel.BOTTOM);
	}
	public static void setJetons(int i) {
		jetons = i;
		showJetons();
	}
	public static void refreshChrono(int s,int m) {
		jlChrono.setText(m+" m "+s+" s");
		jlChrono.setFont(font);
		jlChrono.setForeground(Color.red);
		jlChrono.setHorizontalAlignment(JLabel.CENTER);
	}
	private static void showJetons() {
		jlJetons.setText(jetons + "/6 jetons   ");
		jlJetons.setFont(font);
		jlJetons.setForeground(Color.red);
		jlJetons.setVerticalAlignment(JLabel.BOTTOM);
	}
	public static void setBatteryLevel(int i) {
		batteryLevel = i;
		showBatteryLevel();
	}
	private static void showBatteryLevel() {
		jlBatteryLevel.setText(batteryLevel + "%");
		jlBatteryLevel.setFont(font);
		jlBatteryLevel.setForeground(Color.red);
//		jlBatteryLevel.setVerticalAlignment(JLabel.BOTTOM);
	}
	//	private static void setXBoxController() {
//		b = new BluetoothManager();
//		xc = new XboxController();
//		xc.setLeftThumbDeadZone(0.2);
//		if (!xc.isConnected()) {
//	      JOptionPane.showMessageDialog(null, 
//	        "Xbox controller not connected.",
//	        "Error",
//	        JOptionPane.ERROR_MESSAGE);
//	      xc.release();
//	      return;
//	    }
//		bbox = new XBoxCtrlListener();
//		xc.addXboxControllerListener(bbox);
//	}
//	
//	private static XBoxCtrlListener bbox;
//	private static BluetoothManager b;
//	private static XboxController xc;
//	
//	public static XBoxCtrlListener getXBoxCtrlListener() {
//		return bbox;
//	}
//	
//	public static BluetoothManager getBluetoothManager() {
//		return b;
//	}
//	
//	public static XboxController getXboxController() {
//		return xc;
//	}

	static class ActionSend implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			message = jtfChat.getText();
			jtfChat.setForeground(Color.gray);
			System.out.println(message);
			layeredPane.remove(jpInformations2);
			if(color == false) {
				color = true;
				jpInformations2.remove(imageRed);
				jpInformations2.add(imageGreen, BorderLayout.NORTH);
			} else {
				color = false;
				jpInformations2.remove(imageGreen);
				jpInformations2.add(imageRed, BorderLayout.NORTH);
			}
			layeredPane.add(jpInformations2, new Integer(3));
//			send(message);
		}
	}
	static class ActionBip implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(bip);
//			send(bip);
		}
	}
	static class ActionStartChrono implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(startChrono) {
				startChrono = false;
			} else {
				startChrono = true;
			}
		}
	}
	public static boolean getStartChrono() {
		return startChrono;
	}
	public static boolean setStartChrono(boolean b) {
		return startChrono = b;
	}
	static class ActionPauseChrono implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(pauseChrono) {
				pauseChrono = false;
			} else {
				pauseChrono = true;
			}
		}
	}
	public static boolean getPauseChrono() {
		return pauseChrono;
	}
	public static boolean setPauseChrono(boolean b) {
		return pauseChrono = b;
	}
	static class ActionResumeChrono implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(resumeChrono) {
				resumeChrono = false;
			} else {
				resumeChrono = true;
			}
		}
	}
	public static boolean getResumeChrono() {
		return resumeChrono;
	}
	public static boolean setResumeChrono(boolean b) {
		return resumeChrono = b;
	}
	static class ActionStopChrono implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(stopChrono) {
				stopChrono = false;
			} else {
				stopChrono = true;
			}
		}
	}
	public static boolean getStopChrono() {
		return stopChrono;
	}
	public static boolean setStopChrono(boolean b) {
		return stopChrono = b;
	}
	public static void setTextStart(String s) {
		jbStartChrono.setText(s);
	}
	public static JButton getButtonStartChrono() {
		return jbStartChrono;
	}
}