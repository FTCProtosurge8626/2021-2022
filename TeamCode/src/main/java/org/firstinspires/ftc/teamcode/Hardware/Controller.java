package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {

    private static Gamepad Gamepad1;
    private static Gamepad Gamepad2;
    public static Gamepad[] Gamepads;

    //Instantiates arrays for the Gamepad inputs
    public static double[][] sticks = new double[2][2];
    public double[] triggers = new double[2];

    public boolean[] buttons = new boolean[4];
    public boolean[] hatSticks = new boolean[4];
    public boolean[] bumpers = new boolean[2];
    public boolean[] menus = new boolean[2];

    public static double stickDeadband = 0.05;

    public static double[] initSticks() {
        for(int i = 0, j = 0; i < sticks.length/2; i++, j++) {
            if(sticks[i - j * 2][j] > stickDeadband) {
                return new double[] {sticks[0][0]/stickDeadband, sticks[0][1]/stickDeadband, sticks[1][0]/stickDeadband, sticks[1][1]/stickDeadband};
            }
        }
        return new double[] {0,0,0,0};
    }

    /*
        //Adds both Gamepad inputs
        private static <T> Gamepad controls(LinearOpMode opmode){
            gamepad1 = GamePad1;
            gamepad2 = opmode.gamepad2;
            return gamepad1.input + gamepad2.input;
        }
    */

    //Sets the Gamepad inputs
    public Controller (LinearOpMode opmode){
        Gamepad1 = opmode.gamepad1;
        Gamepad2 = opmode.gamepad2;
        Gamepads = new Gamepad[] {Gamepad1, Gamepad2};

        sticks[0][0] = Gamepad1.left_stick_x;
        sticks[0][1] = Gamepad1.left_stick_y;
        sticks[1][0] = Gamepad1.right_stick_x;
        sticks[1][1] = Gamepad1.right_stick_y;

        triggers[0] = Gamepad1.right_trigger;
        triggers[1] = Gamepad1.left_trigger;

        buttons[0] = Gamepad1.a;
        buttons[1] = Gamepad1.b;
        buttons[2] = Gamepad1.x;
        buttons[3] = Gamepad1.y;

        hatSticks[0] = Gamepad1.dpad_right;
        hatSticks[1] = Gamepad1.dpad_left;
        hatSticks[2] = Gamepad1.dpad_up;
        hatSticks[3] = Gamepad1.dpad_down;

        bumpers[0] = Gamepad1.right_bumper;
        bumpers[1] = Gamepad1.left_bumper;

        menus[0] = Gamepad1.start;
        menus[1] = Gamepad1.guide;
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
/*
    //boolean switch;
    public static <T extends Number> T pressed(T hold) {
        return (T) Double.valueOf(hold);
        return hold;
    }*/


    //Gamepad gamepad1, Gamepad gamepad2
    public static void keyBind(LinearOpMode opmode, Controller Back) {
        //opmode.telemetry.clear();
        if(Back.press(Gamepad1.back)) {
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
