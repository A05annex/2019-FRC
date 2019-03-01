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

    requires(Robot.bucketLimitSwitch);
    requires(Robot.bucketWheelz);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.bucketWheelz.collect();
    //runs wheels so robot is ready to collect ball

  }

  @Override
  protected boolean isFinished() {

    if((Robot.bucketLimitSwitch.bucketSwitch.get() == true) && (Robot.bucketLimitSwitch.bucketSwitch2.get() == true)){
      return true;
    }
    else{
      return false;
    }
    //return Robot.bucketLimitSwitch.bucketSwitch.get();
    
    //returns true when switch is hit by ball
  }

  @Override
  protected void end() {

    Robot.bucketWheelz.stop();
  }

  @Override
  protected void interrupted() {
  }
}
