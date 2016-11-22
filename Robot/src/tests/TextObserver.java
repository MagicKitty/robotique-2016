package tests;

import java.util.Observer;
import java.util.Observable;

public class TextObserver implements Observer {

	private ObservableValue ov = null;

	public TextObserver(ObservableValue ov) {
		this.ov = ov;
	}

	public void update(Observable obs, Object obj) {
		if (obs == ov) {
			System.out.println(ov.getValue());
			System.out.println(ov.getSpeed());
		}
	}
}
