/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Lift;

public class EndGameLifter extends Command {
  
  DoubleSolenoid liftSolenoid = new DoubleSolenoid(RobotMap.lift1, RobotMap.lift2);

  public EndGameLifter() {
    requires(Robot.lift);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  public void liftUp() {
    Robot.lift.up();

  }

  public void goDown(){
    Robot.lift.down();
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }

}

  