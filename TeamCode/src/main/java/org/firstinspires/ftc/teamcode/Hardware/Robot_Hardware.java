package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Rotation;
/**
 *  @Author [Protosurge8626]
 *  @Beta [All names and variables are temporary]
 * */
public class Robot_Hardware extends LinearOpMode {

	//Initializes all of the robot objects
	public static DcMotor[] Motors = new DcMotor[4];
	public static Servo[] Servos = new Servo[2];
	public static CRServo[] CRServos = new CRServo[2];

	// The IMU sensor object
	public static BNO055IMU IMU;

	//Compile inside of the RunOpMode Autonomous
	public void Autonomous(){
		InitializeRunMode();
		/*  Motors  */
		Motor_Hardware.runEncoder();
		Motor_Hardware.InitMotors();
		/*  Servos  */
		Servo_Hardware.InitServosAutonomous();
		/*  Sensors  */
		IMU_Hardware.InitIMU();
	}

	//Compile inside of the RunOpMode TeleOp
	public void TeleOp(){
		InitializeRunMode();
		/*  Motors  */
		Motor_Hardware.runWithoutEncoder();
		Motor_Hardware.InitMotors();
		/*  Servos  */
		Servo_Hardware.InitServosTeleOp();
		/*  Sensors  */
		IMU_Hardware.InitIMU();
	}

	//Compile inside of any of the RunOpMode
	public void InitializeRunMode(){
		/*  Motors  */
		Motors[0] = hardwareMap.get(DcMotor.class, "RightFront");
		Motors[1] = hardwareMap.get(DcMotor.class, "RightRear");
		Motors[2] = hardwareMap.get(DcMotor.class, "LeftFront");
		Motors[3] = hardwareMap.get(DcMotor.class, "LeftRear");
		/*  Servos  */
		Servos[0] = hardwareMap.get(Servo.class, "LeftArm");
		Servos[1] = hardwareMap.get(Servo.class, "RightArm");

		Servos[0].setDirection(Servo.Direction.FORWARD);
		Servos[1].setDirection(Servo.Direction.FORWARD);
		/*  CRServos  */
		CRServos[0] = hardwareMap.get(CRServo.class, "LeftShooter");
		CRServos[1] = hardwareMap.get(CRServo.class, "RightShooter");

		CRServos[0].setDirection(CRServo.Direction.FORWARD);
		CRServos[1].setDirection(CRServo.Direction.FORWARD);
		/*  Sensors */
		IMU = hardwareMap.get(BNO055IMU.class, "IMU");
	}

	@Override
	public void runOpMode() throws InterruptedException { }
}
