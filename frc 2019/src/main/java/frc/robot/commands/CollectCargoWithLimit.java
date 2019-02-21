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
      requires(Robot.driveTrain);

      //this will be used on actual robot, not during test on old robot
      //for test the victor above will be used
      //requires(Robot.BucketWheelz);
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

      //test
      Robot.driveTrain.rm1.set(0.25);

      //use for actual robot
      //Robot.bucketWheelz.collect();

    }
  
    @Override
    protected boolean isFinished() {
  
      return Robot.bucketLimitSwitch.bucketSwitch.get();
    }
  
    @Override
    protected void end() {

      Robot.driveTrain.rm1.set(0);
    }
  
    @Override
    protected void interrupted() {
    }
  }
