package Tests;

public class MesTests {
	
static long chrono = 0;
	
	static void Go_Chrono() { 
		System.out.println("hello");
		chrono = java.lang.System.currentTimeMillis() ;
		System.out.println(chrono);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello1");
		Go_Chrono();
	}

}