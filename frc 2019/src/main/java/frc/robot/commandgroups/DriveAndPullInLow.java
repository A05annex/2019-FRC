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

public class DriveAndPullInLow extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DriveAndPullInLow() {

    //Part of LiftToLowPlatform.
    //Robot drives forward as the arm pulls it in. 
    
    addParallel(new TimedDrive(1.5, 0.4));
    addSequential(new InterpolateAndCheck(ArmPositions.PULL_IN_LOW));
  }
}
