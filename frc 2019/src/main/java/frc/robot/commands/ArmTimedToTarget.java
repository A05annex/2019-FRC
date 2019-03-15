/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.ArmPositions;

public class ArmTimedToTarget extends ArmInterpolateToTarget {

  private final double duration;
  private final double delay;
  Timer time = new Timer();
  public ArmTimedToTarget(ArmPositions target, double delay, double duration) {
    super(target);
    this.duration = duration;
    this.delay = delay;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    super.initialize();
    time.reset();
    time.start();

  }

  @Override
  protected int getSteps(){
    return (int)(duration * 50);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(time.get()<delay){
      return;
    }
    super.execute();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return super.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    super.end();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
