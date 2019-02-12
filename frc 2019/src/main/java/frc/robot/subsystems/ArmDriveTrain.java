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
        armMotorLower.set(0);
        armMotorUpper.set(0);
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