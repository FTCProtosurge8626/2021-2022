package org.firstinspires.ftc.teamcode.Hardware.ClassHardware;

import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.HMap;
import static org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware.Print;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

/**
 *  @Author [Marcus Turley]
 * */
public class CRServo_Hardware {

	//Creates a new array of CRServo Objects
	public static CRServo[] CRServos = new CRServo[4];

	//Initializes the CRServos
	public static void InitCRServos(Robot_Hardware.RunMode runMode) {
		//Sets all the CRServos' names
		HardwareMap(CRServos, HMap, "CRServo1", "CRServo12");
		//Sets all the CRServos' directions to forward
		SetDirections(CRServos, CRServo.Direction.FORWARD, CRServo.Direction.FORWARD);
	}

	//Sets all CRServos names on the phone
	public static void HardwareMap(CRServo[] CRServos, HardwareMap hardwareMap, String... names) {
		for(int i = 0; i < names.length; i++){
			CRServos[i] = hardwareMap.get(CRServo.class, names[i]);
		}
	}
	//Sets all CRServos direction to a direction
	public static void SetDirections(CRServo[] CRServos, CRServo.Direction... directions) {
		for(int i = 0; i < directions.length; i++){
			CRServos[i].setDirection(directions[i]);
		}
	}

	//Sets all CRServos power based on their index
	public static void SetPowers(CRServo[] CRServos, double... powers) {
		for(int i = 0; i < powers.length; i++){
			CRServos[i].setPower(powers[i]);
		}
	}
}
