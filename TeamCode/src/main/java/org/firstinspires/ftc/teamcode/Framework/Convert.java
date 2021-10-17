package org.firstinspires.ftc.teamcode.Framework;

public class Convert {
	//Converts any number to boolean
	private static boolean toBoolean(int value) {
		return value != 0;
	}

	//Converts boolean to int
	private static int toInt(boolean input) {
		return input ? 1 : 0;
	}
}
