package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractLift extends Command {

    boolean isFinished = false;

    public RetractLift() {
        super();
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        isFinished = false;
    }

    @Override
    protected void execute() {
        Robot.lift.retract_lifters();
        isFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }
}
