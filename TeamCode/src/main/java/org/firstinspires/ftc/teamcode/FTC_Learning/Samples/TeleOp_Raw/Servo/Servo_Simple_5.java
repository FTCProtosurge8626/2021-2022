package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


//@Purpose You can also make methods set the Servos direction


public class Servo_Simple_5 extends LinearOpMode {

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
			RightPosition(gamepad1.a, 1, 0);
			RightPosition(gamepad1.b, -1 , 1);
		}
	}

	//Causes the RightServo go to an assigned position
	private void RightPosition(boolean input, double position1, double position2) {
		if(input){
			RightServo.setPosition(position1);
		} else {
			RightServo.setPosition(position2);
		}
	}

	//Causes the LeftServo go to an assigned position
	private void LeftPosition(boolean input, double position1, double position2) {
		if(input) {
			LeftServo.setPosition(position1);
		} else {
			LeftServo.setPosition(position2);
		}
	}
}
