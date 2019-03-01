package frc.robot;

/**
 * Constants that configure and tune robot operation.
 */
public class Constants {
    // The practice robot has a different hardware configuration:
    // * true - This is the competition configuration
    // * false - This is the practice robot configuration
    public static final boolean COMPETITION_ROBOT = false;

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


    public static final double END_GAME_PNEUMATICS_LIFT_DURATION = 3.0;
    public static final double END_GAME_PNEUMATICS_RETRACT_DURATION = 3.0;
    public static final double END_GAME_MOTOR_POWER = 0.7;
    public static final double END_GAME_DRIVE_DURATION = 4.0;
    public static final int INTERPOLATE_STEPS = 50;
    public static final double SECS_FROM_NEUTRAL_TO_FULL = 2.0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Drive tuning constants                                                                                         //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double DRIVE_FORWARD_GAIN = 0.66;
    public static double DRIVE_TURN_GAIN = 0.5;
    public static double DRIVE_AT_SPPED_GAIN = 0.1;
    public static double DRIVE_FORWARD_SENSITIVITY = 1.0;
    public static double DRIVE_TURN_SENSITIVITY = 1.0;
    public static double DRIVE_DEADBAND= 0.05;

    public static double FINE_CONTROL_MAX = 0.2;


}
