/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class EndGameLifter extends Command {
  
  private final Timer time = new Timer();

  public EndGameLifter() {
    requires(Robot.lift);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  @Override
  protected void initialize() {
    time.start();
  }
  @Override
  protected void execute() {
    Robot.lift.lift_robot();
    }
    
  @Override
  protected boolean isFinished() {
    if(time.get()>Constants.END_GAME_PNEUMATICS_LIFT_DURATION){
      return true;
      }
      else{
      return false;
      }

  }

  @Override
  protected void end(){
    
  }

}

  