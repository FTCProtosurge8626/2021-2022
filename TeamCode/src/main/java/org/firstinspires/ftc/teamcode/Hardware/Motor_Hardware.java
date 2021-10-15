package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Motor_Hardware extends CustomLinearOpMode {
	public static void InitMotors() {
		//Sets the Motor names on the phone
		Motors[0] = hardwareMap.get(DcMotor.class, "RightFront");
		Motors[1] = hardwareMap.get(DcMotor.class, "RightRear");
		Motors[2] = hardwareMap.get(DcMotor.class, "LeftFront");
		Motors[3] = hardwareMap.get(DcMotor.class, "LeftRear");
		//Sets the Motor direction to zero
		Motors[0].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[1].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[2].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[3].setDirection(DcMotorSimple.Direction.FORWARD);
		//Sets the Motor to break when the setPower is zero instead of keeping its momentum
		Motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Motors[2].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Motors[3].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	//Reset and turn on Encoders
	public static void runEncoder() {
		Motors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		Motors[1].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		Motors[2].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		Motors[3].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		Motors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		Motors[1].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		Motors[2].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		Motors[3].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}

	//Turn off Encoders
	public static void runWithoutEncoder(){
		Motors[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		Motors[1].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		Motors[2].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		Motors[3].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	}
}
