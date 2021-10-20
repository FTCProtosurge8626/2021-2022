package org.firstinspires.ftc.teamcode.RunMode.TeleOp;

import static org.firstinspires.ftc.teamcode.Hardware.Controller.*;
import static org.firstinspires.ftc.teamcode.RunMode.TeleOp.TeleOp_Methods.*;

import org.firstinspires.ftc.teamcode.Hardware.Controller;
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
		Controller Gpad1 = new Controller(gamepad1, 0);
		Controller Gpad2 = new Controller(gamepad2, 0);
		Controller Gpads = new Controller(0, Gpad1, Gpad2);

		waitForStart();
		while (opModeIsActive() && !isStopRequested()) {
			if(Rollover(Gpads.sticks[0][0], Gpads.bumpers[0])) {

			}

			moveHeading(Gpads.sticks[0][0], Gpads.sticks[0][1], Gpads.sticks[1][0],  Gpads.sticks[0][3], press(Gpads.sticks[1][3]));
		}
	}

}
