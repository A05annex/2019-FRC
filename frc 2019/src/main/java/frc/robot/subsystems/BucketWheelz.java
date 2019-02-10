package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class BucketWheelz extends Subsystem {

    public Servo wheelsServo = new Servo(RobotMap.servo2);

	public BucketWheelz() {
	}

	@Override
    public void initDefaultCommand() {
    
    }

}