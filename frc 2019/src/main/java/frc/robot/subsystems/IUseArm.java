package frc.robot.subsystems;

/**
 * This is an interface for using the robot arm so we can have an implementation that uses the position
 * potentiometers hooked to the Roborio and an implementation where rhe potentiometers are hooked directly
 * to the Talon SRX controllers and we use the magic control stuff in the motor controller
 */
public interface IUseArm {
    /**
     * Get the angle of the lower arm. The angle of the lower arm is 90&deg; when perfectly vertical
     * to the ground surface; 0&deg; would be the arm moved towards the front of the robot (moved retract_lifters)
     * until it is parallel to the ground, 180&deg; would be the arm moved towards the rear of the robot
     * (moved lift_robot) until it is parallel to the ground.
     *
     * @return (double) The position angle of the lower arm.
     */
    double getLowerArmAngle();

    /**
     * Get the angle of the upper arm.
     *
     * @return (double) The position angle of the upper arm.
     */
    double getUpperArmAngle();

    /**
     * Get the angle of the bucket.
     *
     * @return (double) The position angle of the bucket
     */
    double getBucketAngle();

    /**
     * Set the arm motor power (or speed, depending on the implementation)for the lower arm.
     *
     * @param lowerArmPower (double) The power to the lower arm in the range -1 to
     *                      1; where a positive value is lift_robot and a negative value is
     *                      retract_lifters.
     */
    void inputDriveLowArm(double lowerArmPower);

    /**
     * Set the arm motor power (or speed, depending on the implementation) for the upper arm.
     *
     * @param upperArmPower (double) The power to the upper arm in the range -1 to
     *                      1; where a positive value is lift_robot and a negative value is
     *                      retract_lifters.
     */
    void inputDriveUppArm(double upperArmPower);

    /**
     * Set the bucket motor power (or speed, depending on the implementation) for the upper arm.
     *
     * @param bucketPower (double) The power to the upper arm in the range -1 to
     *                    1; where a positive value is lift_robot and a negative value is
     *                    retract_lifters.
     */
    void inputDriveBucket(double bucketPower);

    /**
     * Set a target arm position. The arm will move and hold at that target position until either another
     * target position is set, or,there is a call to {@link #inputDriveLowArm(double)} or
     * {@link #inputDriveLowArm(double)}, which indicates that some other mechanism (like driver control by stick)
     * is taking control of the arm
     *
     * @param armPosition ({@link ArmPositions}) The target position for the arm selected from a list of
     *                    known target positions
     */
    void setTargetPosition(ArmPositions armPosition);

    /**
     * Get the currently set target position.
     *
     * @return ({ @ link ArmPositions }) The currently set target position.
     */
    ArmPositions getTargetPosition();

    /**
     * 
     * @param armPosition The arm position
     * @return (double[]) The [lower,upper,bucket] arm angles for this position.
     */
    double[] getTargetPositionAngles(ArmPositions armPosition);

    double[] getCurrentTargetAngles();

    void setTargetAngle(double[] targetAngles);
    /**
     * Is the arm at the currently set target point
     *
     * @return (boolean) Returns <tt>true</tt> if the arm is at the target point (within a reasonable tolerance),
     * <tt>false</tt> otherwise.
     */
    boolean isAtTargetPosition();

    /**
     * A calibration function used to bump the arm target by a small delta to aid in refining rough target
     * positions determined through hand positioning to the actual positions that will work in competition.
     *
     * @param lowerAngleDelta  (double) the delta angle to be added to the lower arm target angle.
     * @param upperAngleDelta  (double) the delta angle to be added to the upper arm target angle.
     * @param bucketAngleDelta (double) the delta angle to be added to the bucket angle.
     */
    void bumpTargetPosition(double lowerAngleDelta, double upperAngleDelta, double bucketAngleDelta);

    void resetIntegral();

    /**
     * Call this repeatedly to move the arm to target
     */
    void moveToTarget();

    void stop();

    void setLifting(boolean lifting);

    void setBucketLifting(boolean bucketLifting);
}
