package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Motor_Sample_2 extends LinearOpMode {

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
			MoveRight(gamepad1.a);
			MoveLeft(gamepad1.b);
			MoveBoth(gamepad1.x);
		}
	}

	//Causes the RightMotor to move whilst the given input is true otherwise it doesn't move
	private void MoveRight(boolean input) {
		if(input){
			RightMotor.setPower(1);
		} else {
			RightMotor.setPower(0);
		}
	}

	//Causes the LeftMotor to move whilst the given input is true otherwise it doesn't move
	private void MoveLeft(boolean input) {
		if(input){
			LeftMotor.setPower(1);
		} else {
			LeftMotor.setPower(0);
		}
	}

	//Causes the both the LeftMotor and RightMotor to move whilst the given input is true otherwise it doesn't move
	private void MoveBoth(boolean input) {
		if(input){
			LeftMotor.setPower(1);
			RightMotor.setPower(1);
		} else {
			LeftMotor.setPower(0);
			RightMotor.setPower(0);
		}
	}

}
