package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class BucketWheelz extends Subsystem {

    VictorSP cargoMotor = new VictorSP(RobotMap.cargoMotor);

    public BucketWheelz() {
        super();
    }


    @Override
    public void initDefaultCommand() {
    }

    public void collect() {
        cargoMotor.set(-1.0);
    }

    public void eject() {
        cargoMotor.set(1.0);
    }

    public void stop() {
        cargoMotor.set(0.0);
    }
}