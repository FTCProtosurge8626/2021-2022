package org.firstinspires.ftc.teamcode.Sam.Basic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(group = "Autonomous", name = "Autonomous: Depot")
public class BasicAutonomousDepot extends BasicHardware{

    // todo: write your code here
    public void runOpMode() {
        initialize();

        waitForStart();
        //Code goes here
        forward(.6,275);
    }
    public void forward(double power, int wait) {
        power *= -1;
        backRight.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        frontLeft.setPower(power);
        sleep(wait);
        stopAllMotors();
    }
    public void turn(double power, int wait) {
        power *= -1;
        backRight.setPower(-power);
        backLeft.setPower(power);
        frontRight.setPower(-power);
        frontLeft.setPower(power);
        sleep(wait);
        stopAllMotors();
    }
    public void strafe(double power, int wait) {
        power *= -1;
        backRight.setPower(-power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        frontLeft.setPower(-power);
        sleep(wait);
        stopAllMotors();
    }
    public void stopAllMotors() {
        backRight.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setPower(0);
    }
}