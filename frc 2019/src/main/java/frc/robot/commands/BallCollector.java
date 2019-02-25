package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

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