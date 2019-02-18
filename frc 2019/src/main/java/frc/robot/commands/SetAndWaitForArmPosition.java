/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class SetAndWaitForArmPosition extends Command {

  ArmPositions targetPosition;
  
  public SetAndWaitForArmPosition(ArmPositions targetPosition){
    super();
        this.targetPosition = targetPosition;

  }
  

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    super.execute();
        Robot.armDriveTrain.setTargetPosition(targetPosition);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
 
    return Robot.armDriveTrain.isAtTargetPosition();
    //returns true if is at target position
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }
  
  @Override
  protected void interrupted() {
  }
}
