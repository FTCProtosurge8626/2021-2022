package org.firstinspires.ftc.teamcode.FTC_Learning.Samples.TeleOp_Raw.Control_Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;


//@Purpose CRServo Acts just like a motor with only the initialization stage appearing different


public class Control_Servo_Simple_1 extends LinearOpMode {

	//Creates new DcMotor instances
	CRServo RightCRServo;
	CRServo LeftCRServo;

	@Override
	public void runOpMode() {

		//Assigns the motors within the program to the motors on the robot
		RightCRServo = hardwareMap.get(CRServo.class, "CRServoRight");
		LeftCRServo = hardwareMap.get(CRServo.class, "CRServoLeft");

		//Assigns the motors within the program to the motors on the robot
		RightCRServo.setDirection(CRServo.Direction.FORWARD);
		LeftCRServo.setDirection(CRServo.Direction.FORWARD);

		waitForStart();

		while (opModeIsActive() && !isStopRequested()) {
			RightCRServo.setPower(1);
			LeftCRServo.setPower(1);
		}
	}
}
