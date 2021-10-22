package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Motor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


//@Purpose Sleep halts anything that executes after the sleep whilst keeping anything previously running at a constant rate


public class Motor_Simple_2 extends LinearOpMode {

	//Creates new DcMotor instances
	DcMotor RightMotor;
	DcMotor LeftMotor;

	@Override
	public void runOpMode() {

		//Assigns the motors within the program to the motors on the robot
		RightMotor = hardwareMap.get(DcMotor.class, "MotorRight");
		LeftMotor = hardwareMap.get(DcMotor.class, "MotorLeft");

		//Assigns the motors within the program to the motors on the robot
		RightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
		LeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

		waitForStart();

		while (opModeIsActive() && !isStopRequested()) {
			RightMotor.setPower(1);
			LeftMotor.setPower(1);
			sleep(1000);
			RightMotor.setPower(0);
			LeftMotor.setPower(0);
			sleep(1000);
			RightMotor.setPower(0.5);
			LeftMotor.setPower(0.5);
			sleep(1000);
			RightMotor.setPower(1);
			LeftMotor.setPower(0);
			sleep(1000);
			RightMotor.setPower(0);
			LeftMotor.setPower(1);
			sleep(1000);
		}
	}
}
