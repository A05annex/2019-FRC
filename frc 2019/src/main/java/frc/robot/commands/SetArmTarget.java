package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class SetArmTarget extends Command {

    public SetArmTarget(ArmPositions targetPosition) {
        super();
        Robot.armDriveTrain.setTargetPosition(targetPosition);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

