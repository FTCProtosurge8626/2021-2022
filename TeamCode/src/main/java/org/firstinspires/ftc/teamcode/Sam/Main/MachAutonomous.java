package org.firstinspires.ftc.teamcode.Sam.Main;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="TEST", group="Autonomous")
public class MachAutonomous extends MachHardware {
    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        motors[1].setPower(1);
        sleep(500);
    }
}