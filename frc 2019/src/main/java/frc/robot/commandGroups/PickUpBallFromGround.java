/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CollectCargoWithLimit;
import frc.robot.subsystems.ArmPositions;

public class PickUpBallFromGround extends CommandGroup {
  
  public PickUpBallFromGround() {

    //one button command that gets the robot ready to collect the ball
    //once ball is collected, collector wheels will stop and robot will go to travel position
    addSequential(new InterpolateAndCheck(ArmPositions.PICKUP_FROM_FLOOR));
    addSequential(new CollectCargoWithLimit());
    addSequential(new InterpolateAndCheck(ArmPositions.HOME));
  }
}
