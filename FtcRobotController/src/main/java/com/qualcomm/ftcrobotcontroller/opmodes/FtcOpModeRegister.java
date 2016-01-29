package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */

  public void register(OpModeManager manager) {
      manager.register("TeleOp", teleOp.class);
      manager.register("Autonomous Red", redMountain.class);
      manager.register("Autonomous Blue", blueMountain.class);
      manager.register("Test", driveForwards.class);

  }
}
