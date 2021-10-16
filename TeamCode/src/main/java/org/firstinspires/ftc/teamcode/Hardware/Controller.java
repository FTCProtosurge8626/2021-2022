package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {
    //Instantiates arrays for the Gamepad inputs
    double[][] stick = new double[2][2];
    double[] trigger = new double[2];

    boolean[] button = new boolean[4];
    boolean[] hatStick = new boolean[4];
    boolean[] bumper = new boolean[2];
    boolean[] menu = new boolean[2];

    private static Gamepad gamepad1;
    private static Gamepad gamepad2;

    /*
        //Adds both Gamepad inputs
        private static <T> Gamepad controls(LinearOpMode opmode){
            gamepad1 = opmode.gamepad1;
            gamepad2 = opmode.gamepad2;
            return gamepad1.input + gamepad2.input;
        }
    */

    //Sets the Gamepad inputs
    public Controller (LinearOpMode opmode){
        stick[0][0] = opmode.gamepad1.left_stick_x;
        stick[0][1] = opmode.gamepad1.left_stick_y;
        stick[1][0] = opmode.gamepad1.right_stick_x;
        stick[1][1] = opmode.gamepad1.right_stick_y;

        trigger[0] = opmode.gamepad1.right_trigger;
        trigger[1] = opmode.gamepad1.left_trigger;

        button[0] = opmode.gamepad1.a;
        button[1] = opmode.gamepad1.b;
        button[2] = opmode.gamepad1.x;
        button[3] = opmode.gamepad1.y;

        hatStick[0] = opmode.gamepad1.dpad_right;
        hatStick[1] = opmode.gamepad1.dpad_left;
        hatStick[2] = opmode.gamepad1.dpad_up;
        hatStick[3] = opmode.gamepad1.dpad_down;

        bumper[0] = opmode.gamepad1.right_bumper;
        bumper[1] = opmode.gamepad1.left_bumper;

        menu[0] = opmode.gamepad1.start;
        menu[1] = opmode.gamepad1.guide;
    }

    boolean pressSwitch;
    boolean loop;

    boolean press(boolean press) {
        if(press && loop) {
            if(pressSwitch) {
                loop = false;
                pressSwitch = false;
            } else {
                loop = false;
                pressSwitch = true;
            }
        } else if(!press){
            loop = true;
        }
        this.pressSwitch = pressSwitch;
        this.loop = loop;
        return pressSwitch;
    }
    public static <T> T hold(T hold) {
        return hold;
    }


    //Gamepad gamepad1, Gamepad gamepad2
    static void keyBind(LinearOpMode opmode, Controller Back) {
        //opmode.telemetry.clear();
        if(Back.press(opmode.gamepad1.back)) {
            /*
            opmode.telemetry.addData("Move Forward: ", LeftStickY);
            opmode.telemetry.addData("Move Sideways: ", LeftStickX);
            opmode.telemetry.addData("Move Turn: ", RightStickX);
            */
            //telemetry.update();
        }
        opmode.telemetry.update();
    }
}
