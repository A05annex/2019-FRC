package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class ArmDriveTrain extends Subsystem{
    public AnalogPotentiometer
        baseAngle = new AnalogPotentiometer(2, -360, 334.1),
        secondAngle = new AnalogPotentiometer(3, -360, 360);

    //construction of arm motors
    public WPI_TalonSRX
        armMotorLower = new WPI_TalonSRX(RobotMap.arm1),
        armMotorUpper = new WPI_TalonSRX(RobotMap.arm2);
    
    public ArmDriveTrain(){
        //configures both drive motors for the motors
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
    }
    //default command for the subsystem, this one being teleoperation for the arm
    public void initDefaultCommand(){
        setDefaultCommand(new ArmTeleop());
    }
    //method for driving arms with stick input
    public void stickDrive(Joystick stick){
        if(stick.getRawButton(5)){
          
        }
        else if(stick.getRawButton(6)){
            armMotorLower.set(-.5);
        }
        else{
            armMotorLower.set(0);
        }

        if(stick.getRawButton(7)){
            armMotorUpper.set(.3);
        }
        else if(stick.getRawButton(8)){
            armMotorUpper.set(-.3);
        }
        else{
            armMotorUpper.set(0);
        }
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
    public void setHeight(int height){
        double
            arm1 = 39.25,
            arm2 = 34.5,
            xdifference = 26;
        double angle1 = Math.toDegrees(Math.atan(height/xdifference) + Math.acos((arm1*arm1 + height*height + xdifference*xdifference - arm2*arm2) / (2 * arm1 * Math.sqrt(xdifference*xdifference + height*height))));
        double angle2 = Math.toDegrees(Math.acos((arm1*arm1 + arm2*arm2 - xdifference*xdifference - height*height) / (2 * arm1 * arm2)));
        SmartDashboard.putString("DB/String 6", Double.toString(angle1));
        SmartDashboard.putString("DB/String 7", Double.toString(angle2));
    }
}