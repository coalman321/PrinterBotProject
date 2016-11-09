package lib;

public class ServoLogic {
	private int target;
	private int current;
	private int tol;
	public ServoLogic(int tolerance){
		tol=tolerance;
	}
	public double toTarget(int target, int current){
		double speed = 0.5;
		if(target - tol < current){
			return -speed;
		}
		else if(target + tol > current){
			return speed;
		}
		else{
			return 0.0;
		}
	}
}
