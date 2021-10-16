package org.firstinspires.ftc.teamcode.RunMode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware.ClassHardware.IMU_Hardware;

@Autonomous(name = "Run: RunModeAutonomous", group = "Autonomous")
public class RunMode_Autonomous extends Autonomous_Methods {

	@Override
	public void runOpMode(){
		InitAutonomous();
		
		waitForStart();

	}

}