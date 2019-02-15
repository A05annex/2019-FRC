/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DepositDrive;
import frc.robot.commands.MoveServo;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.commands.SetArmTarget;
import frc.robot.subsystems.ArmPositions;

public class AttatchHatchPanelHigh extends CommandGroup {
  
  public AttatchHatchPanelHigh() {

    addSequential(new SetArmTarget(ArmPositions.HIGH_HATCH));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.HIGH_HATCH));
    //addSequential(new Centering());
    //We need to find a way to center the robot
    addSequential(new DepositDrive());
    addSequential(new MoveServo(1));

  }
}
