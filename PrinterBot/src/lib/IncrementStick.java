package lib;

public class IncrementStick {
	public Joystick Stick;
	public int count;
	public IncrementStick(Joystick stick){
		Stick = stick;	
	}
	public int incVal(boolean inc){
		count++;
		return count;
	}
	public int decVal(boolean dec){
		count--;
		return count;
	}
		
}
