package org.firstinspires.ftc.teamcode.Mach;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MachHardware extends LinearOpMode {
    /* Public OpMode members. */
    DcMotor[] motors = new DcMotor[4];

    /* Initialize standard Hardware interfaces */
    public void initialize() {

        // Define and Initialize Motors
        motors[0]  = hardwareMap.get(DcMotor.class, "back_left_drive");
        motors[1]  = hardwareMap.get(DcMotor.class, "back_right_drive");
        motors[2] = hardwareMap.get(DcMotor.class, "front_left_drive");
        motors[3] = hardwareMap.get(DcMotor.class, "front_right_drive");

        // Set the motor directions. Two motors will need to be reversed
        motors[0].setDirection(DcMotor.Direction.FORWARD);
        motors[1].setDirection(DcMotor.Direction.REVERSE);
        motors[2].setDirection(DcMotor.Direction.FORWARD);
        motors[3].setDirection(DcMotor.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[2].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[3].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        motors[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[1].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[2].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[3].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void runOpMode() throws InterruptedException {

    }
}