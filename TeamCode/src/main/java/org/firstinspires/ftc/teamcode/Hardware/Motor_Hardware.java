package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Motor_Hardware {
	public static void InitMotors(){
		Motors[0].setPower(0);
		Motors[1].setPower(0);
		Motors[2].setPower(0);
		Motors[3].setPower(0);
		Motors[0].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[1].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[2].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[3].setDirection(DcMotorSimple.Direction.FORWARD);
		Motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Motors[2].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Motors[3].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	//Reset and turn on Encoders
	public static void runEncoder(){
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
