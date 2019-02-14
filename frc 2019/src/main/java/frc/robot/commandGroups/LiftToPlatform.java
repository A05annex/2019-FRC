/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EndGameDrive;
import frc.robot.commands.EndGameLifter;
import frc.robot.commands.MoveArmToTarget;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.subsystems.ArmPositions;


public class LiftToPlatform extends CommandGroup {
  /**
   * Add your docs here.
 * @return 
   */
  public void LiftToPlatform() {

    addSequential(new MoveArmToTarget(ArmPositions.PRE_ENDGAME_LIFT));
    //can MoveArmToTarget() take arguments yet? 
    //need to learn how to get target in here
    addSequential(new SetAndWaitForArmPosition()); 
    addParallel(new MoveArmToTarget(DURING_LIFT));
    addSequential(new EndGameLifter(liftUp()));
    //may have to make separate commands for both lifting up and going down?
    //find way to get this going as a command
    addSequential(new MoveArmToTarget(ENDGAME_LIFT));
    addParallel(new EndGameLifter(goDown()));
    addSequential(new MoveArmToTarget(ENDGAME_LAND));
    addParallel(new EndGameDrive());
    addSequential(new MoveArmToTarget(ENDGAME_PARK));
    addSequential(new MoveArmToTarget(POST_ENDGAME_PARK));
    

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
