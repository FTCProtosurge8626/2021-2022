package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.IMU_Hardware.*;
import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.Motor_Hardware.*;
import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.DriveMode.*;
import static org.firstinspires.ftc.teamcode.Framework.Convert.*;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

public class TeleOp_Methods extends Robot_Hardware {

	private static double[] powers = new double[4];

	public static void move(double forward, double horizontal, double rotational) {
		double totalPower = Math.abs(forward) + Math.abs(horizontal) + Math.abs(rotational)
			
		if(totalPower > 1){
			forward =/ totalPower;
			horizontal =/ totalPower;
			rotational =/ totalPower;
		}
		
		switch(driveMode) {
			case Tank:
				powers[0] = forward + rotational;
				powers[1] = forward - rotational;
				powers[2] = forward + rotational;
				powers[3] = forward - rotational;
				break;
			case Arcade:
				powers[0] = forward - horizontal + rotational;
				powers[1] = forward + horizontal - rotational;
				powers[2] = forward + horizontal + rotational;
				powers[3] = forward - horizontal - rotational;
				break;
			case Custom:
				break;
			default:
				break;
		}
		SetPowers(Motors, powers);
	}

	public static void moveHeading(double forward, double horizontal, double rotational, boolean haultHeading,  boolean lockedHaultHeading) {
		if(rotational != 0) {
			SetTargetHeading(getCurrentHeading());
		}
		
		double error = normalize(getHeadingError(getTargetHeading()), getheadingMin(), getheadingMax());

		move(forward, horizontal, error + rotational);
		
		if(haultHeading || lockedHaultHeading){
			move(forward, horizontal, rotational);
		}
	}
	
	private static void SetDirection(double dir) {
		SetTargetHeading(dir);
	}
	
	public static OrientCompass() {
		switch(compass) {
			case Compass.NORTH:
				SetDirection(0);
				break;
			case Compass.SOUTH:
				SetDirection(90);
				break;
			case Compass.EAST:
				SetDirection(180);
				break;
			case Compass.WEST:
				SetDirection(270);
				break;
			default:
				compass = NONE;
				break;
		}
	}
	
	public static void PointCompass(boolean[] direction){
		switch(true) {
			case direction[0]:
				compass = Compass.NORTH;
				break;
			case direction[1]:
				compass = Compass.SOUTH;
				break;
			case direction[2]:
				compass = Compass.EAST;
				break;
			case direction[3]:
				compass = Compass.WEST;
				break;
			default:
				compass = NONE;
				break;
		}
		OrientCompass();
	}
}
