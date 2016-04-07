package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.phoenix.AutoPhoenixNav;
import com.qualcomm.ftcrobotcontroller.opmodes.phoenix.AutoPhoenixNavAsync;
import com.qualcomm.ftcrobotcontroller.opmodes.phresh.TeleopSkunk;
import com.qualcomm.ftcrobotcontroller.opmodes.purplebot.AutoPurplebot;
import com.qualcomm.ftcrobotcontroller.opmodes.vision.BasicVisionSample;
import com.qualcomm.ftcrobotcontroller.opmodes.vision.LinearVisionSample;
import com.qualcomm.ftcrobotcontroller.opmodes.vision.ManualVisionSample;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {
    public static String robot = "4290";

    /**
     * The Op Mode Manager will call this method when it wants a list of all
     * available op modes. Add your op mode to the list to enable it.
     *
     * @param manager op mode manager
     */

    public void register(OpModeManager manager) {
        manager.register("* Teleop Skunk (v3)", TeleopSkunk.class);
        manager.register("* Auto PHoenix (v2)", AutoPhoenixNav.class);
        manager.register("* Auto PHoenix Async (v2)", AutoPhoenixNavAsync.class);
        manager.register("Auto Purplebot", AutoPurplebot.class);
        manager.register("Basic Vision Test", BasicVisionSample.class);
        manager.register("Linear Vision Test", LinearVisionSample.class);
        manager.register("Manual Vision Test", ManualVisionSample.class);
    }
}
