package org.firstinspires.ftc.teamcode.Marcus.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Marcus.Hardware.Class.Motor_Hardware_1;
import org.firstinspires.ftc.teamcode.Marcus.Hardware.Main_Hardware;

@Autonomous(name = "Marcus: Duck_Autonomous", group = "Duck")
public class Autonomous_Duck extends Main_Hardware {

    @Override
    public void runOpMode() {
        initAutonomous(Main_Hardware.RunMode.TeleOp, hardwareMap, telemetry);
        waitForStart();

        strafe(-.5,500);
        move(-.5,500);
        duck(.85,3000);
        turn(0.5,500);
        move(1,3000);
    }

    private void move(double power, int sleep) {
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors,-power);
        sleep(sleep);
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors,0);
        sleep(500);
    }

    private void strafe(double power, int sleep) {
        double[] powers = new double[4];

        powers[0] = power;
        powers[1] = -power;
        powers[2] = -power;
        powers[3] = power;
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors,power);
        sleep(sleep);
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors,0);
        sleep(500);
    }

    private void turn(double power, int sleep) {
        double[] powers = new double[4];

        powers[0] = -power;
        powers[1] = power;
        powers[2] = -power;
        powers[3] = power;
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors,power);
        sleep(sleep);
        Motor_Hardware_1.setPowers(Motor_Hardware_1.motors,0);
        sleep(500);
    }

    private void duck(double power, int sleep) {
        double[] powers = new double[4];

        powers[0] = -power;
        powers[1] = power;
        powers[2] = -power;
        powers[3] = power;
        Motor_Hardware_1.setPowers(Motor_Hardware_1.carousel,power);
        sleep(sleep);
        Motor_Hardware_1.setPowers(Motor_Hardware_1.carousel,0);
        sleep(500);
    }
}
