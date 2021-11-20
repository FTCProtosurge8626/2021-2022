package org.firstinspires.ftc.teamcode.Basic;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.Range;
import java.util.Date;

@Disabled
@TeleOp(name= "RunMode: Efficient_Battery_Test", group = "TeleOp")
//We extend Basic_Hardware to be able to reference it's methods
public class BatteryEfficientTeleOp extends BasicHardware {

    // Setup a variable for each drive wheel to save power level for telemetry
    private double backLeftPower;
    private double backRightPower;
    private double frontLeftPower;
    private double frontRightPower;

    private double suggestedRange; //Rotational and horizontal
    private double sr;
    private double suggestedLinearRange;
    private double slr;

    //Defines left stick and right stick drive inputs
    private double turnBoost;
    private double strafeBoost;
    private double forwardBoost;
    private double total;
    private boolean goingForward;

    private int weight;
    private boolean going;
    private long timeSincePress;
    private long timeSinceDrift;
    private long delay;
    private Date currentDate;
    private static DriveMode driveMode = DriveMode.POV;

    private enum DriveMode{
        POV,
        Tank
    }

    private String test;

    //This overrides the runOpMode method from the LinearOpMode class and when the program is executed
    @Override
    public void runOpMode() {
        //We call the initialize from the BasicHardware class
        initialize();

        //Initialize variables
        double powerSaved;
        double backLeftPowerTemp;
        double backRightPowerTemp;
        double frontLeftPowerTemp;
        double frontRightPowerTemp;
        going = false;
        backLeftPower = 0;
        backRightPower = 0;
        frontLeftPower = 0;
        frontRightPower = 0;
        suggestedRange = 0.7;
        sr = suggestedRange;
        suggestedLinearRange = 0.2;
        slr = suggestedLinearRange;
        weight = 20;
        turnBoost = 1.25;
        strafeBoost = 1.5;
        forwardBoost = 1;
        goingForward = false;
        currentDate = new Date();
        timeSincePress = currentDate.getTime();
        delay = 1000;

        //This will wait for the run button to be pressed and continue compilation
        waitForStart();	//@Description This is called from LinearOpMode

        //This will keep looping for the entirety of runtime so that the inputs can be read constantly
        //This also checks to see if the program has been forced stop to stop the while loop to prevent an error

        while (opModeIsActive() && !isStopRequested()) {
            //We input the gamepad that we are using along with the input we wish to output by using gamepad[#number].[input]
            backLeftPowerTemp = backLeftPower;
            backRightPowerTemp = backRightPower;
            frontLeftPowerTemp = frontLeftPower;
            frontRightPowerTemp = frontRightPower;

            //Speed mode
            turbo();

            //Adjustable initialWeight
            drift();

            //Change driveMode based on inputs
            driveMode();

            //Gets outputs based on inputs
            switch (driveMode) {
                case Tank:
                    driveTank(gamepad1.left_stick_y, gamepad1.right_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, sr, slr);
                    break;
                case POV:
                    //Fallthrough
                default:
                    drive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x, sr, slr);
            }

            goingForward = Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x) + Math.abs(gamepad1.right_stick_x);

            //For telemetry
            //powerSaved = Math.abs(backLeftPower) + Math.abs(backRightPower) + Math.abs(frontLeftPower) + Math.abs(frontRightPower);

            //Normalize outputs based on previous outputs
            backLeftPower   = normalize(backLeftPowerTemp,backLeftPower,weight,sr, slr,15);
            backRightPower  = normalize(backRightPowerTemp,backRightPower,weight,sr, slr,15);
            frontLeftPower  = normalize(frontLeftPowerTemp,frontLeftPower,weight,sr, slr,15);
            frontRightPower = normalize(frontRightPowerTemp,frontRightPower,weight,sr, slr,15);

            //For telemetry
            //powerSaved -= Math.abs(backLeftPower) + Math.abs(backRightPower) + Math.abs(frontLeftPower) + Math.abs(frontRightPower);

            //Sends the outputs to the motors, do not delete
            setPower();

