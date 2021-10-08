package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.IMU_Hardware;

@TeleOp(name = "Run: RunModeTeleOp", group = "TeleOP")
public class RunMode_TeleOp extends TeleOp_Methods {

	@Override
	public void runOpMode() {
		TeleOp();

		waitForStart();
		while (opModeIsActive() && !isStopRequested()) {

		}
	}

}