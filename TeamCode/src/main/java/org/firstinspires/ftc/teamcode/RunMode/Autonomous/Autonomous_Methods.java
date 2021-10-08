package org.firstinspires.ftc.teamcode.RunMode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware.IMU_Hardware;
import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Autonomous_Methods extends Robot_Hardware {

	public void driveHeading(double forward, double target, double timeout) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();

		while(opModeIsActive()) {
			if(timer.seconds() > timeout) break; {
				double heading = IMU_Hardware.getHeading();
				double turn = (heading-target) * 0.02;
				Motors[0].setPower(forward + turn);
				Motors[1].setPower(forward - turn);
				Motors[2].setPower(forward + turn);
				Motors[3].setPower(forward - turn);
			}
		}
	}

}