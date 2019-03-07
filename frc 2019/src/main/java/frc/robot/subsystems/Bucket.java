package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Bucket extends Subsystem {
    //private final Solenoid grabberSolenoid = new Solenoid(RobotMap.grabber);
    public Servo servo = new Servo(RobotMap.servo);
    //VictorSP cargoMotor = new VictorSP(RobotMap.cargoMotor);
    

    @Override
    public void initDefaultCommand() {

    }

    public Bucket() {
        servo.set(0);
    }

    /*public void grabHatch() {
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

    public void stop() {
        cargoMotor.set(0.0);
    }*/
}