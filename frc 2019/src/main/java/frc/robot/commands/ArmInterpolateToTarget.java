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

  //Moves the arm to a target position by interpolating the arm angles between the current position and the target.
  //
   
  ArmPositions finalTarget;
  double[] newTarget;
  double[] bumpIncs = new double[3];
  int currentIncrement = 0;
  double[] currentTarget;
  int interpolateSteps;

  public ArmInterpolateToTarget(ArmPositions newTarget) {
    super();
    requires(Robot.armInterpolate);
    this.newTarget = Robot.armDriveTrain.getTargetPositionAngles(newTarget);
    finalTarget = newTarget;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    currentIncrement = 0;
    currentTarget = Robot.armDriveTrain.getCurrentTargetAngles();
    double deltaLower = newTarget[0] - currentTarget[0];
    double deltaUpper = newTarget[1] - currentTarget[1];
    double deltaBucket = newTarget[2] - currentTarget[2];
    interpolateSteps = getSteps();
    bumpIncs[0] = deltaLower / interpolateSteps;
    bumpIncs[1] = deltaUpper / interpolateSteps;
    bumpIncs[2] = deltaBucket / interpolateSteps;

    }

  protected int getSteps(){
    return Constants.INTERPOLATE_STEPS;
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
    if(currentIncrement>=interpolateSteps){
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
