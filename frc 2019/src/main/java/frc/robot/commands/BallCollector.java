package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Bucket;

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
        requires(Robot.bucketWheelz);
        this.collect = collect;
    }

    @Override
    protected void execute() {
        if (collect) {
            Robot.bucketWheelz.collect();
            frc.robot.subsystems.Bucket.deposit(Bucket.BALL);
        } else {
            Robot.bucketWheelz.eject();

        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.bucketWheelz.stop();
    }

}