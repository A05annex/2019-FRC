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

public class DepositDrive extends Command {

  private final Timer time = new Timer();

  public DepositDrive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
    time.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //how make motors go backwards? 
    //negative double?
    Robot.driveTrain.leftMaster.set(0.5);
    Robot.driveTrain.rightMaster.set(0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(time.get()>1.0){
      //i am guessing on time here
      //need just a nudge onto to make sure panel is attatched
      return true;
    }
    else{
      return false;
    }
  
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
