package org.firstinspires.ftc.teamcode.Framework;

public class Convert {
	//Converts any number to boolean
	public static boolean toBoolean(int value) {
		return value != 0;
	}

	//Converts boolean to int
	public static int toInt(boolean input) {
		return input ? 1 : 0;
	}
	
	//Used inside the toEvent and checks if the inpus has occured. Acts as a Event.
	private boolean inputEvent;
	//Converts the value to an Event allowing for the boolean to occur true only once when it is set to true.
	public boolean toEvent(boolean input) {
		if(input && inputEvent){
			inputEvent = false;
			return true;
		} else {
			inputEvent = true;
		}
		return false;
	}
	
	//Converts the value to the normalized value
	public double normalize(double value, double min, double max) {
    		return (value - min) / (max - min);
	}
	
	//Converts the value to the normalized value between a custom range
	public double normalize(double value, double min, double max, double minRange, double maxRange) {
    		return (((value - min) / (max - min)) * (maxRange + Math.abs(minRange))) - minRange;
	}
}
