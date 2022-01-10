package org.firstinspires.ftc.teamcode.Mach;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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