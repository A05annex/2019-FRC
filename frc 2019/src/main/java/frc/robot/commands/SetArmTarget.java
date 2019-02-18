package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class SetArmTarget extends Command {

    ArmPositions targetPosition;
    boolean finished = false;

    public SetArmTarget(ArmPositions targetPosition) {
        super();
        this.targetPosition = targetPosition;
    }


    @Override
    protected void execute() {
        super.execute();
        Robot.armDriveTrain.resetIntegral();
        Robot.armDriveTrain.setTargetPosition(targetPosition);
        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}

