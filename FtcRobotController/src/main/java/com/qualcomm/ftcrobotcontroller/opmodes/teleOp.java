package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.ButtonState;
import com.lasarobotics.library.controller.Controller;
import com.lasarobotics.library.drive.Tank;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * created by Ethan on 1/9/16.
 */

public class teleOp extends OpMode {
    Controller driver, operator;
    float winchPower;
    final double leftServoTop = .97, leftServoBottom = .25, rightServoTop = .05, rightServoBottom = .75, climberTop = .35, climberBottom = .95, stopperOn = .4, stopperOff = .9;
    double leftPosition = leftServoTop, rightPosition = rightServoTop, climberPosition = climberTop, anglerPower, stopperPosition = stopperOff;

    public void telemetry(){
        if(leftPosition == leftServoTop){
            telemetry.addData("1", "left is off");
        } else if(leftPosition == leftServoBottom){
            telemetry.addData("1", "left is on");
        } else {
            telemetry.addData("1", "no left servo");
        }

        if(rightPosition == rightServoTop){
            telemetry.addData("2", "right is off");
        } else if(rightPosition == rightServoBottom){
            telemetry.addData("2", "right is on");
        } else {
            telemetry.addData("2", "no right servo");
        }

        if(climberPosition == climberBottom){
            telemetry.addData("3", "climber is off");
        } else if(climberPosition == climberTop){
            telemetry.addData("3", "climber is on");
        } else {
            telemetry.addData("3", "no climber servo");
        }
        if(stopperPosition == stopperOn){
            telemetry.addData("4", "stopper is on");
        } else if(stopperPosition == stopperOff){
            telemetry.addData("4", "stopper is off");
        } else {
            telemetry.addData("4", "stopper at: " + stopperPosition);
        }
        telemetry.addData("5", " ENCODERS: ");
        telemetry.addData("6", "left motor enc: " + hardware.leftWheel.getCurrentPosition());
        telemetry.addData("7", "right motor enc: " + hardware.rightWheel.getCurrentPosition());
        telemetry.addData("8", "winch1 enc: " + hardware.winch1.getCurrentPosition());
        telemetry.addData("9", "winch2 enc: " + hardware.winch2.getCurrentPosition());
        telemetry.addData("10", "angler enc: " + hardware.angler.getCurrentPosition());
        telemetry.addData("11", " POWERS: ");
        telemetry.addData("12", "winch pow: " + winchPower);
        telemetry.addData("13", "angler pow:" + anglerPower);

    }

    @Override
    public void init() {
        hardware.leftWheel = hardwareMap.dcMotor.get("l");
        hardware.leftWheel.setDirection(DcMotor.Direction.REVERSE);
        hardware.rightWheel = hardwareMap.dcMotor.get("r");
        hardware.winch1 = hardwareMap.dcMotor.get("w1");
        hardware.winch1.setDirection(DcMotor.Direction.REVERSE);
        hardware.winch2 = hardwareMap.dcMotor.get("w2");
        hardware.angler = hardwareMap.dcMotor.get("a");
        hardware.angler.setDirection(DcMotor.Direction.REVERSE);
        hardware.climberLeft = hardwareMap.servo.get("cl");
        hardware.climberRight = hardwareMap.servo.get("cr");
        hardware.stopper = hardwareMap.servo.get("s");
        hardware.climber = hardwareMap.servo.get("c");
        driver = new Controller(gamepad1);
        operator = new Controller(gamepad2);
    }

    @Override
    public void loop() {
        driver.update(gamepad1);
        operator.update(gamepad2);

        if(operator.y == ButtonState.PRESSED && climberPosition != climberTop) {
            climberPosition=climberTop;
        } else if(operator.y == ButtonState.PRESSED && climberPosition != climberBottom) {
            climberPosition=climberBottom;
        }

        if(operator.x  == ButtonState.PRESSED && leftPosition != leftServoTop) {
            leftPosition=leftServoTop;
        } else if(operator.x == ButtonState.PRESSED && leftPosition != leftServoBottom) {
            leftPosition=leftServoBottom;
        }
        if(operator.b == ButtonState.PRESSED && rightPosition != rightServoTop) {
            rightPosition=rightServoTop;
        } else if(operator.b == ButtonState.PRESSED && rightPosition != rightServoBottom) {
            rightPosition=rightServoBottom;
        }

        if(Math.abs(operator.right_stick_y) > .05) {
            winchPower = operator.right_stick_y;
        } else {
            winchPower = 0;
        }
        if(Math.abs(operator.left_stick_y) > .05) {
            anglerPower = operator.left_stick_y;
        } else {
            anglerPower = 0;
        }

        if(operator.a == ButtonState.PRESSED && stopperPosition != stopperOn) {
            stopperPosition = stopperOn;
        } else if(operator.a == ButtonState.PRESSED && stopperPosition != stopperOff) {
            stopperPosition = stopperOff;
        }

        hardware.stopper.setPosition(stopperPosition);
        hardware.climber.setPosition(climberPosition);
        hardware.climberLeft.setPosition(leftPosition);
        hardware.climberRight.setPosition(rightPosition);

        hardware.winch1.setPower(winchPower);
        hardware.winch2.setPower(winchPower);
        hardware.angler.setPower(anglerPower);
        Tank.Motor2(hardware.leftWheel, hardware.rightWheel, driver.left_stick_y, driver.right_stick_y);


        telemetry();
    }
}