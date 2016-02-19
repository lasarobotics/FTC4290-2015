package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.util.MathUtil;
import com.qualcomm.ftcrobotcontroller.Auto;
import com.qualcomm.ftcrobotcontroller.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class AutoRed extends LinearOpMode {
    private static final int TOLERANCE_DEGREES = 2;

    Hardware config;

    @Override
    public void runOpMode() throws InterruptedException {
        config = new Hardware(hardwareMap);

        waitForStart();
        Auto.driveTo(config, telemetry, 8000, 1, 1);
        turnToDegNavX(45, .25);
        Auto.driveTo(config, telemetry, 1000, .5, .5);
        waitOneFullHardwareCycle();
        waitForNextHardwareCycle();
        config.climber.setPosition(Hardware.CLIMBER_TOP);
        sleep(3000);
        config.climber.setPosition(Hardware.CLIMBER_BOTTOM);

        config.navx.close();
    }

    public  float convertDegNavX(float deg) {
        if (deg < 0)
            deg = 360 - Math.abs(deg);
        return deg;
    }

    public void turnToDegNavX(int deg, double power) throws InterruptedException {
        config.navx.zeroYaw();
        waitOneFullHardwareCycle();
        config.leftWheel.setPower(-power);
        config.rightWheel.setPower(power);

        float yaw;

        boolean arrived = false;

        while(!arrived) {
            yaw = convertDegNavX(config.navx.getYaw());
            telemetry.addData("Yaw", yaw);
            telemetry.addData("Target Yaw", deg);
            if (MathUtil.inBounds(deg - TOLERANCE_DEGREES, deg + TOLERANCE_DEGREES, yaw))
                arrived = true;
            waitOneFullHardwareCycle();
        }

        config.leftWheel.setPower(0);
        config.rightWheel.setPower(0);
        Auto.resetEncoder(config.leftWheel);
        Auto.resetEncoder(config.rightWheel);
    }
}
