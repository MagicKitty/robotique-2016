package Camera;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.Toolkit;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//import java.awt.image.BufferedImage;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
//	private static Dimension die ,die2;
//	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

//	private static int width,height;
	private static int speed;
	private static int jetons;
	private static int widthFrame, heightFrame;
	
//	private static int widthScreen = (int)screenSize.getWidth();
//	private static int heightScreen = (int)screenSize.getHeight();
	
	private static JLayeredPane layeredPane = new JLayeredPane();
	
	private static BorderLayout bl = new BorderLayout();
	private static BorderLayout bl2 = new BorderLayout();
	
	private static JLabel jlSpeed = new JLabel();
	private static JLabel jlJetons = new JLabel();
	private static JLabel jlChrono = new JLabel();
	private static JLabel jlWebcamSize = new JLabel();
	private static JPanel jpInformations = new JPanel();
	private static JPanel jpInformations2 = new JPanel();
	
	private static Font font = new Font("Arial",Font.PLAIN,20);
	private static Font font2 = new Font("Arial",Font.BOLD,15);
	
	private static JLabel image;

	static {
		Webcam.setDriver(new IpCamDriver());
	}

	public static void main(String[] args) {
		System.out.println("Started IHM");
		startIHM();
	}

	public static void startIHM() {
		image = new JLabel(new ImageIcon("src/images/rouge.png"));
		image.setHorizontalAlignment(JLabel.LEFT);
		
//		setXBoxController();
		
		setWebcamIPAsVideo();
		
		f = new MaJFrame(widthFrame,heightFrame);
		
		f.add(layeredPane, BorderLayout.CENTER);

		layeredPane.add(video, new Integer(1));

//		windowResize();

		setJPanelInformations();
		
		layeredPane.add(jpInformations, new Integer(2));
		
		layeredPane.add(jpInformations2, new Integer(3));
	}
	
	private static void setWebcamIPAsVideo() {
		try {
			IpCamDeviceRegistry.register("Robot!", "http://192.168.43.1:8080/video", IpCamMode.PUSH);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		new CameraDetection();
		
		new RepetAction();
		
		new RepetChrono();

		Webcam webcam = (Webcam) Webcam.getWebcams().get(0);
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		video = new WebcamPanel(webcam);

		widthFrame = (int)webcam.getViewSize().getWidth();
		heightFrame = (int)webcam.getViewSize().getHeight();
		video.setBounds(0, 0, widthFrame, heightFrame);
		jpInformations.setBounds(0, 0, widthFrame, heightFrame);
		jpInformations2.setBounds(0, 0, widthFrame, heightFrame);
	}


	
	private static void setJPanelInformations() {
		jpInformations.setLayout(bl);
		jpInformations2.setLayout(bl2);
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
		
		
		jpInformations2.add(image, BorderLayout.NORTH);
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
	
//	private static void windowResize() {
//	die = f.getSize();
//	f.addComponentListener(new ComponentAdapter(){
//		public void componentResized(ComponentEvent e) {
//			width = f.getWidth();
//			height = f.getHeight();
//			double proportion = (double)width/(double)height;
//			if(e.getSource() instanceof JFrame)	{
//				die2 = f.getSize();
//				if(die2.equals(die)) {
//					die = die2;
//				} else {
//					while(proportion < (1.333) && (width <= widthScreen-100)) {
//						width++;
//						proportion = (double)width/(double)height;
//					}
//					while(proportion > (1.34) && (height <= heightScreen-100)) {
//						height++;
//						proportion = (double)width/(double)height;
//					}
//					f.setSize(width,height);
//					video.setBounds(0, 0, width, height); // augmenter la taille de la video
//					jpInformations.setBounds(0, 0, width, height);
//					jpInformations2.setBounds(0, 0, width, height);
//				}
//			}
//		}
//	});
//}
	
}