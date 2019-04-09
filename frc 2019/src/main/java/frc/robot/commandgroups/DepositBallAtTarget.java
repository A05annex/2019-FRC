/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DepositCargoWithLimit;
import frc.robot.subsystems.ArmPositions;


public class DepositBallAtTarget extends CommandGroup {
  
  ArmPositions target;

  public DepositBallAtTarget(ArmPositions target) {

    //Goes to target position, runs ejecting cargo wheels until ball has left, then returns to home position.

    addSequential(new InterpolateAndCheck(target));
    addSequential(new DepositCargoWithLimit());
    addSequential(new InterpolateAndCheck(ArmPositions.HOME)); 
  }
}
