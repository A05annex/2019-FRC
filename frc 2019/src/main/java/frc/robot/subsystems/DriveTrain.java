package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Teleop;

public class DriveTrain extends Subsystem{

    public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
    public WPI_TalonSRX
        rightMotor = new WPI_TalonSRX(RobotMap.rm1),
        rm2 = new WPI_TalonSRX(RobotMap.rm2),
        rm3 = new WPI_TalonSRX(RobotMap.rm3),
        leftMotor = new WPI_TalonSRX(RobotMap.lm1),
        lm2 = new WPI_TalonSRX(RobotMap.lm2),
        lm3 = new WPI_TalonSRX(RobotMap.lm3);
    
    public DriveTrain(){
        //constructs and configures all six drive motors
        rm2.follow(rightMotor);
        rm3.follow(rightMotor);
        lm2.follow(leftMotor);
        lm3.follow(leftMotor);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        rm2.setNeutralMode(NeutralMode.Brake);
        rm3.setNeutralMode(NeutralMode.Brake);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        lm2.setNeutralMode(NeutralMode.Brake);
        lm3.setNeutralMode(NeutralMode.Brake);
        leftMotor.setInverted(true);
        lm2.setInverted(true);
        lm3.setInverted(true);
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }

    public void initDefaultCommand(){
        setDefaultCommand(new Teleop());
    }

    public void arcadeDrive(Joystick stick){
        //this is called from commands to drive the robot
        rightMotor.set(stick.getRawAxis(1) + (stick.getRawAxis(2)/2));
        leftMotor.set(stick.getRawAxis(1) - (stick.getRawAxis(2)/2));
    }

    public void setNeutralMode(NeutralMode mode){
        //method to easily set the neutral mode of all of the driveTrain motors
        rightMotor.setNeutralMode(mode);
        rm2.setNeutralMode(mode);
        rm3.setNeutralMode(mode);
        leftMotor.setNeutralMode(mode);
        lm2.setNeutralMode(mode);
        lm3.setNeutralMode(mode);
    }

    public void stop(){
        //method to easily stop the motors
        rightMotor.set(0);
        leftMotor.set(0);
    }
}