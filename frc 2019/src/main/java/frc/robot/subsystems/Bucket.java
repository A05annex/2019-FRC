package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Bucket extends Subsystem {
    public Servo servo = new Servo(RobotMap.servo);
    VictorSP cargoMotor = new VictorSP(RobotMap.cargoMotor);

    @Override
    public void initDefaultCommand() {

    }

    public Bucket() {
        super();
        servo.set(0);
    }

    public void collectBall() {
        cargoMotor.set(1.0);
    }

    public void ejectBall() {
        cargoMotor.set(-1.0);
    }

    public void stopWheels() {
        cargoMotor.set(0.0);
    }
}