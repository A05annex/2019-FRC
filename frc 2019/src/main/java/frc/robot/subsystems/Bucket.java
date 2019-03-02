package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Bucket extends Subsystem {
    private final Solenoid grabberSolenoid = new Solenoid(RobotMap.grabber);
    VictorSP cargoMotor = new VictorSP(RobotMap.cargoMotor);
    public DigitalInput bucketSwitch = new DigitalInput(RobotMap.limitSwitch);

    public static int HATCH = 0;
    public static int BALL = 1;
    public static int EMPTY = 2;

    public int state = HATCH;

    @Override
    public void initDefaultCommand() {

    }

    public Bucket() {
        grabberSolenoid.set(true);
    }

    public void grabHatch() {
        grabberSolenoid.set(true);
    }

    public void releaseHatch() {
        grabberSolenoid.set(false);
    }
    
    public void collectBall() {
        cargoMotor.set(-1.0);
    }

    public void ejectBall() {
        cargoMotor.set(1.0);
    }

    public void stopWheels() {
        cargoMotor.set(0.0);
    }
}