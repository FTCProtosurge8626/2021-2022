package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Motor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


//@Purpose You can make methods sets the motor power to a specified power with a single method


public class Motor_Simple_5 extends LinearOpMode {

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
			MoveRight(gamepad1.a, 0.2);
			MoveLeft(gamepad1.b,0.8);
			MoveRight(gamepad1.right_stick_x);
			MoveLeft(gamepad1.left_stick_x);
		}
	}

	//Causes the RightMotor to move whilst the given input is true otherwise it doesn't move and it moves at a power of the input value
	private void MoveRight(boolean input, double power) {
		if(input){
			RightMotor.setPower(power);
		} else {
			RightMotor.setPower(0);
		}
	}

	//Causes the LeftMotor to move whilst the given input is true otherwise it doesn't move and it moves at a power of the input value
	private void MoveLeft(boolean input, double power) {
		if(input){
			LeftMotor.setPower(power);
		} else {
			LeftMotor.setPower(0);
		}
	}

	//Causes the RightMotor to move at a power of the input value
	private void MoveRight(double power) {
		RightMotor.setPower(power);
	}

	//Causes the LeftMotor to move at a power of the input value
	private void MoveLeft(double power) {
		LeftMotor.setPower(power);
	}
}
