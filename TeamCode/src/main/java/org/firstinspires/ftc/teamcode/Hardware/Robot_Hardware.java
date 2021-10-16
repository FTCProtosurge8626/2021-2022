package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.DriveMode.Arcade;

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

	public enum DriveMode {
		Tank,
		Arcade,
		Custom
	}

	public static HardwareMap HMap;
	public static Telemetry Print;

	//Creates new RunMode and DriveMode Objects
	protected static RunMode runMode = RunMode.TeleOp;
	protected static DriveMode driveMode = Arcade;

	//Compiles inside of the RunOpMode Autonomous
	protected void InitAutonomous(){
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
	protected void InitTeleOp(DriveMode newDriveMode){
		driveMode = newDriveMode;
		runMode = RunMode.TeleOp;
		/*  Motors  */
		Motor_Hardware.InitMotors(runMode);
		/*  Servos  */
		Servo_Hardware.InitServos(runMode);
		/*  Sensors  */
		IMU_Hardware.InitIMU(runMode);
	}
}
