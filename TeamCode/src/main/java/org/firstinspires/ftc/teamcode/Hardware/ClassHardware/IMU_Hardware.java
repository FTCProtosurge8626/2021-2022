package org.firstinspires.ftc.teamcode.Hardware.ClassHardware;


import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.HMap;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

/**
 *  @Author [Marcus Turley]
 * */
public class IMU_Hardware {

	//Creates an new IMU Object
	public static BNO055IMU IMU;

	//Creates variables for the orientation and gravity
	public static Orientation angles;
	public static Acceleration gravity;

	//Creates a variable for the target heading
	private static double targetHeading;

	//Initializes the IMU
	public static void InitIMU(Robot_Hardware.RunMode runMode){
		//Sets name of the IMU
		IMU = HMap.get(BNO055IMU.class, "IMU");

		//Sets parameters of the IMU
		BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
		parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
		parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
		parameters.calibrationDataFile = "BNO055IMUCalibration.json"; //Need to Calibrate

		//Sets executes the parameters of theIMU
		IMU.initialize(parameters);
	}

	//Gets the IMU's current orientation
	public static double getCurrentHeading() {
		IMU_Hardware.angles = IMU.getAngularOrientation();
		return IMU_Hardware.angles.firstAngle;
	}

	//Gets the IMU's target orientation
	public static void SetTargetHeading(double newTarget) {
		targetHeading = newTarget;
	}

	//Gets the IMU's target orientation
	public static double getTargetHeading() {
		return targetHeading;
	}

	//Gets the IMU's distance to target heading
	public static double getHeadingError(double targetHeading) {
		IMU_Hardware.angles = IMU.getAngularOrientation();
		return IMU_Hardware.angles.firstAngle - targetHeading;
	}
}
