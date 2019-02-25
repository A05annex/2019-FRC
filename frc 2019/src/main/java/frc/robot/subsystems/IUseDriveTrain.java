package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface IUseDriveTrain {

    public void initDefaultCommand();

    /**
     * Set the drive motor power based on an arcade control model of forward and turn speed.
     *
     * @param forward (double) forward speed in the range -1.0 to 1.0 (negative is
     *                backwards, positive is forward).
     * @param rotate  (double) rotation speed in the range -1.0 to 1.0 (negative is
     *                clockwise, positive is counter-clockwise).
     */
    public void setArcadePower(double forward, double rotate);

    public void upShift();

    public void downShift();

    //theos thingy
    public void inputDrive(double[] motorInput);

    public void inputDriveDB(double motorleft, double motorright);

    public void inputDriveSG(double motorpow);

    public void inputPDrive(double motorleft, double motorright, double threshold);

    public void setNeutralMode(NeutralMode mode);

    public void stop();

}
