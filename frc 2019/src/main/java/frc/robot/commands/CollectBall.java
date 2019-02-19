package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class CollectBall extends Command {

    public CollectBall() {
        super();
        requires(Robot.bucket);

    }

    @Override
    protected void execute() {
        new SetArmTarget(ArmPositions.PICKUP_FROM_FLOOR);
        Robot.bucket.collectBall();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}