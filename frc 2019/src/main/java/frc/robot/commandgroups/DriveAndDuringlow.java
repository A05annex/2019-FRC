/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class DriveAndDuringlow extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DriveAndDuringlow() {
    
    //Goes to DURING_LOW_LIFT position. In a command group becuase the robot formerly drove at the same time. 
    addSequential(new InterpolateAndCheck(ArmPositions.DURING_LOW_LIFT));
  }
}
