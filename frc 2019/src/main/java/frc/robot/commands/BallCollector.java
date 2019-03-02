package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This subsystem controls the cargo (ball) collection wheels to either grab the ball into the bucket,
 * {@link #GRAB_BALL}; or eject the ball onto the rocket or cargo ship, {@link #EJECT_BALL}.
 */
public class BallCollector extends Command {

    public static final boolean GRAB_BALL = true;
    public static final boolean EJECT_BALL = false;

    private final boolean collect;

    public BallCollector(boolean collect) {
        super();
        requires(Robot.bucket);
        this.collect = collect;
    }

    @Override
    protected void execute() {
        if (collect) {
            Robot.bucket.collectBall();
        } else {
            Robot.bucket.ejectBall();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.bucket.stopWheels();
    }

}