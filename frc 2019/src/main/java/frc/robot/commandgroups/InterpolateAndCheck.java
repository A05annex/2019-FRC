/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ArmInterpolateToTarget;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.subsystems.ArmPositions;

public class InterpolateAndCheck extends CommandGroup {
  /**
   * Add your docs here.
   */
  public InterpolateAndCheck(ArmPositions target) {

    //Makes arm go to target nice n smooth.
    //Ends command/command group when the robot is at the target position.
    //Our main commandgroup for moving the arm. 
    
    addSequential(new ArmInterpolateToTarget(target));
    addSequential(new SetAndWaitForArmPosition(target));
  }
}
