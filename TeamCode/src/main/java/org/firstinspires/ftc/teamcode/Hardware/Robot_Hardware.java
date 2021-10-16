package org.firstinspires.ftc.teamcode.Hardware;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.ClassHardware.IMU_Hardware;
import org.firstinspires.ftc.teamcode.Hardware.ClassHardware.Motor_Hardware;
import org.firstinspires.ftc.teamcode.Hardware.ClassHardware.Servo_Hardware;

/**
 *  @Author [Marcus Turley]
 * */
public class Robot_Hardware extends LinearOpMode_Handler {

	public enum RunMode {
		TeleOp,
		Autonomous,
	}

	public static HardwareMap HMap;
	public static Telemetry Print;

	//Creates an new RunMode Object
	private static RunMode runMode = RunMode.TeleOp;

	//Compiles inside of the RunOpMode Autonomous
	public void InitAutonomous(){
		HMap = hardwareMap;
		Print = telemetry;

		runMode = RunMode.Autonomous;
		/*  Motors  */
		Motor_Hardware.InitMotors(runMode);
		/*  Servos  */
		Servo_Hardware.InitServos(runMode);
		/*  Sensors  */
		IMU_Hardware.InitIMU(runMode);
	}

	//Compiles inside of the RunOpMode TeleOp
	public void InitTeleOp(){
		runMode = RunMode.TeleOp;
		/*  Motors  */
		Motor_Hardware.InitMotors(runMode);
		/*  Servos  */
		Servo_Hardware.InitServos(runMode);
		/*  Sensors  */
		IMU_Hardware.InitIMU(runMode);
	}
}
