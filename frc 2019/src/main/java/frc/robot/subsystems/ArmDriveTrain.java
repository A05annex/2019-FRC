package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArmTeleop;

public class ArmDriveTrain extends Subsystem{
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
    }
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