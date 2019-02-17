package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Teleop;

public class DriveTrain extends Subsystem {

    public AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
    public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
    public WPI_TalonSRX
            rightMaster = new WPI_TalonSRX(RobotMap.rm1),
            rm2 = new WPI_TalonSRX(RobotMap.rm2),
            rm3 = new WPI_TalonSRX(RobotMap.rm3),
            leftMaster = new WPI_TalonSRX(RobotMap.lm1),
            lm2 = new WPI_TalonSRX(RobotMap.lm2),
            lm3 = new WPI_TalonSRX(RobotMap.lm3);

    public DriveTrain() {
        //constructs and configures all six drive motors
        // restore everything to known factory default state
        rightMaster.configFactoryDefault();
        rm2.configFactoryDefault();
        rm3.configFactoryDefault();
        leftMaster.configFactoryDefault();
        lm2.configFactoryDefault();
        lm3.configFactoryDefault();
        // now configure them
        rm2.follow(rightMaster);
        rm3.follow(rightMaster);
        lm2.follow(leftMaster);
        lm3.follow(leftMaster);
        rm2.setInverted(InvertType.FollowMaster);
        rm3.setInverted(InvertType.FollowMaster);
        lm2.setInverted(InvertType.FollowMaster);
        lm3.setInverted(InvertType.FollowMaster);
        setNeutralMode(NeutralMode.Brake);
        leftMaster.setInverted(InvertType.InvertMotorOutput);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        ahrs.reset();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Teleop());
    }

    /**
     * Set the drive motor power based on an arcade control model of forward and turn speed.
     *
     * @param forward (double) forward speed in the range -1.0 to 1.0 (negative is
     *                backwards, positive is forward).
     * @param rotate  (double) rotation speed in the range -1.0 to 1.0 (negative is
     *                clockwise, positive is counter-clockwise).
     */
    public void setArcadePower(double forward, double rotate) {
        double max = Math.abs(forward) + Math.abs(rotate);
        double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
        rightMaster.set(scale * (forward + rotate));
        leftMaster.set(scale * (forward - rotate));
    }

    //theos thingy
    public void inputDrive(double[] motorInput) {
        leftMaster.set(motorInput[0]);
        rightMaster.set(motorInput[1]);
    }
    public void inputDriveDB(double motorleft,double motorright){
        leftMaster.set(motorleft);
        rightMaster.set(limitTo(motorright,-.1,.1));
    }
    public void inputDriveSG(double motorpow){
        leftMaster.set(motorpow);
        rightMaster.set(motorpow);
    }
    public void inputPDrive(double motorleft,double motorright,double threshold){
        leftMaster.set((motorright-threshold)/threshold);
        rightMaster.set((motorleft-threshold)/threshold);
    }

    public void setNeutralMode(NeutralMode mode) {
        //method to easily set the neutral mode of all of the driveTrain motors
        rightMaster.setNeutralMode(mode);
        rm2.setNeutralMode(mode);
        rm3.setNeutralMode(mode);
        leftMaster.setNeutralMode(mode);
        lm2.setNeutralMode(mode);
        lm3.setNeutralMode(mode);
    }

    public void stop() {
        //method to easily stop the motors
        rightMaster.set(0);
        leftMaster.set(0);
    }
    public double limitTo(double value,double lowerlim,double upperlim){
        if(value > upperlim){
            value=upperlim;
        }
        if(value < lowerlim){
            value=lowerlim;
        }
        return(value);
    }
}