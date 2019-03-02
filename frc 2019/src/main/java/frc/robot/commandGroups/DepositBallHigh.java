/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.ArmPositions;


public class DepositBallHigh extends CommandGroup {

  public DepositBallHigh() {

    //goes to high position, runs ejecting cargo wheels until ball has left, then returns home
    //can make for other cargo deposits as well
    //wil lhook up to buttons if deemed useful

    addSequential(new InterpolateAndCheck(ArmPositions.HIGH_CARGO));
    //addSequential(new DepositBallHigh());
    addSequential(new InterpolateAndCheck(ArmPositions.HOME));
  }
}
