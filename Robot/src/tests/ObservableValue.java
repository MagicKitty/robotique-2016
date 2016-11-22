package tests;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ObservableValue extends Observable {

	private int n = 0;
	private Timer t;
	private int speed;
	private Random r;

	public ObservableValue(int n) {
		this.n = n;
		this.t = new Timer();
		this.r = new Random();
		this.speed = 0;
		this.t.schedule(new MonAction(), 0, 1*1000);
	}

	private class MonAction extends TimerTask {
		public void run() {
			System.out.println("Ca bosse dur!");
			setSpeed(r.nextInt(26));
		}
	}

	public void setSpeed(int n) {
		this.speed = n;
		setChanged();
		notifyObservers();
	}

	public void setValue(int n) {
		this.n = n;
		setChanged();
		notifyObservers();
	}

	public int getValue() {
		return this.n;
	}
	
	public int getSpeed() {
		return this.speed;
	}
}
