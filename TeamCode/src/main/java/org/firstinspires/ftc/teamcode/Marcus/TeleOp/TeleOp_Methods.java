package org.firstinspires.ftc.teamcode.Marcus.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Marcus.Framework.Converter;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.IMU_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.Motor_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Main_Hardware;

public class TeleOp_Methods extends Main_Hardware {

    public static boolean inOrient = false;

    private static double powerGain = 1;

    public static void changePower(boolean increase, boolean decrease){
        if(increase && powerGain < 1) {
            powerGain += 0.1;
        } else if(decrease && powerGain > 0) {
            powerGain -= 0.1;
        }
    }

    public static void move(double[] lStick, double[] rStick, boolean[] hatSticks) {
        double[] powers = new double[4];

        lStick[0] *= powerGain;
        lStick[1] *= powerGain;
        rStick[0] *= powerGain;
        rStick[1] *= powerGain;

        double totalPower = Math.abs(lStick[0]) + Math.abs(lStick[1]) + Math.abs(rStick[0]) + Math.abs(rStick[1]);
        int range = 1;

        if(totalPower > 1) {
            lStick[0] /= totalPower;
            lStick[1] /= totalPower;
            rStick[0] /= totalPower;
            rStick[1] /= totalPower;
        }

        if(rStick[1] != 0) {
            inOrient = false;
        }

        orient(hatSticks);

        if(inOrient) {
            if(!IMU_Hardware_1.inRange()) {
                rStick[1] = IMU_Hardware_1.error();
            }
        }

        switch(driveMode) {
            case POV:
                powers[0] = Range.clip(lStick[0] - lStick[1] + rStick[1], -range, range);
                powers[1] = Range.clip(lStick[0] + lStick[1] - rStick[1], -range, range);
                powers[2] = Range.clip(lStick[0] + lStick[1] + rStick[1], -range, range);
                powers[3] = Range.clip(lStick[0] - lStick[1] - rStick[1], -range, range);
                break;
            case Tank:
                powers[0] = rStick[0];
                powers[1] = lStick[0];
                powers[2] = rStick[0];
                powers[3] = lStick[0];
                break;
            case Arcade:
                powers[0] = lStick[0] + lStick[1];
                powers[1] = lStick[0] - lStick[1];
                powers[2] = lStick[0] + lStick[1];
                powers[3] = lStick[0] - lStick[1];
                break;
            default:
                break;
        }
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors, powers);
    }

    public static void orient(boolean[] direction){
        if(direction[0]) {
            inOrient = true;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.NORTH;
        } else if(direction[1]) {
            inOrient = true;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.SOUTH;
        } else if(direction[2]) {
            inOrient = true;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.EAST;
        } else if(direction[3]) {
            inOrient = true;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.WEST;
        }
        IMU_Hardware_1.orientCompass();
    }

    public static void intake(boolean[] intake, double[] horizontal, double vertical){
        double[] powers = new double[3];

        powers[0] = Converter.toInt(intake[1]) + -Converter.toInt(intake[0]);
        powers[1] = Range.clip(-vertical/2 + 0.25,0,1);
        powers[2] = horizontal[0] - horizontal[1];

        /*
        if(horizontal[0] > 0) powers[1] = 1;
        else if(horizontal[1] > 0) powers[1] = -1;
        else powers[1] = 0;

        if(vertical > 0) powers[2] = 1;
        else if(vertical < 0) powers[2] = -1;
        else powers[2] = 0;
        */
        Motor_Hardware_1.setPowers(Motor_Hardware_1.intake, powers);
    }

    public static void moveHeading(double forward, double horizontal, double rotational, boolean haltHeading,  boolean lockedHaltHeading) {

        double error = IMU_Hardware_1.error();

        while (error > IMU_Hardware_1.range || error < -IMU_Hardware_1.range) {
            if(rotational != 0) {
                IMU_Hardware_1.target = IMU_Hardware_1.heading();
                break;
            }
            //move(forward, horizontal, error);
        }
        //double error = Convert.normalize(IMU_Hardware_1.getHeadingError(IMU_Hardware_1.getTargetHeading()), IMU_Hardware_1.getHeadingMin(), IMU_Hardware_1.getHeadingMax());

        //move(forward, horizontal, error);
        //move(forward, horizontal, rotational);

        if(haltHeading /*|| Controller_1.press(lockedHaltHeading)*/){
            //move(forward, horizontal, rotational);
        }
    }

    public static void moveHeading(double forward, double horizontal, double rotational, LinearOpMode linearOpMode) {
        double error = IMU_Hardware_1.error();

        while (error > IMU_Hardware_1.range || error < -IMU_Hardware_1.range && !linearOpMode.isStopRequested()) {
            if(rotational != 0) {
                IMU_Hardware_1.target = IMU_Hardware_1.heading();
                break;
            }
            //move(forward, horizontal, error);
        }
        //move(forward, horizontal, error);
    }

}