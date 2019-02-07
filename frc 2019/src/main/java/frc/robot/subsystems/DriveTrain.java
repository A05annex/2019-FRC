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

public class DriveTrain extends Subsystem{

    public AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
    //public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
    public WPI_TalonSRX
        rightMaster = new WPI_TalonSRX(RobotMap.rm1),
        rm2 = new WPI_TalonSRX(RobotMap.rm2),
        rm3 = new WPI_TalonSRX(RobotMap.rm3),
        leftMaster = new WPI_TalonSRX(RobotMap.lm1),
        lm2 = new WPI_TalonSRX(RobotMap.lm2),
        lm3 = new WPI_TalonSRX(RobotMap.lm3);
    
    public DriveTrain(){
        //constructs and configures all six drive motors
        rm2.follow(rightMaster);
        rm3.follow(rightMaster);
        lm2.follow(leftMaster);
        lm3.follow(leftMaster);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        rm2.setNeutralMode(NeutralMode.Brake);
        rm3.setNeutralMode(NeutralMode.Brake);
        leftMaster.setNeutralMode(NeutralMode.Brake);
        lm2.setNeutralMode(NeutralMode.Brake);
        lm3.setNeutralMode(NeutralMode.Brake);
        leftMaster.setInverted(true);
        lm2.setInverted(true);
        lm3.setInverted(true);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        ahrs.reset();
    }

    public void initDefaultCommand(){
        setDefaultCommand(new Teleop());
    }

    public void arcadeDrive(Joystick stick){
        //this is called from commands to drive the robot
        //rightMaster.set((stick.getRawAxis(1) + (stick.getRawAxis(2)/2))/3);
        //leftMaster.set((stick.getRawAxis(1) - (stick.getRawAxis(2)/2))/3);
    }

    public void inputDrive(double[] motorInput){
        leftMaster.set(motorInput[0]);
        rightMaster.set(motorInput[1]);
    }
    public void inputPDrive(double[] motorInput,double threshold){
        leftMaster.set(0);
        rightMaster.set(0);
    }

    public void setNeutralMode(NeutralMode mode){
        //method to easily set the neutral mode of all of the driveTrain motors
        rightMaster.setNeutralMode(mode);
        rm2.setNeutralMode(mode);
        rm3.setNeutralMode(mode);
        leftMaster.setNeutralMode(mode);
        lm2.setNeutralMode(mode);
        lm3.setNeutralMode(mode);
    }

    public void stop(){
        //method to easily stop the motors
        rightMaster.set(0);
        leftMaster.set(0);
    }
}