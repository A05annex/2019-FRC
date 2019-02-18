/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EndGameDowner;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.subsystems.ArmPositions;

public class DownerAndLand extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DownerAndLand() {

    addParallel(new EndGameDowner());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LAND));
    
  }
}
