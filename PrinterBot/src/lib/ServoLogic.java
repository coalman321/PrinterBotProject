package lib;

public class ServoLogic {
	private int target;
	private int current;
	private int tol; 
        Private int spd;
	public ServoLogic(int tolerance,double speed){
		tol=tolerance;
                spd=speed;
	}
	public double toTarget(int target, int current){
		if(target - tol < current){
			return (spd*-1);
		}
		else if(target + tol > current){
			return spd;
		}
		else{
			return 0.0;
		}
	}
}
