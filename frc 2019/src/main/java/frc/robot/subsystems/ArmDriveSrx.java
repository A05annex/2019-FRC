package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;

public class ArmDriveSrx extends Subsystem implements IUseArm {

    // construction of arm motors
    public WPI_TalonSRX armMotorLower = new WPI_TalonSRX(RobotMap.arm1);
    public WPI_TalonSRX armMotorUpper = new WPI_TalonSRX(RobotMap.arm2);

    public ArmDriveSrx() {
        super();
        // Initialize to a known configuration
        armMotorLower.configFactoryDefault();
        armMotorUpper.configFactoryDefault();
        // configure the lower arm drive motor
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorLower.configSelectedFeedbackSensor(FeedbackDevice.Analog);

        // configure the upper arm drive motor
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setInverted(true);
        armMotorLower.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    }

    // default command for the subsystem, this one being teleoperation for the arm
    public void initDefaultCommand() {
        setDefaultCommand(new ArmTeleop());
    }

    @Override
    public double getLowerArmAngle() {
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return armMotorLower.getSelectedSensorPosition();
    }

    /**
     * The position of the upper arm in the range <tt>upperArmMin</tt> to
     * <tt>upperArmMax</tt>
     *
     * @return (double) The position of the upper arm.
     */
    public double getUpperArmAngle() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return armMotorLower.getSelectedSensorPosition();
    }

    @Override
    public double getBucketAngle() {
        return 0;
    }

    @Override
    public void inputDriveLowArm(double lowerArmPower) {

    }

    @Override
    public void inputDriveUppArm(double upperArmPower) {

    }

    @Override
    public void inputDriveBucket(double bucketPower) {

    }

    @Override
    public void setTargetPosition(ArmPositions armPosition) {

    }

    @Override
    public ArmPositions getTargetPosition() {
        return null;
    }

    @Override
    public boolean isAtTargetPosition() {
        return false;
    }

    @Override
    public void bumpTargetPosition(double lowerAngleDelta, double upperAngleDelta, double bucketAngleDelta) {

    }

    @Override
    public void resetIntegral() {

    }

    @Override
    public void moveToTarget() {

    }

    public void stop() {
        // method to easily stop the motors
        armMotorLower.set(0.0);
        armMotorUpper.set(0.0);
    }
}
