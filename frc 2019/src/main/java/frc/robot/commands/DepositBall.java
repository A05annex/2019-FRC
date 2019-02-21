/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DepositBall extends Command {

  Timer time = new Timer();

  public DepositBall() {
    requires(Robot.bucketWheelz);
    requires(Robot.bucketLimitSwitch);
  }

  @Override
  protected void initialize() {
    time.reset();
    time.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.bucketWheelz.eject();
    //this should run the collect method from BucketWheelz
    
  }
  @Override
  protected boolean isFinished() {
    if(time.get()>2.0){
      return true;
    }
    else{
      return false;
    }
    //guessing on the time here. will be however long it takes the wheels to intake a ball
  }


  @Override
  protected void end() {
    Robot.bucketWheelz.stop();
  }
  @Override
  protected void interrupted() {
  }
}
