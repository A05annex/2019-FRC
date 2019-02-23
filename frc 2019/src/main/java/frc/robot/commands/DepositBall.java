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
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.bucketWheelz.eject();
    //this should run the collect method from BucketWheelz
    
  }
  @Override
  protected boolean isFinished() {
    if(Robot.bucketLimitSwitch.bucketSwitch.get()==true){
      time.reset();
      time.start();
      if(time.get()>1.0){
        return true;
      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
    //ends command if the limit switch is not pressed anymore and one second has passed
  }


  @Override
  protected void end() {
    Robot.bucketWheelz.stop();
  }
  @Override
  protected void interrupted() {
  }
}
