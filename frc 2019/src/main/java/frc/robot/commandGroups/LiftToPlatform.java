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
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.subsystems.ArmPositions;


public class LiftToPlatform extends CommandGroup {
  /**
   * Add your docs here.
 * @return 
   */
  public LiftToPlatform() {

    //one button command for lifting robot to third platform

    addParallel(new EndGameDrive());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.PRE_ENDGAME_LIFT));
    addParallel(new SetAndWaitForArmPosition(ArmPositions.DURING_LIFT));
    //still need to make DURING_LIFT
    addSequential(new EndGameLifter());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LIFT));
    addParallel(new EndGameDowner());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LAND));
    addParallel(new EndGameDrive());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_PARK));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.POST_ENDGAME_PARK));
    


  }
}
