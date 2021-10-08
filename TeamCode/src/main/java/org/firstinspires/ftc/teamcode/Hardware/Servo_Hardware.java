package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.CRServos;
import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.Servos;

public class Servo_Hardware {
	public static void InitServosAutonomous() {
		Servos[0].setPosition(0);
		Servos[1].setPosition(0);
		Servos[2].setPosition(0);
		Servos[3].setPosition(0);

		CRServos[0].setPower(0);
		CRServos[1].setPower(0);
	}

	public static void InitServosTeleOp() {
		Servos[0].setPosition(0);
		Servos[1].setPosition(0);
		Servos[2].setPosition(0);
		Servos[3].setPosition(0);

		CRServos[0].setPower(0);
		CRServos[1].setPower(0);
	}
}
