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
        requires(Robot.bucket);
    }

    @Override
    protected void initialize() {
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.bucket.ejectBall();
        //this should run the collect method from BucketWheelz

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (time.get() > 1.5) {
            return true;
        } else {
            return false;
        }
        //guessing on the time here. will be however long it takes the wheels to intake a ball
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
