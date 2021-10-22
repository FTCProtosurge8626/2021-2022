package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import static org.firstinspires.ftc.teamcode.Framework.Controller.press;
import static org.firstinspires.ftc.teamcode.Framework.Convert.normalize;
import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.IMU_Hardware.*;
import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.IMU_Hardware.Compass.*;
import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.Motor_Hardware.*;

import org.firstinspires.ftc.teamcode.Framework.Convert;
import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

public class TeleOp_Methods extends Robot_Hardware {

	private static double[] powers = new double[4];

	public static void move(double forward, double horizontal, double rotational) {
		double totalPower = Math.abs(forward) + Math.abs(horizontal) + Math.abs(rotational);

		if(totalPower > 1){
			forward /= totalPower;
			horizontal /= totalPower;
			rotational /= totalPower;
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

	public static void moveHeading(double forward, double horizontal, double rotational) {
		if(rotational != 0) {
			SetTargetHeading(getCurrentHeading());
		}

		double error = normalize(getHeadingError(getTargetHeading()), getHeadingMin(), getHeadingMax());

		move(forward, horizontal, error + rotational);
	}

	public static void moveHeading(double forward, double horizontal, double rotational, boolean haltHeading,  boolean lockedHaltHeading) {
		if(rotational != 0) {
			SetTargetHeading(getCurrentHeading());
		}

		double error = normalize(getHeadingError(getTargetHeading()), getHeadingMin(), getHeadingMax());

		move(forward, horizontal, error + rotational);

		if(haltHeading || press(lockedHaltHeading, new Convert())){
			move(forward, horizontal, rotational);
		}
	}

	private static void SetDirection(double dir) {
		SetTargetHeading(dir);
	}

	public static void PointCompass(boolean[] direction){
		if(direction[0]) {
			compass = NORTH;
		} else if(direction[1]) {
			compass = SOUTH;
		} else if(direction[2]) {
			compass = EAST;
		} else if(direction[3]) {
			compass = WEST;
		} else {
			compass = NONE;
		}
		OrientCompass();
	}

	public static void OrientCompass() {
		switch(compass) {
			case NORTH:
				SetDirection(0);
				break;
			case SOUTH:
				SetDirection(90);
				break;
			case EAST:
				SetDirection(180);
				break;
			case WEST:
				SetDirection(270);
				break;
			default:
				compass = NONE;
				break;
		}
	}
}