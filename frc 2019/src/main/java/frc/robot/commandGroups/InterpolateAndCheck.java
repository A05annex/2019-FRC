/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.ArmInterpolateToTarget;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.subsystems.ArmPositions;

public class InterpolateAndCheck extends CommandGroup {
  /**
   * Add your docs here.
   */
  public InterpolateAndCheck(ArmPositions target) {
    requires((Subsystem)Robot.armDriveTrain);

    addSequential(new ArmInterpolateToTarget(target));
    addSequential(new SetAndWaitForArmPosition(target));
  }
}
