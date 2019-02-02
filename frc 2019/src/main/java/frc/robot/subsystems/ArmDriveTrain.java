package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;

public class ArmDriveTrain extends Subsystem{
    //construction of arm motors
    public WPI_TalonSRX
        armMotorLower = new WPI_TalonSRX(RobotMap.arm1),
        armMotorUpper = new WPI_TalonSRX(RobotMap.arm2);
    static double
        armLengthUpper=6.0,
        armLengthLower=8.0;

    
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
            armMotorLower.set(.5);

        }
        else if(stick.getRawButton(6)){
            armMotorLower.set(-.5);
        }
        else{
            armMotorLower.set(0);
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
    }
}