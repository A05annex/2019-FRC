package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class BumpTargetPosition extends Command {

    /**
     * A calibration command used to bump the arm target by a small delta to aid in refining rough target
     * positions determined through hand positioning to the actual positions that will work in competition.
     *
     * @param lowerAngleDelta (double) the delta angle to be added to the lower arm target angle.
     * @param upperAngleDelta (double) the delta angle to be added to the upper arm target angle.
     * @param bucketAngleDelta (double) the delta angle to be added to the bucket angle.
     */
    public BumpTargetPosition(double lowerAngleDelta, double upperAngleDelta, double bucketAngleDelta) {
        super();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
