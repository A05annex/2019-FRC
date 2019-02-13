package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
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
        angle2,
        angle3,
        arm1multiplier = 30,
        arm2multiplier = 30;

    public AnalogPotentiometer
        baseAngle = new AnalogPotentiometer(2, -360, 327),
        secondAngle = new AnalogPotentiometer(3, -360, 162);

    //construction of arm motors
    public WPI_TalonSRX
        armMotorLower = new WPI_TalonSRX(RobotMap.arm1),
        armMotorUpper = new WPI_TalonSRX(RobotMap.arm2),
        bucket = new WPI_TalonSRX(RobotMap.bucket);
    
    // TODO: once the arms and done and the positions of the potentiometers are fixed, manually
    // rotate the arms and track the potentiometer values at the limits of motion. These become
    // the constraints for arm movement - i.e. if you try to move the arm beyond these values
    // you will run into the frame or some other hard stop that could damage the robot/arm - don't
    // let that happen !!
    private double lowerArmMin = 30.0;
    private double lowerArmMax = 130.0;
    private double upperArmMin = 40.0;
    private double upperArmMax = 140.0;
    private double armStopBuffer = 5.0;     // The degrees before the hard stop that you should
                                            // cut power to 0.0
    private double armCreepBuffer = 15.0;   // The distance before the hard stop that you
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
            arm1 = 39.5,
            arm2 = 41.25,
            xdifference = 26;
        angle1 = Math.toDegrees(Math.atan(height/xdifference) + Math.acos((arm1*arm1 + height*height + xdifference*xdifference - arm2*arm2) / (2 * arm1 * Math.sqrt(xdifference*xdifference + height*height))));
        angle2 = Math.toDegrees(Math.acos((arm1*arm1 + arm2*arm2 - xdifference*xdifference - height*height) / (2 * arm1 * arm2)));
        angle3 = angle1 + angle2;
        SmartDashboard.putString("DB/String 6", Double.toString(angle1));
        SmartDashboard.putString("DB/String 7", Double.toString(angle2));
        SmartDashboard.putString("DB/String 8", Double.toString(height));
    }

    public void moveToHeight(){
        armMotorLower.set(limitTo((angle1 - baseAngle.get())/arm1multiplier, -.3, .7));
        armMotorUpper.set(limitTo((secondAngle.get() - angle2)/arm2multiplier, -.5, .5));
    }

    public void lockPosition(){
        armMotorLower.set(0);
        armMotorUpper.set(0);
    }

    public double limitTo(double value, double lowerLimit, double upperLimit){
        if(value > upperLimit){
            value = upperLimit;
        }
        if(value < lowerLimit){
            value = lowerLimit;
        }
        return value;
    }
}