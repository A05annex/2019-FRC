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

    private static final int LOWER = 0;
    private static final int UPPER = 1;
    private static final int BUCKET = 2;

    private static final int X = 0;
    private static final int Y = 1;

    ArmPositions finalTarget;
    int currentIncrement = 0;
    double useIncrements;
    double[] currentTarget;
    double[] currentPosition = new double[2];
    double[] bumpIncs = new double[3];

    public ArmPathInterpToTarget(ArmPositions newTarget) {
        super();
        requires(Robot.armInterpolate);
        finalTarget = newTarget;

    }

    /**
     * Convert the arm angles to an end position for the arm
     * @param angles (double[]) The angles to be converted.
     * @param position (double[] modified) The x and y positions are written into this array.
     * @return (double[]) Returns the passed in array
     */
    static public double[] anglesToPosition(double[] angles, double[] position) {
        position[X] = (Constants.LOWER_ARM_LENGTH * Math.cos(Math.toRadians(angles[LOWER]))) +
                (Constants.UPPER_ARM_LENGTH * Math.cos(Math.toRadians((angles[LOWER]+angles[UPPER])-180.0)));
        position[Y] = (Constants.LOWER_ARM_LENGTH * Math.sin(Math.toRadians(angles[LOWER]))) +
                (Constants.UPPER_ARM_LENGTH * Math.sin(Math.toRadians((angles[LOWER]+angles[UPPER])-180.0)));
        return position;
    }

    /**
     * Convert an end position to the angles that would produce that end position.
     * @param pt (double[]) The position to be converted
     * @param angles (double[] modified) The lower and upper arm angles are written into
     *               this array.
     * @return (double[]) Returns the passed in angles array.
     */
    static public double[] positionToAngles(double[] pt, double[] angles) {
        double D = Math.sqrt((pt[X] * pt[X]) + (pt[Y] * pt[Y]));
        double angle_D = Math.atan2(pt[Y], pt[X]);
        double angle_d_lower = Math.toDegrees(Math.acos((Constants.LOWER_ARM_LENGTH * Constants.LOWER_ARM_LENGTH) -
                (Constants.UPPER_ARM_LENGTH * Constants.UPPER_ARM_LENGTH) + (D * D) /
                (2.0 * Constants.LOWER_ARM_LENGTH * D)));
        angles[LOWER] = angle_D + angle_d_lower;
        angles[UPPER] = Math.toDegrees(Math.acos((Constants.LOWER_ARM_LENGTH * Constants.LOWER_ARM_LENGTH) +
                (Constants.UPPER_ARM_LENGTH * Constants.UPPER_ARM_LENGTH) - (D * D)) /
                (2.0 * Constants.LOWER_ARM_LENGTH * Constants.UPPER_ARM_LENGTH));
        return angles;
    }
    // called whenever this command is restarted
    @Override
    protected void initialize() {
        currentIncrement = 0;
        // in initialized because the position in the table may have been tuned
        double[] endAngles = Robot.armDriveTrain.getTargetPositionAngles(finalTarget);
        double[] endPosition = anglesToPosition(endAngles, new double[2]);
        // in initialized because the position in the table may have been tuned
        currentTarget = Robot.armDriveTrain.getCurrentTargetAngles();
        anglesToPosition(currentTarget, currentPosition);
        useIncrements = Math.sqrt((endPosition[X] * endPosition[X]) + (endPosition[Y] * endPosition[Y])) /
                Constants.ARM_INCHES_PER_CYCLE;
        bumpIncs[X] = (endPosition[X] - currentPosition[X]) / useIncrements;
        bumpIncs[Y] = (endPosition[Y] - currentPosition[Y]) / useIncrements;
        bumpIncs[BUCKET] = (endAngles[BUCKET] - currentTarget[BUCKET]) / useIncrements;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        currentPosition[X] += bumpIncs[X];
        currentPosition[Y] += bumpIncs[Y];
        positionToAngles(currentPosition,currentTarget);
        currentTarget[BUCKET] += bumpIncs[BUCKET];
        currentIncrement += 1;
        Robot.armDriveTrain.setTargetAngle(currentTarget);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (currentIncrement >= useIncrements) {
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