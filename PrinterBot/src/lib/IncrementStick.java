package lib;

public class IncrementStick {
	private int count;
	private int val;
	public IncrementStick(int countBy, int begin){
		count = begin;
		val=countBy;
	}
	public int incVal(boolean inc){
		count+=val;
		return count;
	}
	public int decVal(boolean dec){
		count-=val;
		return count;
	}
		
}
