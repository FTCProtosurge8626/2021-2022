package org.firstinspires.ftc.teamcode.Sam.Basic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(group = "Autonomous", name = "Autonomous: Red Warehouse")
public class BasicAutonomousRed extends BasicHardware{

    // todo: write your code here
    public void runOpMode() {
        initialize();

        waitForStart();
        //Code goes here
        strafe(.5,1000);
        forward(.6,2000);
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