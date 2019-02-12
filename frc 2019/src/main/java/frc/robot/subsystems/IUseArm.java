package frc.robot.subsystems;

/**
 * This is an interface for using the robot arm so we can have an implementation that uses the position
 * potentiometers hooked to the Roborio and an implementation where rhe potentiometers are hooked directly
 * to the Talon SRX controllers and we use the majic control stuff in the motor controller
 */
public interface IUseArm {
    /**
     * The position of the lower arm in the range <tt>lowerArmMin</tt> to
     * <tt>lowerArmMax</tt>
     *
     * @return (double) The position of the lower arm.
     */
    double getLowerArmPosition();

    /**
     * The position of the upper arm in the range <tt>upperArmMin</tt> to
     * <tt>upperArmMax</tt>
     *
     * @return (double) The position of the upper arm.
     */
    double getUpperArmPosition();

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
     * @param armPosition ({@link ArmPositions}) The target position for the arm selected from a list of
     *                    known target positions
     */
    void setTargetPosition(ArmPositions armPosition);

    void stop();
}
