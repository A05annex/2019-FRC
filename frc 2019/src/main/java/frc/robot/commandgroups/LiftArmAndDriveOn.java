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
import frc.robot.commands.TimedLift;
import frc.robot.subsystems.ArmPositions;

public class LiftArmAndDriveOn extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LiftArmAndDriveOn() {

    //Part of LiftToPlatform. 
    //At the end of the sequence, so liftingPower is set back to false, as the arm no longer needs full power. 
    //The arm lifts up and drives onto the platform in a way where it will not unbalance the robot. 
    //The arm then returns to home position. 

    addSequential(new LiftingPower(false));
    addParallel(new TimedLift(6.0));
    addParallel(new TimedDrive(6.0, 0.2));
    addSequential(new InterpolateAndCheck(ArmPositions.LIFT_ARM));
    addParallel(new TimedDrive(1.0, 0.5));
    addSequential(new InterpolateAndCheck(ArmPositions.HOME));
    addSequential(new TimedDrive(1.5, 0.5));
  }
}
