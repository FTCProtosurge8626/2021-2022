package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Motor;

import static org.firstinspires.ftc.teamcode.Framework.Convert.toEvent;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Framework.Convert;

public class Motor_Advanced_1 extends LinearOpMode {

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
			MoveLeftOnPress(gamepad1.a);
			MoveRightOnPress(gamepad1.b);
		}
	}

	private boolean pressSwitch;

	private void MoveLeftOnPress(boolean input) {
		if(toEvent(input,new Convert()))
			pressSwitch = !pressSwitch;

		if(pressSwitch){
			LeftMotor.setPower(1);
		} else {
			LeftMotor.setPower(0);
		}
	}

	private void MoveRightOnPress(boolean input) {
		if(toEvent(input, new Convert()))
			pressSwitch = !pressSwitch;
		if(pressSwitch){
			RightMotor.setPower(1);
		} else {
			RightMotor.setPower(0);
		}
	}
}
