package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;

public class BumpSensitivity extends Command {

    final double inc;

    public BumpSensitivity(double inc) {
        super();
        this.inc = inc;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Constants.DRIVE_SENSITIVITY += inc;
        if (Constants.DRIVE_SENSITIVITY > 3.0) {
            Constants.DRIVE_SENSITIVITY = 3.0;
        } else if (Constants.DRIVE_SENSITIVITY < 1.0) {
            Constants.DRIVE_SENSITIVITY = 1.0;
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
