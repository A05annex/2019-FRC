/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LiftingPower;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class LiftToLowPlatform extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LiftToLowPlatform() {
    
    addSequential(new LiftingPower(true));
    addSequential(new InterpolateAndCheck(ArmPositions.START_LOW_LIFT));
    addParallel(new TimedDrive(1.0, 0.3));
    addSequential(new InterpolateAndCheck(ArmPositions.DURING_LOW_LIFT));
    addParallel(new TimedDrive(1.0, 0.3));
    addSequential(new InterpolateAndCheck(ArmPositions.PULL_IN_LOW));
    addSequential(new LiftingPower(false));
    addSequential(new InterpolateAndCheck(ArmPositions.LIFT_ARM));
    addParallel(new TimedDrive(1.0, 0.5));
    addSequential(new InterpolateAndCheck(ArmPositions.HOME));
    addSequential(new TimedDrive(1.5, 0.5));


  }
}
