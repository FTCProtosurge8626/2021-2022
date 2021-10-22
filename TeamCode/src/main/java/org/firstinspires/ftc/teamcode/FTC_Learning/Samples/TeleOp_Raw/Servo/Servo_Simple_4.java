package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


//@Purpose You can also make methods set the Servos direction


public class Servo_Simple_4 extends LinearOpMode {

	//Creates new DcMotor instances
	Servo RightServo;
	Servo LeftServo;

	@Override
	public void runOpMode() {

		//Assigns the motors within the program to the motors on the robot
		RightServo = hardwareMap.get(Servo.class, "ServoRight");
		LeftServo = hardwareMap.get(Servo.class, "ServoLeft");

		//Assigns the motors within the program to the motors on the robot
		RightServo.setDirection(Servo.Direction.FORWARD);
		LeftServo.setDirection(Servo.Direction.FORWARD);

		waitForStart();

		while (opModeIsActive() && !isStopRequested()) {
			RightPosition(gamepad1.a);
			RightPosition(gamepad1.b);
		}
	}

	//Causes the RightServo go to an assigned position
	private void RightPosition(boolean input) {
		if(input){
			RightServo.setPosition(1);
		} else {
			RightServo.setPosition(-1);
		}
	}

	//Causes the LeftServo go to an assigned position
	private void LeftPosition(boolean input) {
		if(input) {
			LeftServo.setPosition(1);
		} else {
			LeftServo.setPosition(-1);
		}
	}
}
