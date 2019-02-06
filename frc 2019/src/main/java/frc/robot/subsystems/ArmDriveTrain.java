package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;

public class ArmDriveTrain extends Subsystem{
    
    public double
        angle1,
        angle2;

    public AnalogPotentiometer
        baseAngle = new AnalogPotentiometer(2, -360, 334.1),
        secondAngle = new AnalogPotentiometer(3, -360, 360);

    public WPI_TalonSRX
        armMotorLower = new WPI_TalonSRX(RobotMap.arm1),
        armMotorUpper = new WPI_TalonSRX(RobotMap.arm2);
    
    public ArmDriveTrain(){
        //constructs and configures all six drive motors
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
    }

    public void initDefaultCommand(){
        setDefaultCommand(new ArmTeleop());
    }
    public void stickDrive(Joystick stick){
        if(stick.getRawButton(5)){
            armMotorLower.set(.3);
        }
        else if(stick.getRawButton(6)){
            armMotorLower.set(-.3);
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

    public void inputDrive(double[] motorInput){
    }

    public void setNeutralMode(NeutralMode mode){
        //method to easily set the neutral mode of all of the driveTrain motors
        armMotorLower.setNeutralMode(mode);
        armMotorUpper.setNeutralMode(mode);
    }

    public void stop(){
        //method to easily stop the motors
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