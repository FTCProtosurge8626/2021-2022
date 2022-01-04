package org.firstinspires.ftc.teamcode.Sam.Main;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;

/**
 * {@link org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU} gives a short demo on how to use the BNO055 Inertial Motion Unit (IMU) from AdaFruit.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 *
 * @see <a href="http://www.adafruit.com/products/2472">Adafruit IMU</a>
 */
@TeleOp(name = "TeleOp With IMU", group = "Sensor")
@Disabled
public class TeleOpWithIMU extends MachHardware {
    //----------------------------------------------------------------------------------------------
    // State
    //----------------------------------------------------------------------------------------------

    // The IMU sensor object
    BNO055IMU imu;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    // Setup a variable for each drive wheel to save power level for telemetry
    private double backLeftPower;
    private double backRightPower;
    private double frontLeftPower;
    private double frontRightPower;

    //Defines left stick and right stick drive inputs
    private double drive;
    private double strafe;
    private double turn;
    private double total;

    private double leftForward;
    private double rightForward;
    private double leftStrafe;
    private double rightStrafe;

    private double maxSpeed;

    //----------------------------------------------------------------------------------------------
    // Main logic
    //----------------------------------------------------------------------------------------------

    @Override
    public void runOpMode() {

        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit		   = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit		   = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled	  = true;
        parameters.loggingTag		  = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        initialize();

        // Set up our telemetry dashboard
        composeTelemetry();
        Swap driveMode = Swap.POV;
        maxSpeed = 1;

        // Wait until we're told to go
        waitForStart();

        // Start the logging of measured acceleration
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        // Loop and update the dashboard
        while (opModeIsActive()) {
            if (gamepad1.a && !gamepad1.b) {
                driveMode = Swap.POV;
            } else if (!gamepad1.a && gamepad1.b) {
                driveMode = Swap.TANK;
            }

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            if (driveMode == Swap.POV) {
                drive = gamepad1.left_stick_y; //Checks the left stick on the controller for vertical offset
                strafe = gamepad1.left_stick_x; //Checks the left stick on the controller for horizontal offset
                turn  = gamepad1.right_stick_x; //Checks the right stick on the controller for horizontal offset
                total =  Math.abs(drive) + Math.abs(strafe) + Math.abs(turn);

                //This code is to ensure that no values would go over 1.0 or under -1.0, and to maintain aspect ratio so movement is not wonkified by large values

                if (total > maxSpeed) {
                    drive /= total;
                    strafe /= total;
                    turn /= total;
                }

                //Calculate required movement based on given inputs

                backLeftPower	  = Range.clip(drive - strafe - turn, -maxSpeed, maxSpeed);
                backRightPower	  = Range.clip(drive + strafe + turn, -maxSpeed, maxSpeed);
                frontLeftPower	  = Range.clip(drive + strafe - turn, -maxSpeed, maxSpeed);
                frontRightPower   = Range.clip(drive - strafe + turn, -maxSpeed, maxSpeed);
            } else {
                leftForward = gamepad1.left_stick_y;
                rightForward = gamepad1.right_stick_y;
                leftStrafe = gamepad1.left_stick_x;
                rightStrafe = gamepad1.right_stick_x;

                total = Math.abs(leftForward) + Math.abs(leftStrafe);
                if (total > maxSpeed) {
                    leftStrafe /= total;
                    leftForward /= total;
                }
                total = Math.abs(rightForward) + Math.abs(rightStrafe);
                if (total > maxSpeed) {
                    rightStrafe /= total;
                    rightForward /= total;
                }

                backLeftPower	  = Range.clip(leftForward	- leftStrafe, -maxSpeed, maxSpeed);
                backRightPower	  = Range.clip(rightForward + rightStrafe, -maxSpeed, maxSpeed);
                frontLeftPower	  = Range.clip(leftForward	+ leftStrafe, -maxSpeed, maxSpeed);
                frontRightPower   = Range.clip(rightForward - rightStrafe, -maxSpeed, maxSpeed);
            }

            motors[0].setPower(backLeftPower);
            motors[1].setPower(backRightPower);
            motors[2].setPower(frontLeftPower);
            motors[3].setPower(frontRightPower);

            if (gamepad1.y) {
                telemetry.update();
            } else {
                telemetry.clear();
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Telemetry Configuration
    //----------------------------------------------------------------------------------------------

    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
                angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity  = imu.getGravity();
            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel*gravity.xAccel
                                        + gravity.yAccel*gravity.yAccel
                                        + gravity.zAccel*gravity.zAccel));
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}

