package lib;

public class ServoLogic {
	private int target;
	private int current;
	private int tol; 
        private double spd;
	public ServoLogic(int tolerance,double speed){
		tol=tolerance;
                spd=speed;
	}
	public double toTarget(int target, int current){
		if(target < current - tol){
			return (spd*-1);
		}
		else if(target > current + tol){
			return spd;
		}
		else{
			return 0.0;
		}
	}
}
