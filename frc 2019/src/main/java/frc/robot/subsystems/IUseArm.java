package frc.robot.subsystems;

/**
 * This is an interface for using the robot arm so we can have an implementation that uses the position
 * potentiometers hooked to the Roborio and an implementation where rhe potentiometers are hooked directly
 * to the Talon SRX controllers and we use the magic control stuff in the motor controller
 */
public interface IUseArm {
    /**
     * Get the angle of the lower arm.
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
     * Set the arm motor power for the lower arm.
     *
     * @param lowerArmPower (double) The power to the lower arm in the range -1 to
     *                      1; where a positive value is up and a negative value is
     *                      down.
     */
    void inputDriveLowArm(double lowerArmPower);

    /**
     * Set the arm motor power for the upper arm.
     *
     * @param upperArmPower (double) The power to the upper arm in the range -1 to
     *                      1; where a positive value is up and a negative value is
     *                      down.
     */
    void inputDriveUppArm(double upperArmPower);

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
     * A calibration function used to bump the arm target by a small delta to aid in refining rough target
     * positions determined through hand positioning to the actual positions that will work in competition.
     *
     * @param lowerAngleDelta (double) the delta angle to be added to the lower arm target angle.
     * @param upperAngleDelta (double) the delta angle to be added to the upper arm target angle.
     * @param bucketAngleDelta (double) the delta angle to be added to the bucket angle.
     */
    void bumpTargetPosition(double lowerAngleDelta, double upperAngleDelta, double bucketAngleDelta);

    /**
     * Call this repeatedly to move the arm to target
     */
    void moveToTarget();

    void stop();
}
