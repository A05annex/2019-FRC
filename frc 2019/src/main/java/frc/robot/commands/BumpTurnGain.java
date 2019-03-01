package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;

public class BumpTurnGain extends Command {

    final double inc;

    public BumpTurnGain(double inc) {
        super();
        this.inc = inc;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Constants.DRIVE_TURN_GAIN += inc;
        if (Constants.DRIVE_TURN_GAIN > 1.0) {
            Constants.DRIVE_TURN_GAIN = 1.0;
        } else if (Constants.DRIVE_TURN_GAIN < 0.1) {
            Constants.DRIVE_TURN_GAIN = 0.1;
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
