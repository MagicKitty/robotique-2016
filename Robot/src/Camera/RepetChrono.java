package Camera;

import java.util.Timer;
import java.util.TimerTask;

public class RepetChrono {
	private Timer t;
	private static int seconds = 0;
	private static int minutes = 0;
	
	public RepetChrono() {
		t = new Timer();
		t.schedule(new MonAction2(), 0, 1000);
	}
	
	class MonAction2 extends TimerTask {
		boolean repet = true;
		public void run() {
			if (repet) {
				seconds++;
				if(seconds == 60) {
					seconds = 0;
					minutes++;
				}
				Cam.refreshChrono(seconds,minutes);
			} else {
				t.cancel();
				
			}
		}
	}
}
