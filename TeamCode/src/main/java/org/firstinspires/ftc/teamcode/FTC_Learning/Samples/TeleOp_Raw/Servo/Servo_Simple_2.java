package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Servo_Simple_2 extends LinearOpMode {

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
			RightServo.setPosition(1);
			RightServo.setPosition(1);
			sleep(1000);
			RightServo.setPosition(0);
			RightServo.setPosition(0);
			sleep(1000);
			RightServo.setPosition(-1);
			RightServo.setPosition(-1);
			sleep(1000);
		}
	}
}
