//@Note It is recommended that you read Basic_Hardware class before viewing this class

package org.firstinspires.ftc.teamcode.FTC_Learning.Lessons;

//This is the import for the @Autonomous that is used above the class declaration
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

//This adds this program to the phone so that it can be executed onto the Control Hub
@Autonomous(name= "RunMode: Basic_RunMode_Autonomous", group = "Autonomous") //@Description this sets the file name to "RunMode: Basic_RunMode_Autonomous" under the category "Autonomous"

//We extend org.firstinspires.ftc.teamcode.FTCLearning.Basic_Hardware to be able to reference it's methods
public class Basic_RunMode_Autonomous extends Basic_Hardware {
	
	//This overrides the runOpMode method from the LinearOpMode class and when the program is executed
	@Override
	public void runOpMode() {
		//We call the initialize from the org.firstinspires.ftc.teamcode.FTCLearning.Basic_Hardware class
		initialize();

		//This will wait for the run button to be pressed and continue compilation
		waitForStart();	//@Description This is called from LinearOpMode
    
		//Input the move code here. Note that the controllers will not be able to send inputs to the robot, however you may use sensors
    		MoveArms(1, 1, 0.5, 0, 1000);
   		MoveClamps(1, 1);
    		MoveClaws(1, 1);
	}
	
	
	//Moves the arms based on the given inputs
	private void MoveArms(double leftForward, double leftHoricontal,double rightForward, double righHorizontal, int waitTime) {
		ArmLeft.setPower(leftForward + leftHoricontal);
		ArmLeft.setPower(rightForward + righHorizontal);
    		sleep(waitTime);
	}
	
	//Moves the clamps based on the given inputs
	private void MoveClamps(double rightPosition, double leftPosition) {
			ClampRight.setPosition(rightPosition);
			ClampLeft.setPosition(leftPosition);
	}
	
	//Moves the claws based on the given inputs
	private void MoveClaws(double leftPower, double rightPower) {
		ClawRight.setPower(leftPower);
		ClawLeft.setPower(rightPower);
    		sleep(500);
	}
}
