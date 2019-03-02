package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;

public class BumpTurnAtSpeedGain extends Command {

    final double inc;

    public BumpTurnAtSpeedGain(double inc) {
        super();
        this.inc = inc;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Constants.DRIVE_TURN_AT_SPEED_GAIN += inc;
        if (Constants.DRIVE_TURN_AT_SPEED_GAIN > 0.5) {
            Constants.DRIVE_TURN_AT_SPEED_GAIN = 0.5;
        } else if (Constants.DRIVE_TURN_AT_SPEED_GAIN < 0.05) {
            Constants.DRIVE_TURN_AT_SPEED_GAIN = 0.05;
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
