package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

/**
 * This is an alternate implementation to the {@link ArmInterpolateToTarget} that interpolates positions in the
 * path and maps those to changes in the arm angles. The {@link ArmInterpolateToTarget} implementation interpolates
 * arm angles between start and end - this does not ensure the end of the arm takes a linear path in space from
 * the start to the end.
 *
 * This command maps the current and target angle positions to spatial positions, interpolates the spatial
 * positions with ease-out and ease-in and maps those back to the target angle positions for the move. Unlike
 * {@link ArmInterpolateToTarget} which used a 1 sec time to move from start to end, this function uses a maximum
 * path movement speed (inches per update) - so the time from current to target changes depending on distance
 * of movement, and the arm speeds should never exceed that maximum.
 */
public class ArmPathInterpToTarget extends Command {

    ArmPositions finalTarget;
    int currentIncrement = 0;
    double x_start, y_start, bucket_start;
    double x_end, y_end, bucket_end;
    double x_inc, y_inc, bucket_inc;

    public ArmPathInterpToTarget(ArmPositions newTarget) {
        super();
        requires(Robot.armInterpolate);
        finalTarget = newTarget;

    }

    /**
     * Convert the arm angles to an end position for the arm
     * @param angles
     * @return
     */
    private double[] anglesToPosition(double[] angles) {
        double[] position = new double[2];
        position[0] = (Constants.LOWER_ARM_LENGTH * Math.cos(Math.toRadians(angles[0]))) +
                (Constants.UPPER_ARM_LENGTH * Math.cos(Math.toRadians((angles[0]+angles[1])-180.0)));
        position[1] = (Constants.LOWER_ARM_LENGTH * Math.sin(Math.toRadians(angles[0]))) +
                (Constants.UPPER_ARM_LENGTH * Math.sin(Math.toRadians((angles[0]+angles[1])-180.0)));
        return position;
    }

    /**
     * Convert an end position to the angles that would produce that end position.
     * @param pt
     * @return
     */
    private double[] positionToAngles(double[] pt) {
        double[] angles = new double[2];
        double D = Math.sqrt((pt[0] * pt[0]) + (pt[1] * pt[1]));
        double angle_D = Math.atan2(pt[1], pt[0]);
        double lengths = (Constants.LOWER_ARM_LENGTH * Constants.LOWER_ARM_LENGTH) +
                (Constants.UPPER_ARM_LENGTH * Constants.UPPER_ARM_LENGTH) + (D * D);
        angles[1] = Math.toDegrees(Math.acos(lengths /
                (2.0 * Constants.LOWER_ARM_LENGTH * Constants.UPPER_ARM_LENGTH)));
        double angle_d_lower = Math.toDegrees(Math.acos(lengths /
                (2.0 * Constants.LOWER_ARM_LENGTH * D)));
        angles[0] = angle_D + angle_d_lower;
        return angles;
    }
    // called whenever this command is restarted
    @Override
    protected void initialize() {
        currentIncrement = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
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
