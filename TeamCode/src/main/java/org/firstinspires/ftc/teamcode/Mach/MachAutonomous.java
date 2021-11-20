package org.firstinspires.ftc.teamcode.Mach;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Pushbot: MachAutonomous", group="Autonomous")
public class MachAutonomous extends MachHardware {

    /* Declare OpMode members. */
    private ElapsedTime	 runtime = new ElapsedTime();

    static final double	 COUNTS_PER_MOTOR_REV	= 1440 ;	// eg: TETRIX Motor Encoder
    static final double	 DRIVE_GEAR_REDUCTION	= 2.0 ;	 // This is < 1.0 if geared UP
    static final double	 WHEEL_DIAMETER_INCHES   = 4.0 ;	 // For figuring circumference
    static final double	 COUNTS_PER_INCH		 = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double	 DRIVE_SPEED			 = 0.6;
    static final double	 TURN_SPEED			  = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        initialize();

        // Send telemetry message to signify robot waiting;
        motors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[1].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[2].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[3].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors[1].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors[2].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors[3].setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d :%7d :%7d",
                motors[0].getCurrentPosition(),
                motors[1].getCurrentPosition(),
                motors[2].getCurrentPosition(),
                motors[3].getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  48,  48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout

        sleep(1000);	 // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = motors[0].getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = motors[1].getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            motors[0].setTargetPosition(newLeftTarget);
            motors[1].setTargetPosition(newRightTarget);
            motors[2].setTargetPosition(newLeftTarget);
            motors[3].setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            motors[0].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors[2].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors[3].setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            motors[0].setPower(speed);
            motors[1].setPower(speed);
            motors[2].setPower(speed);
            motors[3].setPower(speed);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (motors[0].isBusy() && motors[1].isBusy() && motors[2].isBusy() && motors[3].isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d :%7d :%7d",
                        motors[0].getCurrentPosition(),
                        motors[1].getCurrentPosition(),
                        motors[2].getCurrentPosition(),
                        motors[3].getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            motors[0].setPower(0);
            motors[1].setPower(0);
            motors[2].setPower(0);
            motors[3].setPower(0);

            // Turn off RUN_TO_POSITION
            motors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motors[1].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motors[2].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motors[3].setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
