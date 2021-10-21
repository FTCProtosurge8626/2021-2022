package org.firstinspires.ftc.teamcode.Hardware.ClassHardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.HMap;
import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.Print;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

/**
 *  @Author [Marcus Turley]
 * */
public class Servo_Hardware {

	//Creates a new array of Servos Objects
	public static Servo[] Servos = new Servo[4];

	//Initializes the Servos
	public static void InitServos(Robot_Hardware.RunMode runMode) {
		//Sets all the Servos' names
		HardwareMap(Servos, HMap, "RightFront", "LeftFront", "RightRear", "LeftRear");
		//Sets all the Servos' directions to forward
		SetDirections(Servos, Servo.Direction.FORWARD, Servo.Direction.FORWARD, Servo.Direction.FORWARD, Servo.Direction.FORWARD);

		//Checks the RunMode if RunMode is not TeleOp or Autonomous
		if(runMode == Robot_Hardware.RunMode.TeleOp) {
			SetPositions(Servos,0,0,0,0);
		} else if(runMode == Robot_Hardware.RunMode.Autonomous) {
		} else {
			//Prints unknown pointer exception
			Print.addLine("Servo_Hardware:");
			Print.addLine("RunMode is set to unknown variable.");
			Print.update();
		}
	}

	//Sets all Servos names on the phone
	public static void HardwareMap(Servo[] Servos, HardwareMap hardwareMap, String... names) {
		for(int i = 0; i < names.length; i++){
			Servos[i] = hardwareMap.get(Servo.class, names[i]);
		}
	}

	//Sets all Servos direction to a direction
	public static void SetDirections(Servo[] Servos, Servo.Direction... directions) {
		for(int i = 0; i < directions.length; i++){
			Servos[i].setDirection(directions[i]);
		}
	}

	//Sets all Servos positions based on their index
	public static void SetPositions(Servo[] Servos, double... positions) {
		for(int i = 0; i < positions.length; i++){
			if(Servos.length != positions.length)
				positions[i] = 1;
			Servos[i].setPosition(positions[i]);
		}
	}
}
