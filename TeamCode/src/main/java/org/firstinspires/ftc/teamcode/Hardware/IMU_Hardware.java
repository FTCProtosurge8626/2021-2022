package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.IMU;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class IMU_Hardware {

	// State used for updating telemetry
	public static Orientation angles;
	public static Acceleration gravity;

	public static void InitIMU(){
		BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
		parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
		parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
		parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
		parameters.loggingEnabled      = true;
		parameters.loggingTag          = "IMU";
		parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
		IMU.initialize(parameters);
	}

	public static double getHeading() {
		IMU_Hardware.angles = IMU.getAngularOrientation();
		return IMU_Hardware.angles.firstAngle;
	}
}
