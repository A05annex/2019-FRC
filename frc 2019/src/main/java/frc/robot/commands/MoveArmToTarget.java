package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class MoveArmToTarget extends Command {

    public MoveArmToTarget() {
        super();
        requires((Subsystem) Robot.armDriveTrain);
        // this is the only thing that ever controls the arm, it cannot be interrupted.
        setInterruptible(false);
    }

    @Override
    protected void execute() {
        Robot.armDriveTrain.moveToTarget();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
