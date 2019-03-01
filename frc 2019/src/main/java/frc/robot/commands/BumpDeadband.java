package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;

public class BumpDeadband extends Command {

    final double inc;

    public BumpDeadband(double inc) {
        super();
        this.inc = inc;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Constants.DRIVE_DEADBAND += inc;
        if (Constants.DRIVE_DEADBAND > 0.1) {
            Constants.DRIVE_DEADBAND = 0.1;
        } else if (Constants.DRIVE_DEADBAND < 0.0) {
            Constants.DRIVE_DEADBAND = 0.0;
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }
}
