package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import static org.firstinspires.ftc.teamcode.Hardware.Controller.initSticks;
import static org.firstinspires.ftc.teamcode.RunMode.TeleOp.TeleOp_Methods.*;

import org.firstinspires.ftc.teamcode.Hardware.Robot_Hardware;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 *  @Author [Marcus Turley]
 * */
@TeleOp(name = "Run: RunModeTeleOp", group = "TeleOP")
public class RunMode_TeleOp extends Robot_Hardware {
	@Override
	public void runOpMode() {
		InitTeleOp(DriveMode.Arcade);

		waitForStart();
		while (opModeIsActive() && !isStopRequested()) {
			move(initSticks()[1], initSticks()[0], initSticks()[3]);
		}
	}

}