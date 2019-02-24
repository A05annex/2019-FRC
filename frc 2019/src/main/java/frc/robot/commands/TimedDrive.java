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

public class TimedDrive extends Command {

    private final Timer time = new Timer();
    private final double driveTime;
    private final double power;

    public TimedDrive(double driveTime, double power) {
        super();
        requires(Robot.driveTrain);
        this.driveTime = driveTime;
        this.power = power;

    }

    @Override
    protected void initialize() {
        time.reset();
        time.start();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //how make motors go backwards?
        //negative double?
        Robot.driveTrain.leftMaster.set(power);
        Robot.driveTrain.rightMaster.set(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (time.get() > driveTime) {

            Robot.driveTrain.leftMaster.set(0);
            Robot.driveTrain.rightMaster.set(0);

            return true;
        } else {

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
