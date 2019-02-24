package frc.robot.commands;

/**
 * This is an alternate implementation to the {@link ArmInterpolateToTarget} that interpolates positions in the
 * path and maps those to changes in the arm angles. The {@link ArmInterpolateToTarget} implementation interpolates
 * arm angles between start and end - this does not ensure the end of the arm takes a linear path in space from
 * the start to the end.
 *
 * This command maps the current and target angle positions to spatial positions, interpolates the spatial
 * positions with ease-out and ease-in and maps those back to the target angle positions for the move. Unlike
 * {@link ArmInterpolateToTarget} which used a 1 sec time to move from start to end, this function uses a maximum
 * path movement speed - so the time from current to target changes depending on distance of movement.
 */
public class ArmPathInterpToTarget {
}
