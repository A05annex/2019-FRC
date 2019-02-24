/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class ArmInterpolateToTarget extends Command {
   
  ArmPositions finalTarget;
  double[] newTarget;
  double[] bumpIncs = new double[3];
  int currentIncrement = 0;
  double[] currentTarget;

  public ArmInterpolateToTarget(ArmPositions newTarget) {
    super();
    requires(Robot.armInterpolate);
    this.newTarget = Robot.armDriveTrain.getTargetPositionAngles(newTarget);
    finalTarget = newTarget;
  }

  // called whenever this command is restarted
  @Override
  protected void initialize() {
    currentIncrement = 0;
    currentTarget = Robot.armDriveTrain.getCurrentTargetAngles();
    double deltaLower = newTarget[0] - currentTarget[0];
    double deltaUpper = newTarget[1] - currentTarget[1];
    double deltaBucket = newTarget[2] - currentTarget[2];
    bumpIncs[0] = deltaLower / Constants.INTERPOLATE_STEPS;
    bumpIncs[1] = deltaUpper / Constants.INTERPOLATE_STEPS;
    bumpIncs[2] = deltaBucket / Constants.INTERPOLATE_STEPS;

    }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentTarget[0] += bumpIncs[0];
    currentTarget[1] += bumpIncs[1];
    currentTarget[2] += bumpIncs[2];
    currentIncrement += 1;
    Robot.armDriveTrain.setTargetAngle(currentTarget);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(currentIncrement>=Constants.INTERPOLATE_STEPS){
      Robot.armDriveTrain.setTargetPosition(finalTarget);
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

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
