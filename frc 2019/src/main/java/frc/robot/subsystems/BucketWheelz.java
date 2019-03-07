package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.BallCollector;

public class BucketWheelz extends Subsystem {

    VictorSP cargoMotor = new VictorSP(RobotMap.cargoMotor);

    public BucketWheelz() {
        super();
    }


    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new BallCollector());

    }

    public void collect() {
        cargoMotor.set(1.0);
    }

    public void eject() {
        cargoMotor.set(-1.0);
    }

    public void stop() {
        cargoMotor.set(0.0);
    }
}