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
    public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
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

    // Jason: I would move this to the command. It is a mapping functions and
    // different drive commands would have different matting functions.
    public void arcadeDrive(Joystick stick){
        //this is called from commands to drive the robot
        double forward = stick.getRawAxis(1)/3.0;
        double rotate = stick.getRawAxis(2)/6.0;
        setArcadePower(forward, rotate);
    }

    /**
     * Set the drive motor power based on an arcade control model of forward and turn speed.
     * @param forward (double) forward speed in the range -1.0 to 1.0 (negative is
     *        backwards, positive is forward).
     * @param rotate (double) rotation speed in the range -1.0 to 1.0 (negative is
     *        clockwise, positive is counter-clockwise).
     */
    public void setArcadePower(double forward, double rotate) {
        double max = Math.abs(forward) + Math.abs(rotate);
        double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
        rightMaster.set(scale * (forward + rotate));
        leftMaster.set(scale * (forward - rotate));
    }

    //theos thingy
    public void inputDrive(double[] motorInput){
        leftMaster.set(motorInput[0]);
        rightMaster.set(motorInput[1]);
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