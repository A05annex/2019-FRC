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

    /**
     * Lifts the robot lift_robot. Only needs to be applied for a short time to move the switching piston.
     */
    public void grabHatch() {
        grabberSolenoid.set(true);
    }

    /**
     * Lowers the robot. Only needs to be applied for a short time to move the switching piston.
     */
    public void releaseHatch() {
        grabberSolenoid.set(false);
    }

}
