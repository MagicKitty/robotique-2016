package tests;

public class Modele {

	public Modele() {
		ObservableValue ov = new ObservableValue(0);
		TextObserver to = new TextObserver(ov);
		ov.addObserver(to);
	}

	public static void main(String [] args)	{
//		Modele m = new Modele();
	}

}	
