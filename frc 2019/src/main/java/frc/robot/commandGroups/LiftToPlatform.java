/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EndGameDowner;
import frc.robot.commands.EndGameDrive;
import frc.robot.commands.EndGameLifter;
import frc.robot.commands.MoveArmToTarget;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.commands.SetArmTarget;
import frc.robot.subsystems.ArmPositions;


public class LiftToPlatform extends CommandGroup {
  /**
   * Add your docs here.
 * @return 
   */
  public void LiftToPlatform() {

    addSequential(new SetArmTarget(ArmPositions.PRE_ENDGAME_LIFT));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.PRE_ENDGAME_LIFT));
    addParallel(new SetArmTarget(ArmPositions.DURING_LIFT));
    //still need to make DURING_LIFT
    addSequential(new EndGameLifter());
    addSequential(new SetArmTarget(ArmPositions.ENDGAME_LIFT));
    addParallel(new EndGameDowner());
    addSequential(new SetArmTarget(ArmPositions.ENDGAME_LAND));
    addParallel(new EndGameDrive());
    addSequential(new SetArmTarget(ArmPositions.ENDGAME_PARK));
    addSequential(new SetArmTarget(ArmPositions.POST_ENDGAME_PARK));
    

    
    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

  }
}
