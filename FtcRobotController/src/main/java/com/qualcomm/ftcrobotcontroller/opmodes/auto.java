package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Ethan on 1/12/16.
 */
public class auto extends LinearOpMode {
    int wheelPosition;

    public void declare(){
        hardware.leftWheel = hardwareMap.dcMotor.get("l");
        hardware.rightWheel = hardwareMap.dcMotor.get("r");
        hardware.rightWheel.setDirection(DcMotor.Direction.REVERSE);
        hardware.winch1 = hardwareMap.dcMotor.get("w1");
        hardware.winch1.setDirection(DcMotor.Direction.REVERSE);
        hardware.winch2 = hardwareMap.dcMotor.get("w2");
        hardware.angler = hardwareMap.dcMotor.get("a");
        hardware.climberLeft = hardwareMap.servo.get("cl");
        hardware.climberRight = hardwareMap.servo.get("cr");
        hardware.stopper = hardwareMap.servo.get("s");
        hardware.climber = hardwareMap.servo.get("c");
    }

    public void driveTo(int ticks, double powerLeft, double powerRight) throws InterruptedException {
        while(wheelPosition != ticks) {
            wheelPosition = hardware.rightWheel.getCurrentPosition();
            telemetry.addData("1", "wheel at: " + wheelPosition + " ticks.");
            if(wheelPosition < ticks) {
                telemetry.addData("2", "tick goal: " + (ticks));
                telemetry.addData("3", "near");
                hardware.leftWheel.setPower(powerLeft);
                hardware.rightWheel.setPower(powerRight);
            }
            else if (wheelPosition > ticks) {
                telemetry.addData("2", "tick goal: " + (ticks));
                telemetry.addData("3", "far");
                hardware.leftWheel.setPower(-powerLeft);
                hardware.rightWheel.setPower(-powerRight);
            } else {
                break;
            }
        }
        stopDrive();
        while (hardware.rightWheel.getCurrentPosition() != 0){
            hardware.rightWheel.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }//gives hardware the time to reset
        hardware.rightWheel.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitOneFullHardwareCycle();
    }
    public void driveAng(int angle, double leftPower, double rightPower) throws InterruptedException {
        double mod = 34;
        driveTo((int) (angle * mod), leftPower, rightPower);
    }
    public void driveDist(int inches, double leftPower, double rightPower) throws InterruptedException {
        double mod = 80;
        driveTo((int)(inches*mod), leftPower, rightPower);
    }
    public void stopDrive(){
        hardware.rightWheel.setPower(0);
        hardware.leftWheel.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        declare();
        hardware.rightWheel.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitOneFullHardwareCycle();
        waitForNextHardwareCycle();
        hardware.rightWheel.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitForStart();
        driveDist(18, .25, .25);
//      driveTo(1500, .25, .25); //backwards towards the mountain
        stopDrive();
        telemetry.addData("5", "distance done");

        //driveAng(90, -.2, .2);
//      driveTo(3000, -.1, .1);  //turns 90 left

        stopDrive();
        telemetry.addData("6", "turn done");
    }
}
