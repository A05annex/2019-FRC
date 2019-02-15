/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.PickUpBall;
import frc.robot.commands.SetArmTarget;
import frc.robot.subsystems.ArmPositions;

public class PickUpBallFromGround extends CommandGroup {
  
  public PickUpBallFromGround() {

    addSequential(new SetArmTarget(ArmPositions.PICKUP_FROM_FLOOR));
    //add argument PICKUP_FROM_FLOOR
    //does MoveToTarget() take arguments yet?
    addSequential(new PickUpBall());
    addSequential(new SetArmTarget(ArmPositions.HOME));
    //is HOME the correct postition for travelling?

    //not hooked up to button yet 

  }
}
