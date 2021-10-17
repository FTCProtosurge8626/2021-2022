package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.IMU_Hardware.*;
import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.Motor_Hardware.*;
import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.DriveMode.*;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

public class TeleOp_Methods extends Robot_Hardware {

	private static double[] powers = new double[4];

	public static void move(double forward, double horizontal, double rotational) {
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
			SetTargetHeading(getTargetHeading());
		}

		double error = getHeadingError(getTargetHeading()) * 0.05;

		if(forward == 0 || horizontal == 0){
			move(forward, horizontal, error + rotational);
		}

	}



}