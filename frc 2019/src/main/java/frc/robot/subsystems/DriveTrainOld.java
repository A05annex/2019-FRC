package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotMapORG;
import frc.robot.commands.Teleop;

public class DriveTrainOld extends Subsystem {

    public AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
    public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
    public WPI_TalonSRX
            rightMaster = new WPI_TalonSRX(RobotMapORG.rm1),
            leftMaster = new WPI_TalonSRX(RobotMapORG.lm1),
            lm2 = new WPI_TalonSRX(RobotMapORG.lm2),
            lm3 = new WPI_TalonSRX(RobotMapORG.lm3);
    public VictorSP
        rm2 = new VictorSP(RobotMapORG.rm2),
        rm3 = new VictorSP(RobotMapORG.rm3);

    public DriveTrainOld() {
        //constructs and configures all six drive motors
        // restore everything to known factory default state
        rightMaster.configFactoryDefault();
        leftMaster.configFactoryDefault();
        lm2.configFactoryDefault();
        lm3.configFactoryDefault();
        // now configure them
        lm2.follow(leftMaster);
        lm3.follow(leftMaster);
        rm2.setInverted(true);
        rm3.setInverted(true);
        rm2.set(0);
        rm3.set(0);
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
    private void setOldMotors(){
        System.out.println(rightMaster.getMotorOutputPercent());
    }
    public void setArcadePower(double forward, double rotate) {
        double max = Math.abs(forward) + Math.abs(rotate);
        double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
        rightMaster.set(scale * (forward + rotate));
        setOldMotors();
        leftMaster.set(scale * (forward - rotate));
    }

    //theos thingy
    public void inputDrive(double[] motorInput) {
        leftMaster.set(motorInput[0]);
        rightMaster.set(motorInput[1]);
        setOldMotors();
    }
    public void inputDriveDB(double motorleft,double motorright){
        leftMaster.set(motorleft);
        rightMaster.set(motorright);
        setOldMotors();
    }
    public void inputDriveSG(double motorpow){
        leftMaster.set(motorpow);
        rightMaster.set(motorpow);
        setOldMotors();
    }
    public void inputPDrive(double motorleft,double motorright,double threshold){
        leftMaster.set((motorright-threshold)/threshold);
        rightMaster.set((motorleft-threshold)/threshold);
        setOldMotors();
    }

    public void setNeutralMode(NeutralMode mode) {
        //method to easily set the neutral mode of all of the driveTrain motors
        rightMaster.setNeutralMode(mode);
        setOldMotors();
        leftMaster.setNeutralMode(mode);
        lm2.setNeutralMode(mode);
        lm3.setNeutralMode(mode);
    }

    public void stop() {
        //method to easily stop the motors
        rightMaster.set(0);
        setOldMotors();
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