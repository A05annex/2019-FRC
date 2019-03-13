/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LiftingPower;
import frc.robot.subsystems.ArmPositions;

public class LiftPowerAndStart extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LiftPowerAndStart() {
    addSequential(new LiftingPower(true));
    addSequential(new InterpolateAndCheck(ArmPositions.START_LOW_LIFT));

  }
}
