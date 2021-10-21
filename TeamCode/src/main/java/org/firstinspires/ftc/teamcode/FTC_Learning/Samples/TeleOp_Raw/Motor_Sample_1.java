package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Motor_Sample_1 extends LinearOpMode {

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
			StopRight(gamepad1.b);
			MoveLeft(gamepad1.x);
			StopLeft(gamepad1.y);
		}
	}

	//Causes the RightMotor to move
	private void MoveRight(boolean input) {
		if(input){
			RightMotor.setPower(1);
		}
	}

	//Causes the RightMotor to stop
	private void StopRight(boolean input) {
		if(input){
			RightMotor.setPower(0);
		}
	}

	//Causes the LeftMotor to move
	private void MoveLeft(boolean input) {
		if(input) {
			LeftMotor.setPower(1);
		}
	}

	//Causes the LeftMotor to stop
	private void StopLeft(boolean input) {
		if(input){
			LeftMotor.setPower(0);
		}
	}

}
