package org.firstinspires.ftc.teamcode.Marcus.RunMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Marcus.Framework.Controller;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.IMU_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Main_Hardware;

@TeleOp(group = "TeleOp", name = "Runmode: Drives")
public class Drive_TeleOp extends Main_Hardware {

    @Override
    public void runOpMode() {

        InitTeleOp(RunMode.TeleOp, hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            //TeleOp_Methods.SetDirection(0);
            Controller Gpad1 = new Controller(gamepad1, 0.0);
            Controller Gpad2 = new Controller(gamepad2, 0.0);
            Controller Gpads = new Controller(0.0, Gpad1, Gpad2);
			/*
			telemetry.addData("0 Degrees", IMU_Hardware_1.getHeadingError(0));
			telemetry.addData("90 Degrees", IMU_Hardware_1.getHeadingError(90));
			telemetry.addData("-90 Degrees", IMU_Hardware_1.getHeadingError(-90));
			telemetry.addData("180 Degrees", IMU_Hardware_1.getHeadingError(180));
			*/
            //telemetry.addData("Test $d", IMU_Hardware_1.getHeadingError(IMU_Hardware_1.getTargetHeading()));

            TeleOp_Methods.move(Gpads.sticks[0][0],Gpads.sticks[0][1],Gpads.sticks[1][0]);
            //TeleOp_Methods.PointCompass(Gpads.hatSticks);
            //TeleOp_Methods.moveHeading(Gpads.sticks[0][0],Gpads.sticks[0][1],Gpads.sticks[1][0]);
        }
    }
}