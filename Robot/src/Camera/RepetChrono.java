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
		Cam.refreshChrono(seconds,minutes);
	}
	
	class MonAction2 extends TimerTask {
		public void run() {
			if(Cam.getStartChrono()) {
				if(Cam.getButtonStartChrono() != null) {
					Cam.setTextStart("Pause");
				}
				seconds++;
				if(seconds == 60) {
					seconds = 0;
					minutes++;
				}
				Cam.refreshChrono(seconds,minutes);
			} else {
				if(Cam.getButtonStartChrono() != null) {
					Cam.setTextStart("Start");
				}
			}
//			if(Cam.getPauseChrono()&&Cam.getStartChrono()) {
//				Cam.setStartChrono(false);
//				Cam.setResumeChrono(true);
//				Cam.setStopChrono(true);
//				Cam.refreshChrono(seconds,minutes);
//			}
//			if(Cam.getResumeChrono()) {
//				Cam.setPauseChrono(true);
//				Cam.setStartChrono(false);
//				Cam.setStopChrono(true);
//				seconds++;
//				if(seconds == 60) {
//					seconds = 0;
//					minutes++;
//				}
//				Cam.refreshChrono(seconds,minutes);
//			}
//			if(Cam.getStopChrono()) {
//				Cam.setPauseChrono(false);
//				Cam.setStartChrono(true);
//				Cam.setResumeChrono(false);
//				seconds = 0;
//				minutes = 0;
//				Cam.refreshChrono(seconds,minutes);
//			}
		}
	}
}
