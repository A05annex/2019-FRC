package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Bucket extends Subsystem {
    
	public Servo servo = new Servo(RobotMap.servo);
    public static final boolean BALL = true;
    public static final boolean HATCH = false;
    public static boolean stuff;

    @Override
    public void initDefaultCommand() {

    }

    public Bucket() {
        servo.set(0);
    }

    public static void deposit(boolean stuff){
        
    }
}