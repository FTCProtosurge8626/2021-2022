package org.firstinspires.ftc.teamcode.RunMode.Autonomous;

import static org.firstinspires.ftc.teamcode.Hardware.ClassHardware.Motor_Hardware.*;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

public class Autonomous_Methods extends Robot_Hardware {
	public static void move(double speed, int waitTime) {
		SetPowers(Motors,speed);
		//sleep(waitTime);
	}
}