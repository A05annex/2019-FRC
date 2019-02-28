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

    private static final int LOWER = 0;
    private static final int UPPER = 1;
    private static final int BUCKET = 2;

    ArmPositions finalTarget;
    double[] newTarget;
    double[] bumpIncs = new double[3];
    int currentIncrement = 0;
    double[] currentTarget;

    public ArmInterpolateToTarget(ArmPositions newTarget) {
        super();
        requires(Robot.armInterpolate);
        finalTarget = newTarget;
    }

    // called whenever this command is restarted
    @Override
    protected void initialize() {
        currentIncrement = 0;
        // in initialized because the position in the table may have been tuned
        newTarget = Robot.armDriveTrain.getTargetPositionAngles(finalTarget);
        // get the current position and compute the angle increments for the reposition
        currentTarget = Robot.armDriveTrain.getCurrentTargetAngles();
        double deltaLower = newTarget[LOWER] - currentTarget[LOWER];
        double deltaUpper = newTarget[UPPER] - currentTarget[UPPER];
        double deltaBucket = newTarget[BUCKET] - currentTarget[BUCKET];
        bumpIncs[LOWER] = deltaLower / Constants.INTERPOLATE_STEPS;
        bumpIncs[UPPER] = deltaUpper / Constants.INTERPOLATE_STEPS;
        bumpIncs[BUCKET] = deltaBucket / Constants.INTERPOLATE_STEPS;

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        currentTarget[LOWER] += bumpIncs[LOWER];
        currentTarget[UPPER] += bumpIncs[UPPER];
        currentTarget[BUCKET] += bumpIncs[BUCKET];
        currentIncrement += 1;
        Robot.armDriveTrain.setTargetAngle(currentTarget);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (currentIncrement >= Constants.INTERPOLATE_STEPS) {
            Robot.armDriveTrain.setTargetPosition(finalTarget);
            return true;
        } else {
            return false;
        }

    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // nothing to do here
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        // if this is interrupted, just stop undating positions, no other action required
    }
}
