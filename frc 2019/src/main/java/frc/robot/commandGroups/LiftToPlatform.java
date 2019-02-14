/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftToPlatform extends CommandGroup {
  /**
   * Add your docs here.
 * @return 
   */
  public void LiftToplatform() {

    addSequential(new SetArmTarget(PRE_ENDGAME_LIFT));
    addSequential(new SetAndWaitForArmPosition()); 
    addParallel(new SetArmTarget(DURING_LIFT));
    addSequential(new Lift(up()));
    addSequential(new SetArmTarget(ENDGAME_LIFT));
    addParallel(new Lift(down()));
    addSequential(new SetArmTarget(ENDGAME_LAND));
    addParallel(new EndGameDrive());
    addSequential(new SetArmTarget(ENDGAME_PARK));
    addSequential(new SetArmTarget(POST_ENDGAME_PARK));
    

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
