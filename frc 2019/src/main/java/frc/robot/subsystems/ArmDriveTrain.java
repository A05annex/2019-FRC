package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;

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
        setDefaultCommand(new ArmTeleop());
    }

    public double getLowerArmPosition() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return lowerArmAngle.get();
    }

    public double getUpperArmPosition() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return upperArmAngle.get();
    }

    public void inputDriveLowArm(double lowerArmPower) {
        // TODO: check lower arm power direction, test against arm position and/or limit
        // switch and set to 0 if we have hit the constraint for that direction
        if (lowerArmPower < 0.0) {
            if (getLowerArmPosition() < (lowerArmMin + armCreepBuffer)) {
                lowerArmPower = (getLowerArmPosition() < (lowerArmMin + armStopBuffer)) ? 0.0 : -armCreepPower;
            }
        } else if (lowerArmPower > 0.0) {
            if (getLowerArmPosition() > (lowerArmMax - armCreepBuffer)) {
                lowerArmPower = (getLowerArmPosition() > (lowerArmMax - armStopBuffer)) ? 0.0 : armCreepPower;
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
    public void inputDriveUppArm(double upperArmPower) {
        // TODO: check upper arm power direction, test against arm position and/or limit
        // switch and set to 0 if we have hit the constraint for that direction
        if (upperArmPower < 0.0) {
            if (getLowerArmPosition() < (upperArmMin + armCreepBuffer)) {
                upperArmPower = (getLowerArmPosition() < (upperArmMin + armStopBuffer)) ? 0.0 : -armCreepPower;
            }
        } else if (upperArmPower > 0.0) {
            if (getUpperArmPosition() > (upperArmMax - armCreepBuffer)) {
                upperArmPower = (getUpperArmPosition() > (upperArmMax - armStopBuffer)) ? 0.0 : armCreepPower;
            }
        }
        armMotorUpper.set(upperArmPower);
    }

    public void stop() {
        // method to easily stop the motors
        armMotorLower.set(0.0);
        armMotorUpper.set(0.0);
    }

    // buncha math
    public void setHeight(int height) {
        double arm1 = 39.25, arm2 = 34.5, xdifference = 26;
        angle1 = Math.toDegrees(Math.atan(height / xdifference)
                + Math.acos((arm1 * arm1 + height * height + xdifference * xdifference - arm2 * arm2)
                / (2 * arm1 * Math.sqrt(xdifference * xdifference + height * height))));
        angle2 = Math.toDegrees(Math
                .acos((arm1 * arm1 + arm2 * arm2 - xdifference * xdifference - height * height) / (2 * arm1 * arm2)));
        SmartDashboard.putString("DB/String 6", Double.toString(angle1));
        SmartDashboard.putString("DB/String 7", Double.toString(angle2));
    }

    public void moveToHeight() {

    }

    public void lockPosition() {
        armMotorLower.set(0);
        armMotorUpper.set(0);
    }
}