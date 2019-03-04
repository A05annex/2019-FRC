package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.Teleop;

public class DriveTrainPractice extends Subsystem implements IUseDriveTrain {

    public AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
//    public Solenoid shifter = Constants.ENABLE_DRIVE_SHIFT ? new Solenoid(RobotMap.shifter) : null;
    public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rm1);
    public WPI_VictorSPX rm2 = new WPI_VictorSPX(RobotMap.rm2);
    public WPI_VictorSPX rm3 = new WPI_VictorSPX(RobotMap.rm3);
    public WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.lm1);
    public WPI_TalonSRX lm2 = new WPI_TalonSRX(RobotMap.lm2);
    public WPI_TalonSRX lm3 = new WPI_TalonSRX(RobotMap.lm3);

    public DriveTrainPractice() {
        // constructs and configures all six drive motors
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
        rightMaster.setInverted(InvertType.InvertMotorOutput);
        ahrs.reset();

        //setting ramp rate for smoother acceleration
        //not tested as of 2/22/19
//        rightMaster.configOpenloopRamp(Constants.SECS_FROM_NEUTRAL_TO_FULL);
//        leftMaster.configOpenloopRamp(Constants.SECS_FROM_NEUTRAL_TO_FULL);
    }

    @Override
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
    @Override
    public void setArcadePower(double forward, double rotate) {
        double max = Math.abs(forward) + Math.abs(Constants.DRIVE_TURN_BIAS) + Math.abs(rotate);
        double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
        rightMaster.set(scale * (forward + (rotate)));
        leftMaster.set(scale * (forward - (rotate)));
    }

    @Override
    public void upShift() {
//        if (null != shifter) {
//            shifter.set(true);
//        }
    }

    @Override
    public void downShift() {
//        if (null != shifter) {
//            shifter.set(false);
//        }
    }

    //theos thingy
    @Override
    public void inputDrive(double[] motorInput) {
        leftMaster.set(motorInput[0]);
        rightMaster.set(motorInput[1]);
    }

    @Override
    public void inputDriveDB(double motorleft, double motorright) {
        leftMaster.set(motorleft);
        rightMaster.set(limitTo(motorright, -.1, .1));
    }

    @Override
    public void inputDriveSG(double motorpow) {
        leftMaster.set(motorpow);
        rightMaster.set(motorpow);
    }

    @Override
    public void inputPDrive(double motorleft, double motorright, double threshold) {
        leftMaster.set((motorright - threshold) / threshold);
        rightMaster.set((motorleft - threshold) / threshold);
    }

    @Override
    public void setNeutralMode(NeutralMode mode) {
        //method to easily set the neutral mode of all of the driveTrain motors
        rightMaster.setNeutralMode(mode);
        rm2.setNeutralMode(mode);
        rm3.setNeutralMode(mode);
        leftMaster.setNeutralMode(mode);
        lm2.setNeutralMode(mode);
        lm3.setNeutralMode(mode);
    }

    @Override
    public void stop() {
        //method to easily stop the motors
        rightMaster.set(0);
        leftMaster.set(0);
    }

    private double limitTo(double value, double lowerlim, double upperlim) {
        if (value > upperlim) {
            value = upperlim;
        }
        if (value < lowerlim) {
            value = lowerlim;
        }
        return (value);
    }

}