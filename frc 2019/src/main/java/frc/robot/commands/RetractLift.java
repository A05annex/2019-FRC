package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractLift extends Command {

    private final double duration;
    private final Timer time = new Timer();

    public RetractLift(double duration) {
        super();
        requires(Robot.lift);
        this.duration = duration;
    }

    @Override
    protected void initialize() {
        time.reset();
        time.start();
    }

    @Override
    protected void execute() {
        Robot.lift.retract_lifters();
    }

    @Override
    protected boolean isFinished() {
        return time.get() > duration;
    }

    @Override
    protected void end() {
        time.stop();
        time.reset();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
