package frc.robot;

/**
 * Constants that configure and tune robot operation. These are mostly physical constants that express either robot
 * geometry, pneumatics performance, or drive constraints.
 */
public class Constants {
    // The practice robot has a different hardware configuration:
    // * true - This is the competition configuration
    // * false - This is the practice robot configuration
    public static final boolean COMPETITION_ROBOT = true;

    // Enable shifter control on the 2 speed gearbox
    // * true - Drive shifter is enabled
    // * false - Drive shifter is diabled
    public static final boolean ENABLE_DRIVE_SHIFT = false;

    // Enable calibration functions controlled by a gamepad. This let's us use the gamepad to make adjustments to
    // some of the configuration values to tune dynamically tune the robot without needing to change code, rebuild,
    // reload for every adjustment. When calibration is enabled a gamepad is required. When calibration is
    // disabled a gamepad is not required.
    public static final boolean ENABLE_CALIBRATION = true;

    // The physical distance, in inches, from the CL of the lower arm pivot axel to the CL of the upper arm pivot
    // axel.
    public static final double LOWER_ARM_LENGTH = 41.25;
    // The physical distance, in inches, from the CL of the upper arm pivot to the CL of the bucket pivot.
    public static final double UPPER_ARM_LENGTH = 39.5;

    // This is the calibration bump - the change in angle (degrees) that the POV calibration control adds to the
    // arm/bucket
    public static final double BUMP_INC_ANGLE = 0.25;
    public static final double BUMP_INC_POSITION = 0.25;
    public static final double BUMP_INC_BUCKET = 0.25;

    // The tolerance for lower arm, upper arm, and bucket angle positions before we deem the arm and bucket to be
    // in the target position.
    public static final double TARGET_POSITION_TOLERANCE = 5.0;

    // The time (in seconds) we allow for lift before we use the arm to drag the robot onto the platform.
    public static final double END_GAME_PNEUMATICS_LIFT_DURATION = 3.0;
    // The time (in seconds) we allow for lifter retraction before we do the final drive onto the platform.
    public static final double END_GAME_PNEUMATICS_RETRACT_DURATION = 3.0;
    public static final double END_GAME_MOTOR_POWER = 0.7;
    public static final double END_GAME_DRIVE_DURATION = 4.0;

    public static final double LOWER_ARM_PROPORTIONAL_CONSTANT = 20.0;
    public static final double LOWER_ARM_NTEGRAL_CONSTANT = 3.0;
    public static final double UPPER_ARM_PROPORTIONAL_CONSTANT = 20.0;
    public static final double UPPER_ARM_NTEGRAL_CONSTANT = 3.0;
    public static final double BUCKET_ARM_PROPORTIONAL_CONSTANT =30.0;


    // This is the number of interpolation steps from the current position to the target position using the
    // {@link ./commands/ArmInterpolateToTarget} command for arm movement.
    public static final int INTERPOLATE_STEPS = 50;

    public static final double ARM_INCHES_PER_CYCLE = 0.5;

    public static final double SECS_FROM_NEUTRAL_TO_FULL = 2.0;


}
