package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 *  @Author [Marcus Turley]
 * */
@TeleOp(name = "Run: RunModeTeleOp", group = "TeleOP")
public class RunMode_TeleOp extends Robot_Hardware {
	@Override
	public void runOpMode() {
		InitTeleOp();

		waitForStart();
		while (opModeIsActive() && !isStopRequested()) {

		}
	}

}