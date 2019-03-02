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
import frc.robot.commands.Grab;
import frc.robot.subsystems.ArmPositions;
import frc.robot.subsystems.Bucket;

public class PickUpHatch extends CommandGroup {
  
  public PickUpHatch() {

    requires(Robot.bucket);
    requires((Subsystem)Robot.armDriveTrain);

    //one button command that gets the robot ready to collect the ball
    //once ball is collected, collector wheels will stop and robot will go to travel position
    addSequential(new InterpolateAndCheck(ArmPositions.LOW_CARGO));
    addSequential(new Grab(Grab.RELEASE_HATCH));
  }

  @Override
  protected void end(){
    new Grab(Grab.GRAB_HATCH).start();
    Robot.bucket.state = Bucket.HATCH;
  }

  @Override
  protected void interrupted(){
    end();
  }
}
