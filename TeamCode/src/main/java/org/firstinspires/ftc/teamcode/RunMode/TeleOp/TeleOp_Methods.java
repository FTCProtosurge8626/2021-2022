package org.firstinspires.ftc.teamcode.RunMode.TeleOp;
import org.firstinspires.ftc.teamcode.Hardware.IMU_Hardware;
import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

public class TeleOp_Methods extends Robot_Hardware {

	public static void movePower(double FLPower, double FRPower, double BLPower, double BRPower) {
		Motors[0].setPower(FLPower);
		Motors[1].setPower(FRPower);
		Motors[2].setPower(BLPower);
		Motors[3].setPower(BRPower);
	}

	static double moveTarget;
	static double moveTurn;

	static void move(double forward, double sideways, double rotation, boolean inverse) {

		double FLPower = forward - sideways + rotation;
		double FRPower = forward + sideways - rotation;
		double BLPower = forward + sideways + rotation;
		double BRPower = forward - sideways - rotation;

		movePower(FLPower, FRPower, BLPower, BRPower);

	}

	static void moveReorient(double forward, double sideways, double rotation, boolean inverse) {

		if(rotation != 0)
			moveTarget = IMU_Hardware.getHeading();

		moveTurn = (IMU_Hardware.getHeading() - moveTarget) * .1;

		move(forward, sideways, moveTurn + rotation, inverse);

	}

}