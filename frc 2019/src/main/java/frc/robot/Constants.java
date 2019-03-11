package frc.robot;

/**
 * Constants that configure and tune robot operation.
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

    // This the calibration bump
    public static final double BUMP_INCREMENT = 0.25;

    public static final double TARGET_POSITION_TOLERANCE = 5.0;


    public static final double END_GAME_PNEUMATICS_LIFT_DURATION = 5.5;
    public static final double END_GAME_PNEUMATICS_RETRACT_DURATION = 4.0;
    public static final double END_GAME_PNEUMATICS_DROP = 1.0;
    
    public static final double END_GAME_MOTOR_POWER = 0.7;
    public static final double END_GAME_DRIVE_DURATION = 4.0;
    public static final int INTERPOLATE_STEPS = 50;
    public static final double SECS_FROM_NEUTRAL_TO_FULL = 2.0;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Drive tuning constants                                                                                         //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The multiplier for full stick to give the power/speed requested from the drive.
    public static double DRIVE_FORWARD_GAIN = 0.8;
    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_GAIN = 0.5;
    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_AT_SPEED_GAIN = 0.3;
    // The center-stick sensitivity, which is really the exponent applied to the stick position to flatten drive
    // response to stick position for greater sensitivity at low speed.
    public static double DRIVE_SENSITIVITY = 2.0;
    // The width of the 0 dead-band of the stick as a fraction of full stick movement.
    public static double DRIVE_DEADBAND = 0.05;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Lift tuning constants                                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The number of control cycles for each lift correction cycle
    public static int LIFT_CORRECT_CYCLES = 10;
    // The angle where we get to cylinder stopped.
    public static double LIFT_CORRECT_MAX_ANGLE = 8.0;
    // The expected lft time in seconds, everything locks on after that
    public static double LIFT_TIME = 7.0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Drive tuning constants                                                                                         //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The multiplier for full stick to give the power/speed requested from the drive.
    public static double DRIVE_FORWARD_GAIN = 0.8;
    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_GAIN = 0.5;
    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_AT_SPEED_GAIN = 0.3;
    // The center-stick sensitivity, which is really the exponent applied to the stick position to flatten drive
    // response to stick position for greater sensitivity at low speed.
    public static double DRIVE_SENSITIVITY = 2.0;
    // The width of the 0 dead-band of the stick as a fraction of full stick movement.
    public static double DRIVE_DEADBAND = 0.05;


}
