package frc.robot;

/**
 * Constants that configure and tune robot operation.
 */
public class Constants {
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

}