            //telemetry.update();
        }
    }

    private void drift() {
        if (gamepad1.left_bumper || gamepad1.right_bumper) {
            currentDate = new Date();
            long milliSeconds = currentDate.getTime();
            if (timeSinceDrift + delay >= milliSeconds) {
                if (gamepad1.left_bumper && gamepad1.right_bumper) {
                    weight = 20;
                    timeSinceDrift = milliSeconds;
                } else if (gamepad1.left_bumper && weight >= 5) {
                    weight -= 5;
                    timeSinceDrift = milliSeconds;
                } else if (gamepad1.right_bumper) {
                    weight += 5;
                    timeSinceDrift = milliSeconds;
                }
            }
        }
    }

    private void turbo() {
        boolean turbo;
        if (gamepad1.a) {
            turbo = true;
            sr = 1;
            slr = 1;
        } else if (gamepad1.b) {
            turbo = false;
            sr = suggestedRange;
            slr = suggestedLinearRange;
        }
    }

    private void driveMode() {
        if (gamepad1.y) {
            currentDate = new Date();
            long milliSeconds = currentDate.getTime();
            switch (driveMode) {
                case POV:
                    if (milliSeconds >= delay + timeSincePress) {
                        timeSincePress = milliSeconds;
                        driveMode = DriveMode.Tank;
                    }
                    break;
                case Tank:
                    //Fallthrough
                default:
                    if (milliSeconds >= delay + timeSincePress) {
                        timeSincePress = milliSeconds;
                        driveMode = DriveMode.POV;
                    }
            }
        }
    }

    //Moves the motors based on the given inputs
    private void drive(double forward, double right, double rotation, double range, double linearRange) {
        total =  Math.abs(forward) + Math.abs(right) + Math.abs(rotation);

        //Boost values based on declared variables
        right	*= strafeBoost;
        rotation *= turnBoost;
        forward  *= forwardBoost;

        //This code is to ensure that no values would go over 1.0 or under -1.0, and to maintain aspect ratio so movement is not wonkified by large values
        if (total > range) {
            forward  /= total;
            right	/= total;
            rotation /= total;
        }

        if (Math.abs(forward) > Math.abs(right) + Math.abs(rotation)) {
            range = linearRange;
        }

        //Calculate required movement based on given inputs
        backLeftPower	= Range.clip(forward + right - rotation, -range, range);
        backRightPower	= Range.clip(forward - right + rotation, -range, range);
        frontLeftPower	= Range.clip(forward - right - rotation, -range, range);
        frontRightPower = Range.clip(forward + right + rotation, -range, range);
    }

    private void driveTank(double left, double right, double leftH, double rightH, double range, double linearRange) {
        total =  Math.abs(left) + Math.abs(right) + Math.abs(leftH) + Math.abs(rightH);

        //Boost values based on declared variables
        double strafe = leftH + rightH;
        strafe	*= strafeBoost;
        left  *= forwardBoost;
        right *= forwardBoost;

        //This code is to ensure that no values would go over 1.0 or under -1.0, and to maintain aspect ratio so movement is not wonkified by large values
        if (total > range) {
            left  /= total;
            right	/= total;
            strafe /= total;
        }

        if (Math.abs(right) == right && Math.abs(left) == Math.abs(left)) {
            range = linearRange;
        }
        if (-Math.abs(right) == right && -Math.abs(left) == Math.abs(left)) {
            range = linearRange;
        }

        //Calculate required movement based on given inputs
        backLeftPower	= Range.clip(left + strafe, -range, range);
        backRightPower	= Range.clip(right - strafe, -range, range);
        frontLeftPower	= Range.clip(left - strafe, -range, range);
        frontRightPower = Range.clip(right + strafe, -range, range);
    }

    private double normalize(double initial, double goal, int initialWeight, double range, double linearRange, int round){
        //Gets large number and small number
        double roundUp = Math.pow(10, round);
        double roundDown = Math.pow(0.1, round/2);

        if (goingForward){
            range = linearRange;
        }

        //Ensures goal is inside the range
        goal = Range.clip(goal, -range, range);

        //Take the average weighted towards the initial
        goal += initial * initialWeight;
        goal /= initialWeight + 1;

        //Round (recommended round = 10) based on the large number
        goal = Math.floor(goal * roundUp)/roundUp;

        //Round to extremities and 0, based on the small number
        if (goal<roundDown&&goal>-roundDown){goal=0;}
        if (goal>range-roundDown){goal=range;}
        if (goal<-range+roundDown){goal=-range;}

        //Ensures goal is inside range
        goal = Range.clip(goal, -range, range);

        return goal;
    }
    private void setPower() {
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
    }
}
