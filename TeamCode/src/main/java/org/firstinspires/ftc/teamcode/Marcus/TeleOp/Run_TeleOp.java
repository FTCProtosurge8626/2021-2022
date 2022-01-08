package org.firstinspires.ftc.teamcode.Marcus.RunMode.TeleOp;

import org.firstinspires.ftc.teamcode.Marcus.Framework.Controller_1;
import org.firstinspires.ftc.teamcode.Marcus.Framework.Converter;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.IMU_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.Motor_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Main_Hardware;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 *  @Author [Marcus Turley]
 * */
@TeleOp(name = "Marcus: TeleOp", group = "TeleOP")
public class Run_TeleOp extends Main_Hardware {
    private int counter = 0;

    @Override
    public void runOpMode() {
        initTeleOp(RunMode.TeleOp, hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            //TeleOp_Methods.SetDirection(0);
            Controller_1 Gpad1 = new Controller_1(0.0, gamepad1);
            Controller_1 Gpad2 = new Controller_1(0.0, gamepad2);
            Controller_1 Gpads = new Controller_1(0.0, Gpad1, Gpad2);

            //TeleOp_Methods.pointCompass(Gpads.hatSticks);
            //IMU_Hardware_1.orientCompass();
/*
            telemetry.addData("Heading", IMU_Hardware_1.heading());
            telemetry.addData("Target", IMU_Hardware_1.target());
            telemetry.addData("Power", IMU_Hardware_1.error());
            telemetry.addData("InRange", IMU_Hardware_1.inRange());
            telemetry.addData("InTurn", TeleOp_Methods.inTurn);
            telemetry.update();
*/
            //TeleOp_Methods.moveCompass(IMU_Hardware_1.target(), Gpad1.sticks[0][0],Gpad1.sticks[0][1],Gpad1.sticks[1][0], Gpad1.hatSticks, this);
            //TeleOp_Methods.changePower(Controller_1.press(Gpads.bumpers[1]), Controller_1.press(Gpads.bumpers[0]));
            org.firstinspires.ftc.teamcode.Marcus.RunMode.TeleOp.TeleOp_Methods.move(Gpad1.sticks[0][0],Gpad1.sticks[0][1],Gpad1.sticks[1][1], Gpad1.hatSticks);
            //TeleOp_Methods.ReOrient(Gpads.sticks[0][0],Gpads.sticks[0][1],Gpads.sticks[1][0]);
            //TeleOp_Methods.moveHeading(Gpads.sticks[0][0],Gpads.sticks[0][1],Gpads.sticks[1][0], this);
            org.firstinspires.ftc.teamcode.Marcus.RunMode.TeleOp.TeleOp_Methods.intake(Gpad2.buttons,Gpad2.triggers,Gpad2.sticks[0][1]);
            org.firstinspires.ftc.teamcode.Marcus.RunMode.TeleOp.TeleOp_Methods.moveCarousel(Gpad1.bumpers[0], Gpad1.bumpers[1]);
        }
    }
}