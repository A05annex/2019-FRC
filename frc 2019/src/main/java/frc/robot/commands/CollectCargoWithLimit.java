/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CollectCargoWithLimit extends Command {
  public CollectCargoWithLimit() {

    requires(Robot.bucket);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.bucket.collectBall();
    //runs wheels so robot is ready to collect ball

  }

  @Override
  protected boolean isFinished() {

    return Robot.bucket.bucketSwitch.get();
    //returns true when switch is hit by ball
  }

  @Override
  protected void end() {

    Robot.bucket.stopWheels();
  }

  @Override
  protected void interrupted() {
  }
}
