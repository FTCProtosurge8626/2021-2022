package org.firstinspires.ftc.teamcode.Marcus.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Marcus.Framework.Converter;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.IMU_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.Motor_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Main_Hardware;

public class TeleOp_Methods extends Main_Hardware {

    public static boolean inTurn = false;

    private static double powerGain = 1;

    public static void changePower(boolean increase, boolean decrease){
        if(increase && powerGain < 1) {
            powerGain += 0.1;
        } else if(decrease && powerGain > 0) {
            powerGain -= 0.1;
        }
    }

    public static void move(double forward, double horizontal, double rotational, boolean[] hatSticks) {
        double[] powers = new double[4];

        forward *= powerGain;
        horizontal *= powerGain;
        rotational *= powerGain;

        double totalPower = Math.abs(forward) + Math.abs(horizontal) + Math.abs(rotational);
        int range = 1;

        if(totalPower > 1) {
            forward /= totalPower;
            horizontal /= totalPower;
            rotational /= totalPower;
        }
/*
        if(rotational != 0) {
            inTurn = true;
        } else {
            inTurn = false;
        }

        if(!inTurn) {
            moveCompass(IMU_Hardware_1.target(), hatSticks);
            if(!IMU_Hardware_1.inRange()) {
                rotational = IMU_Hardware_1.error();
            }
        }
*/
        switch(driveMode) {
            case POV:
                powers[0] = Range.clip(forward - horizontal - rotational, -range, range);
                powers[1] = Range.clip(forward + horizontal - rotational, -range, range);
                powers[2] = Range.clip((-forward) - horizontal - rotational, -range, range);
                powers[3] = Range.clip((-forward) + horizontal - rotational, -range, range);
                break;
            case Tank:
                powers[0] = Range.clip(forward, -range, range);
                powers[1] = Range.clip(horizontal, -range, range);
                powers[2] = Range.clip(forward, -range, range);
                powers[3] = Range.clip(horizontal, -range, range);
                break;
            case Arcade:
                powers[0] = Range.clip(forward - horizontal, -range, range);
                powers[1] = Range.clip(forward - horizontal, -range, range);
                powers[2] = Range.clip(forward + horizontal, -range, range);
                powers[3] = Range.clip(forward + horizontal, -range, range);
                break;
            default:
                break;
        }
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors, powers);
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

    public static void moveCompass(double dir, boolean[] direction) {
        pointCompass(direction);
        IMU_Hardware_1.target = dir;
    }

    public static void pointCompass(boolean[] direction){
        if(direction[0]) {
            inTurn = false;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.NORTH;
        } else if(direction[1]) {
            inTurn = false;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.SOUTH;
        } else if(direction[2]) {
            inTurn = false;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.EAST;
        } else if(direction[3]) {
            inTurn = false;
            IMU_Hardware_1.compass = IMU_Hardware_1.Compass.WEST;
        }
        IMU_Hardware_1.orientCompass();
    }

    public static void intake(boolean[] intake, double[] horizontal, double vertical){
        double[] powers = new double[3];

        powers[0] = Converter.toInt(intake[1]) + -Converter.toInt(intake[0]);
        powers[1] = -vertical;
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
}