package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArmToTarget;

/**
 * The robot arm drive subsystem. This subsystem is controlling the lower arm
 * motor and the upper arm motor only. There are position potentiometers for the
 * lower and upper arms controlled by those motors. The control of the
 * head/bucket is independent of the arm.
 * <p>
 * Roy's note: I'm thinking the arm drive subsystem knows about the
 * potentiometers and the reporting of arm position, and that the control
 * happens in the commands initially - some control method may move to the arm
 * once we really know how to control them.
 */
public class ArmDriveTrain extends Subsystem implements IUseArm {


    private static final int LOWER = 0;
    private static final int UPPER = 1;
    private static final int BUCKET = 2;

    // picked this because it unambiguously represented and way outside any reasonable angle range.
    private static final double AUTO_POSITION_BUCKET = 1024.0;

    private double[][] targetPositions = {
            {92.0, 23.0, 0.0},                          // PREGAME
            {100.0, 25.0, 5.0},                         // HOME
            {120.5, 23.0, AUTO_POSITION_BUCKET},        // LOW_HATCH
            {92.0, 23.0, AUTO_POSITION_BUCKET},         // LOW_CARGO
            {127.5, 65.0, AUTO_POSITION_BUCKET},        // MID_HATCH
            {127.5, 65.0, AUTO_POSITION_BUCKET},        // MID_CARGO
            {105.5, 110.0, AUTO_POSITION_BUCKET},       // HIGH_HATCH
            {105.5, 110.0, AUTO_POSITION_BUCKET},       // HIGH_CARGO
            {85.0, 40.0, 90.0},                         // PICKUP_FROM_FLOOR
            {46.0, 72.5, 0.0},                          // PRE_ENDGAME_LIFT
            {29.5, 95.0, 0.0},                          // ENDGAME_LIFT
            {29.5, 95.0, 0.0},                          // ENDGAME_LAND
            {29.5, 95.0, 0.0},                          // ENDGAME_PARK
            {100.0, 25.0, 5.0}                          // POST_ENDGAME_PARK
    };

    private ArmPositions targatPosition = ArmPositions.PREGAME;

    public double angle1, angle2;

    // construction os the sensor potentiometers hooked to the analog inputs of the Roborio
    private final AnalogPotentiometer lowerArmAngle =
            new AnalogPotentiometer(2, -360, 334.1);
    private final AnalogPotentiometer upperArmAngle =
            new AnalogPotentiometer(3, -360, 360.0);

    // construction of arm motors
    private final WPI_TalonSRX armMotorLower = new WPI_TalonSRX(RobotMap.arm1);
    private final WPI_TalonSRX armMotorUpper = new WPI_TalonSRX(RobotMap.arm2);

    // Limit angles determined by manually moving the arms to the positions we would like to have as limits of motion.
    private final double lowerArmMin = 30.0;
    private final double lowerArmMax = 130.0;
    private final double upperArmMin = 40.0;
    private final double upperArmMax = 140.0;
    private final double armStopBuffer = 5.0; // The degrees before the hard stop that you should
    // cut power to 0.0
    private final double armCreepBuffer = 15.0; // The distance before the hard stop that you
    // should cut power to creep power
    private final double armCreepPower = 0.2; // The maximum power in the creep zone

    public ArmDriveTrain() {
        // configures both drive motors for the motors
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setInverted(true);
    }

    // default command for the subsystem, this one being tele-operation for the arm
    public void initDefaultCommand() {
        setDefaultCommand(new MoveArmToTarget());
    }

    @Override
    public double getLowerArmAngle() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return lowerArmAngle.get();
    }

    @Override
    public double getUpperArmAngle() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return upperArmAngle.get();
    }

    @Override
    public double getBucketAngle() {
        return 0;
    }

    @Override
    public void inputDriveLowArm(double lowerArmPower) {
        // TODO: check lower arm power direction, test against arm position and/or limit
        // switch and set to 0 if we have hit the constraint for that direction
        if (lowerArmPower < 0.0) {
            if (getLowerArmAngle() < (lowerArmMin + armCreepBuffer)) {
                lowerArmPower = (getLowerArmAngle() < (lowerArmMin + armStopBuffer)) ? 0.0 : -armCreepPower;
            }
        } else if (lowerArmPower > 0.0) {
            if (getLowerArmAngle() > (lowerArmMax - armCreepBuffer)) {
                lowerArmPower = (getLowerArmAngle() > (lowerArmMax - armStopBuffer)) ? 0.0 : armCreepPower;
            }
        }
        armMotorLower.set(lowerArmPower);
    }

    /**
     * Set the arm motor power for the upper arm.
     *
     * @param upperArmPower (double) The power to the upper arm in the range -1 to
     *                      1; where a positive value is up and a negative value is
     *                      down.
     */
    @Override
    public void inputDriveUppArm(double upperArmPower) {
        // TODO: check upper arm power direction, test against arm position and/or limit
        // switch and set to 0 if we have hit the constraint for that direction
        if (upperArmPower < 0.0) {
            if (getUpperArmAngle() < (upperArmMin + armCreepBuffer)) {
                upperArmPower = (getUpperArmAngle() < (upperArmMin + armStopBuffer)) ? 0.0 : -armCreepPower;
            }
        } else if (upperArmPower > 0.0) {
            if (getUpperArmAngle() > (upperArmMax - armCreepBuffer)) {
                upperArmPower = (getUpperArmAngle() > (upperArmMax - armStopBuffer)) ? 0.0 : armCreepPower;
            }
        }
        armMotorUpper.set(upperArmPower);
    }

    @Override
    public void setTargetPosition(ArmPositions armPosition) {
        targatPosition = armPosition;
    }

    @Override
    public void moveToTarget() {

    }

    @Override
    public void stop() {
        // method to easily stop the motors
        armMotorLower.set(0.0);
        armMotorUpper.set(0.0);
    }
 }