package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Grabber extends Subsystem {
    private final Solenoid grabberSolenoid = new Solenoid(RobotMap.grabber);

    public Grabber() {
        super();
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void grabHatch() {
        grabberSolenoid.set(false);
    }

    public void releaseHatch() {
        grabberSolenoid.set(true);
    }

}
