package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Teleop;

public class ArmDriveTrain extends Subsystem{
    public WPI_TalonSRX
        armMotorLower = new WPI_TalonSRX(0),
        armMotorUpper = new WPI_TalonSRX(1);
    
    public ArmDriveTrain(){
        //constructs and configures all six drive motors
        armMotorLower.setNeutralMode(NeutralMode.Brake);
        armMotorUpper.setNeutralMode(NeutralMode.Brake);
    }

    public void initDefaultCommand(){
        setDefaultCommand(new Teleop());
    }
    public void stickDrive(Joystick stick){
        armMotorLower.set(-(stick.getRawAxis(1)/10));
        armMotorUpper.set(stick.getRawAxis(0)/10);
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
}