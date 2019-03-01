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

public class DepositCargoWithLimit extends Command {

    Timer time = new Timer();

    public DepositCargoWithLimit() {
        requires(Robot.bucketWheelz);
        requires(Robot.bucketLimitSwitch);
    }

    @Override
    protected void initialize() {
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.bucketWheelz.collect();
        //this should run the collect method from BucketWheelz

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if((Robot.bucketLimitSwitch.bucketSwitch.get() == false) && (Robot.bucketLimitSwitch.bucketSwitch2.get() == false)){
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
