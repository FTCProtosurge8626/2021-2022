package org.firstinspires.ftc.teamcode.Hardware.ClassHardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.HMap;
import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.Print;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Framework.LinearOpMode_Handler;
import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

/**
 *  @Author [Marcus Turley]
 * */
public class Motor_Hardware extends LinearOpMode_Handler {

	//Creates a new array of DCMotor Objects
	public static DcMotor[] Motors = new DcMotor[4];

	//Initializes the motors
	public static void InitMotors(Robot_Hardware.RunMode runMode) {
		//Sets all the motors' names
		HardwareMap(Motors, HMap, "RightFront", "LeftFront", "RightRear", "LeftRear");
		//Sets all the motors' directions to forward
		SetDirections(Motors, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
		//Sets all the motors'
		BrakeBehaviour(Motors, DcMotor.ZeroPowerBehavior.BRAKE);

		//Checks the RunMode if RunMode is not TeleOp or Autonomous
		if(runMode == Robot_Hardware.RunMode.TeleOp) {
			//Sets all the motors to RunWithoutEncoder
			EncoderMode(Motors, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		} else if(runMode == Robot_Hardware.RunMode.Autonomous) {
			//Sets all the motors to RunUsingEncoder
			EncoderMode(Motors, DcMotor.RunMode.RUN_USING_ENCODER);
		} else {
			//Prints unknown pointer exception
			Print.addLine("Motor_Hardware:");
			Print.addLine("RunMode is set to unknown variable.");
			Print.update();
		}
	}

	//Sets all motors names on the phone
	public static void HardwareMap(DcMotor[] Motors, HardwareMap hardwareMap, String... names) {
		for(int i = 0; i < names.length; i++){
			Motors[i] = hardwareMap.get(DcMotor.class, names[i]);
		}
	}

	//Sets all motors to a ZeropowersBehavior
	public static void BrakeBehaviour(DcMotor[] Motors, DcMotor.ZeroPowerBehavior... behaviours) {
		for(int i = 0; i < behaviours.length; i++){
			Motors[i].setZeroPowerBehavior(behaviours[i]);
		}
	}

	//Sets all motors encoders to a RunMode
	public static void EncoderMode(DcMotor[] Motors, DcMotor.RunMode... runMode) {
		for(int i = 0; i < runMode.length; i++){
			Motors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			Motors[i].setMode(runMode[i]);
		}
	}

	//Sets all motors direction to a direction
	public static void SetDirections(DcMotor[] Motors, DcMotorSimple.Direction... dir) {
		for(int i = 0; i < dir.length; i++){
			Motors[i].setDirection(dir[i]);
		}
	}

	//Sets all motors powers based on their index
	public static void SetPowers(DcMotor[] Motors, double... powers) {
		if(powers.length < Motors.length) {
			for(int i = 0; i < powers.length; i++) {
				Motors[i].setPower(powers[i]);
			}
		} else {

		}
	}
}
