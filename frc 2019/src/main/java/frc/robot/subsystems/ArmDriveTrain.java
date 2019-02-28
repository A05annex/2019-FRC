package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
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

    // picked this because it is unambiguously represented and way outside any reasonable angle range.
    private static final double AUTO_POSITION_BUCKET = 1024.0;

    // The target positions. these are not final because we may be tuning/calibrating positions and the
    // bumpTargetAngles() method may be called to dynamically modify these.
    private double[][] targetPositions = {
            {110.0, 35.0, 0.0},     // PREGAME
            {96.0, 33.0, 30},       // HOME
            {96, 30, 30},           // LOW_HATCH
            {106, 39, 200},         // LOW_CARGO
            {116, 54, 30},          // MID_HATCH
            {116, 71, 500},         // MID_CARGO
            {105.5, 110.0, 30},     // HIGH_HATCH
            {98, 126, 750},         // HIGH_CARGO
            {85.0, 40.0, 90.0},     // PICKUP_FROM_FLOOR

            {75.4, 83.9, 0.0},      // PRE_ENDGAME_LIFT
            {57.6, 78.2, 0.0},      // DURING_LIFT
            {62.5, 62.4, 0.0},      // PULL_IN (front lift only)
            {29.5, 95.0, 0.0},      // ENDGAME_LIFT (rear lift only)
            {71.25, 55.05, 0.0},    // ENDGAME_LAND
            {80.25, 55.05, 0.0},    // ENDGAME_PARK
            {76.9, 49.8, 0.0}       // POST_ENDGAME_PARK (not using)
    };

    private ArmPositions targetPosition = ArmPositions.HOME;
    private int targetPositionIndx = targetPosition.value;
    private double[] targetAngles = {targetPositions[targetPositionIndx][LOWER],
        targetPositions[targetPositionIndx][UPPER],
        targetPositions[targetPositionIndx][BUCKET]};

    // construction of the sensor potentiometers hooked to the analog inputs of the Roborio
    private final AnalogPotentiometer lowerArmAngle =
            new AnalogPotentiometer(2, -360, 352);
    private final AnalogPotentiometer upperArmAngle =
            new AnalogPotentiometer(3, -360, 198);

    // construction of arm motors
    private final WPI_TalonSRX
            armMotorLower = new WPI_TalonSRX(RobotMap.arm1),
            armMotorUpper = new WPI_TalonSRX(RobotMap.arm2),
            bucketMotor = new WPI_TalonSRX(RobotMap.bucket);

    private Timer time = new Timer();
    double lastTime = 0;

    // Limit angles determined by manually moving the arms to the positions we would like to have as limits of motion.
    private final double lowerArmMin = 20.0;    // hits frame
    private final double lowerArmMax = 130.0;   // hits wires and stuff on frame, hits frame at 141.5
    private final double upperArmMin = 40.0;
    private final double upperArmMax = 140.0;

    private final double armStopBuffer = 0.0; // The degrees before the hard stop that you should
    // cut power to 0.0
    private final double armCreepBuffer = 0.0; // The distance before the hard stop that you
    // should cut power to creep power
    private final double armCreepPower = 0.5; // The maximum power in the creep zone

    public ArmDriveTrain() {
        super();
        // Initialize to a known configuration
        armMotorLower.configFactoryDefault();
        armMotorUpper.configFactoryDefault();
        armMotorLower.set(0.0);
        armMotorUpper.set(0.0);
        // configures both drive motors for the motors
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
        bucketMotor.setNeutralMode(NeutralMode.Brake);
        bucketMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        bucketMotor.setSelectedSensorPosition(0);
        armMotorUpper.setInverted(true);
        time.start();
        lastTime = time.get();
    }

    /**
     * This implements a soft limit switch for the power/angle combination.
     *
     * @param power    (double) the requested power.
     * @param angle    (double) the current angle.
     * @param minAngle (double) the maximum angle (something hits a physical limit like hitting the frame, crushing
     *                 other parts of the robot, etc. Whatever is moving needs to stop before it reaches this limit.
     * @param maxAngle (double) the maximum angle (something hits a physical limit like hitting the frame, crushing
     *                 other parts of the robot, etc. Whatever is moving needs to stop before it reaches this limit.
     * @return (double) the power that should be used.
     */
    private double softLimitSwitch(double power, double angle, double minAngle, double maxAngle) {
        if (power < 0.0) {
            if (angle < (minAngle + armCreepBuffer)) {
                power = (angle < (minAngle + armStopBuffer)) ? 0.0 : -armCreepPower;
            }
        } else if (power > 0.0) {
            if (angle > (maxAngle - armCreepBuffer)) {
                power = (angle > (maxAngle - armStopBuffer)) ? 0.0 : armCreepPower;
            }
        }
        return power;
    }

    // default command for the subsystem, this one being tele-operation for the arm
    public void initDefaultCommand() {
        // this turns on automatic positioning
        setDefaultCommand(new MoveArmToTarget());
        // this enables control stick control
//        setDefaultCommand(new ArmTeleop());
    }

    @Override
    public double getLowerArmAngle() {
        return lowerArmAngle.get();
    }

    @Override
    public double getUpperArmAngle() {
        return upperArmAngle.get();
    }

    @Override
    public double getBucketAngle() {
        return 0;
    }

    @Override
    public void inputDriveLowArm(double lowerArmPower) {
        armMotorLower.set(softLimitSwitch(lowerArmPower, getLowerArmAngle(), lowerArmMin, lowerArmMax));
    }


    /**
     * Set the arm motor power for the upper arm.
     *
     * @param upperArmPower (double) The power to the upper arm in the range -1 to
     *                      1; where a positive value is lift_robot and a negative value is
     *                      retract_lifters.
     */
    @Override
    public void inputDriveUppArm(double upperArmPower) {
        armMotorUpper.set(softLimitSwitch(upperArmPower, getUpperArmAngle(), upperArmMin, upperArmMax));
    }

    @Override
    public void inputDriveBucket(double bucketPower) {
        bucketMotor.set(bucketPower);
    }

    @Override
    public void setTargetPosition(ArmPositions armPosition) {
        targetPosition = armPosition;
        targetPositionIndx = armPosition.value;
        targetAngles[0] = targetPositions[targetPositionIndx][0];
        targetAngles[1] = targetPositions[targetPositionIndx][1];
        targetAngles[2] = targetPositions[targetPositionIndx][2];
        //resetIntegral();
    }

    @Override
    public ArmPositions getTargetPosition() {
        return targetPosition;
    }

    @Override
    public boolean isAtTargetPosition() {
        double angles[] = targetPositions[targetPositionIndx];
        return (Math.abs(angles[LOWER] - getLowerArmAngle()) < Constants.TARGET_POSITION_TOLERANCE)
                && (Math.abs(angles[UPPER] - getUpperArmAngle()) < Constants.TARGET_POSITION_TOLERANCE)
                /* && (Math.abs(angles[BUCKET] - getBucketAngle()) < TARGET_POSITION_TOLERANCE) */;
    }

    @Override
    public void bumpTargetAngles(double lowerAngleDelta, double upperAngleDelta, double bucketAngleDelta) {
        targetPositions[targetPositionIndx][LOWER] += lowerAngleDelta;
        targetPositions[targetPositionIndx][UPPER] += upperAngleDelta;
        if (AUTO_POSITION_BUCKET != targetPositions[targetPositionIndx][BUCKET]) {
            targetPositions[targetPositionIndx][BUCKET] += bucketAngleDelta;
        }
        targetAngles[0] = targetPositions[targetPositionIndx][0];
        targetAngles[1] = targetPositions[targetPositionIndx][1];
        targetAngles[2] = targetPositions[targetPositionIndx][2];
    }

    public double uP, uI, lP, lI, bP, bI;

    @Override
    public void resetIntegral() {
        uI = 0;
        lI = 0;
        bI = 0;
    }

    @Override
    public void moveToTarget() {
        double
            period = time.get()-lastTime,
            lowerCoefficient = 20,
            upperCoefficient = 20,
            bucketCoefficient = 30,
            lkI = 3;
        lP = (targetAngles[0]-lowerArmAngle.get())/lowerCoefficient;
        lI += lP * period;
        lI = limit(.3, -.3, lI);
        uP = (targetAngles[1]-upperArmAngle.get())/upperCoefficient;
        uI += lkI * (uP * period);
        uI = limit(.2, -.1, uI);
        bP = (targetAngles[2]-bucketMotor.getSelectedSensorPosition())/bucketCoefficient;

        lastTime = time.get();
        //inputDriveLowArm(limit(.6, -1, (targetPositions[targetPositionIndx][0]-lowerArmAngle.get())/lowerCoefficient));
        //inputDriveUppArm(limit(.5, -.5, (targetPositions[targetPositionIndx][UPPER]-upperArmAngle.get())/upperCoefficient + constantErrorUpper));
        inputDriveLowArm(limit(.6, -1, (lP + lI)));
        inputDriveUppArm(limit(1, -.5, (uP + uI)));
        //inputDriveBucket(limit(.5, -.8, (bP))); 

        if (Robot.getOI().getStick().getRawButton(5)) {
            bucketMotor.setSelectedSensorPosition(0);
        }
        SmartDashboard.putString("DB/String 0", Double.toString(targetAngles[LOWER]));
        SmartDashboard.putString("DB/String 1", Double.toString(targetAngles[UPPER]));
        SmartDashboard.putString("DB/String 4", Double.toString(bP));
        SmartDashboard.putString("DB/String 5", Double.toString(lI));
        SmartDashboard.putString("DB/String 6", Integer.toString(bucketMotor.getSelectedSensorPosition()));
        SmartDashboard.putString("DB/String 7", Double.toString((targetAngles[LOWER]-lowerArmAngle.get())/lowerCoefficient));
        SmartDashboard.putString("DB/String 8", Boolean.toString(isAtTargetPosition()));
    }

    @Override
    public void stop() {
        // method to easily stop the motors
        armMotorLower.set(0.0);
        armMotorUpper.set(0.0);
    }

    public double limit(double upper, double lower, double input) {
        if (input > upper) input = upper;
        if (input < lower) input = lower;
        return input;
    }

    @Override
    public double[] getTargetPositionAngles(ArmPositions armPosition) {
        double[] angles = {targetPositions[armPosition.value][LOWER],
            targetPositions[armPosition.value][UPPER],
            targetPositions[armPosition.value][BUCKET]};
        return angles;
    }

    @Override
    public double[] getCurrentTargetAngles() {
        double[] angles = {targetAngles[0],
            targetAngles[1],
            targetAngles[2]};
        return angles;
   }

    @Override
    public void setTargetAngle(double[] targetAngles) {
        this.targetAngles[0] = targetAngles[0];
        this.targetAngles[1] = targetAngles[1];
        this.targetAngles[2] = targetAngles[2];

    }
}
