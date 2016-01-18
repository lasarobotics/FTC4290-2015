package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Ethan on 1/12/16.
 */
public class hardware extends OpMode{

    public static DcMotor leftWheel, rightWheel, winch1, winch2, angler;
    public static Servo climberLeft, climberRight, stopper, climber;
    public static double leftServoTop = .97, leftServoBottom = .25, rightServoTop = .05, rightServoBottom = .75, climberTop = .95, climberBottom = .35, stopperOn = .4, stopperOff = .9;


    public void declare(){
        leftWheel = hardwareMap.dcMotor.get("l");
        leftWheel.setDirection(DcMotor.Direction.REVERSE);
        rightWheel = hardwareMap.dcMotor.get("r");
        winch1 = hardwareMap.dcMotor.get("w1");
        winch1.setDirection(DcMotor.Direction.REVERSE);
        winch2 = hardwareMap.dcMotor.get("w2");
        angler = hardwareMap.dcMotor.get("a");
        climberLeft = hardwareMap.servo.get("cl");
        climberRight = hardwareMap.servo.get("cr");
        stopper = hardwareMap.servo.get("s");
    }


    @Override
    public void init() {
        declare();
    }

    @Override
    public void loop() {

    }
}
