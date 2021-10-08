package org.firstinspires.ftc.teamcode.RunMode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware.IMU_Hardware;

@Autonomous(name = "Run: RunModeAutonomous", group = "Autonomous")
public class RunMode_Autonomous extends Autonomous_Methods {

	@Override
	public void runOpMode(){
		Autonomous();

		waitForStart();
		while(opModeIsActive() && !isStopRequested()) {
			double heading = IMU_Hardware.angles.firstAngle;

			double forward = 0;
			double turn = heading * 0.02;

			driveHeading(gamepad1.left_stick_y, 10, 5);
			driveHeading(gamepad1.left_stick_y, -45, 3);

			telemetry.addData("Status", "Running");
			telemetry.addData("Angles", IMU_Hardware.angles.toString());
			telemetry.addData("Angle 1", IMU_Hardware.angles.firstAngle);
			telemetry.update();
		}
	}

}