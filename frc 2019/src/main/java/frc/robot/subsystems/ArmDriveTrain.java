package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;

/**
 * The robot arm drive subsystem. This subsystem is controlling the lower arm motor
 * and the upper arm motor only. There are position potentiometers for the lower and
 * upper arms controlled by those motors. The controll of the head/bucket is
 * independent of the arm.
 *
 * Roy's note: I'm thinking the arm drive subsystem knows about the potentiometers
 * and the reporting of arm position, and that the control happens in the commands
 * initially - some control method may move to the arm once we really know how to
 * control them.
 */
public class ArmDriveTrain extends Subsystem{

    public double
        angle1,
        angle2;

    public AnalogPotentiometer
        baseAngle = new AnalogPotentiometer(2, -360, 334.1),
        secondAngle = new AnalogPotentiometer(3, -360, 360);

    //construction of arm motors
    public WPI_TalonSRX
        armMotorLower = new WPI_TalonSRX(RobotMap.arm1),
        armMotorUpper = new WPI_TalonSRX(RobotMap.arm2);
    // TODO: once the arms and done and the positions of the potentiometers are fixed, manually
    // rotate the arms and track the potentiometer values at the limits of motion. These become
    // the constraints for arm movement - i.e. if you try to move the arm beyond these values
    // you will run into the frame or some other hard stop that could damage the robot/arm - don't
    // let that happen !!
    private double lowerArmMin = 0.0;
    private double lowerArmMax = 5.0;
    private double upperArmMin = 0.0;
    private double upperArmMax = 5.0;
    private double armStopBuffer = 0.25;    // The distance before the hard stop that you should
                                            // cut power to 0.0
    private double armCreepBuffer = 0.50;   // The distance before the hard stop that you
                                            // should cut power to creep power
    private double armCreepPower = 0.1;     // The maximum power in the creep zone


    public ArmDriveTrain(){
        //configures both drive motors for the motors
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
    }

    //default command for the subsystem, this one being teleoperation for the arm
    public void initDefaultCommand(){
        setDefaultCommand(new ArmTeleop());
    }

    /**
     * The position of the lower arm in the range <tt>lowerArmMin</tt> to <tt>lowerArmMax</tt>
     * @return (double) The position of the lower arm.
     */
    public double getLowerArmPosition() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return 2.5;
    }

    /**
     * The position of the upper arm in the range <tt>upperArmMin</tt> to <tt>upperArmMax</tt>
     * @return (double) The position of the upper arm.
     */
    public double getUpperArmPosition() {
        // TODO: Map from the potentiometer to some position. This could just be the
        // potentiometer value, or it could be mapped to a 'more meaningful' value
        // like degrees from horizontal.
        return 2.5;
    }

    /**
     * Set the arm motor powers for the lower and upper arms.
     * @param lowerArmPower (double) The power to the lower arm in the range -1 to 1.
     * @param upperArmPower (double) The power to the upper arm in the range -1 to 1.
     */
    public void setArmPower( double lowerArmPower, double upperArmPower ) {
        // TODO: check lower arm power direction, test against arm position and/or limit
        // switch and set to 0 if we have hit the constraint for that direction
        if (lowerArmPower < 0.0) {
            if (getLowerArmPosition() < (lowerArmMin + armCreepBuffer)) {
                lowerArmPower = (getLowerArmPosition() < (lowerArmMin + armStopBuffer)) ?
                    0.0 : armCreepPower;
            }
        } else if (lowerArmPower > 0.0) {
            if (getLowerArmPosition() > (lowerArmMax - armCreepBuffer)) {
                lowerArmPower = (getLowerArmPosition() > (lowerArmMax - armStopBuffer)) ?
                    0.0 : armCreepPower;
            }
        }
        // TODO: check upper arm power direction, test against arm position and/or limit
        // switch and set to 0 if we have hit the constraint for that direction
        if (upperArmPower < 0.0) {
            if (getLowerArmPosition() < (upperArmMin + armCreepBuffer)) {
                upperArmPower = (getLowerArmPosition() < (upperArmMin + armStopBuffer)) ?
                    0.0 : armCreepPower;
            }
        } else if (upperArmPower > 0.0) {
            if (getUpperArmPosition() > (upperArmMax - armCreepBuffer)) {
                upperArmPower = (getUpperArmPosition() > (upperArmMax - armStopBuffer)) ?
                    0.0 : armCreepPower;
            }
        }
        // now that we have conditioned the power to the constraints, set the power
        armMotorLower.set(lowerArmPower);
        armMotorUpper.set(upperArmPower);
    }
    //methods to drive the arms independently, if necessary
    public void inputDriveLowArm(double motorInput){
        armMotorLower.set(motorInput);
    }
    public void inputDriveUppArm(double motorInput){
        armMotorUpper.set(motorInput);
    }
    public void setNeutralMode(NeutralMode mode){
        //method to easily set the neutral mode of all of the driveTrain motors
        armMotorLower.setNeutralMode(mode);
        armMotorUpper.setNeutralMode(mode);
    }

    public void stop(){
        //method to easily stop the motors
        armMotorLower.set(0.0);
        armMotorUpper.set(0.0);
    }
    //buncha math
    public void setHeight(int height){
        double
            arm1 = 39.25,
            arm2 = 34.5,
            xdifference = 26;
        angle1 = Math.toDegrees(Math.atan(height/xdifference) + Math.acos((arm1*arm1 + height*height + xdifference*xdifference - arm2*arm2) / (2 * arm1 * Math.sqrt(xdifference*xdifference + height*height))));
        angle2 = Math.toDegrees(Math.acos((arm1*arm1 + arm2*arm2 - xdifference*xdifference - height*height) / (2 * arm1 * arm2)));
        SmartDashboard.putString("DB/String 6", Double.toString(angle1));
        SmartDashboard.putString("DB/String 7", Double.toString(angle2));
    }

    public void moveToHeight(){

    }

    public void lockPosition(){
        armMotorLower.set(0);
        armMotorUpper.set(0);
    }
}