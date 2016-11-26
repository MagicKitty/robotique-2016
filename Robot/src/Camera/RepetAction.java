package Camera;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RepetAction {
	private Timer t;
	private static int speed;
	private static int jetons;
	private static int batteryLevel;
	private Random r;

	public RepetAction() {
		t = new Timer();
		r = new Random();
		speed = 0;
		jetons = 0;
		batteryLevel = 0;
		t.schedule(new MonAction(), 0, 2000);
	}

	class MonAction extends TimerTask {
		boolean repet = true;
		public void run() {
			if (repet) {
				speed = (r.nextInt(26));
				jetons = (r.nextInt(7));
				batteryLevel = (r.nextInt(101));
				Cam.setSpeed(speed);
				Cam.setJetons(jetons);
				Cam.setBatteryLevel(batteryLevel);
				//calcul des valeurs pour chaque roue
//				Cam.getXBoxCtrlListener().movement.calculateWheelsSpeed();
//				speed = (int)Cam.getXBoxCtrlListener().movement.speed;
				//System.out.println("L : " + bbox.movement.leftWheel + " R: " + bbox.movement.rightWheel	);
				//envoi
//				Cam.getBluetoothManager().sendMovement( Cam.getXBoxCtrlListener().movement );
			} else {
				t.cancel();
			}
		}
	}

	public int getSpeedValue() {
		return speed;
	}
	
	public int getJetonsValue() {
		return jetons;
	}
	
	public int getBatteryLevelValue() {
		return batteryLevel;
	}
}