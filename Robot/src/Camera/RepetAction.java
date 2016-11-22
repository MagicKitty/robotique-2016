package Camera;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RepetAction {
	private Timer t;
	private static int speed;
	private static int jetons;
	private Random r;

	public RepetAction() {
		t = new Timer();
		r = new Random();
		speed = 0;
		jetons = 0;
		t.schedule(new MonAction(), 0, 100);
	}

	class MonAction extends TimerTask {
		boolean repet = true;
		public void run() {
			if (repet) {
				speed = (r.nextInt(26));
				jetons = (r.nextInt(7));
				Cam.setSpeed(speed);
				Cam.setJetons(jetons);
				//calcul des valeurs pour chaque roue
				Cam.getXBoxCtrlListener().movement.calculateWheelsSpeed();
				//System.out.println("L : " + bbox.movement.leftWheel + " R: " + bbox.movement.rightWheel	);
				//envoi
				Cam.getBluetoothManager().sendMovement( Cam.getXBoxCtrlListener().movement );
			} else {
				System.out.println("Terminé!");
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
}