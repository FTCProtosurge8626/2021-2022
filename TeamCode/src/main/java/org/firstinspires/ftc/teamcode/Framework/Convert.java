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
	
	public double normalize(double value, double min, double max) {
    		return 1 - ((value - min) / (max - min));
	}
}
