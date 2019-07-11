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
    // * false - Drive shifter is disabled
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
    // The multiplier for full stick to give the power/speed requested from the drive.
    public static double DRIVE_FORWARD_GAIN = 0.8;
    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_GAIN = 0.3;
    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_AT_SPEED_GAIN = 0.05;
    // The center-stick sensitivity, which is really the exponent applied to the stick position to flatten drive
    // response to stick position for greater sensitivity at low speed.
    public static double DRIVE_SENSITIVITY = 2.0;
    // The width of the 0 dead-band of the stick as a fraction of full stick movement.
    public static double DRIVE_DEADBAND = 0.05;
    // The correction for the tendency of the robot to systemically turn as power is applied to the drive. This
    // tendency to turn can result from many factors - alignment, friction, motor differences, controller
    // differences, etc.
    public static double DRIVE_TURN_BIAS = 0.0;

    public static int TALON_TIMEOUT = 30;

    //units pre wheel revolution
    public final static int SENSOR_UNITS_PER_REV = 512;

    // Motor neutral dead-band, set to the minimum 0.1%.
    public final static double kNeutralDeadband = 0.001;

    /**
     * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 6800 represents Velocity units at 100% output
     * Not all set of Gains are used in this project and may be removed as desired.
     *
     * 	                                    			  kP   kI   kD   kF               Iz    PeakOut */
//    public final static Gains kGains_Distanc = new Gains( 0.1, 0.0,  0.0, 0.0,            100,  0.50 );
//    public final static Gains kGains_Turning = new Gains( 2.0, 0.0,  4.0, 0.0,            200,  1.00 );
    public final static Gains kGains_Velocit = new Gains( 0.1, 0.0, 20.0, 1023.0/6800.0,  300,  0.75 );
//    public final static Gains kGains_MotProf = new Gains( 1.0, 0.0,  0.0, 1023.0/6800.0,  400,  1.00 );

    public static double FINE_CONTROL_MAX = 0.2;


}
