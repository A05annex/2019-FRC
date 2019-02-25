package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Grab extends Command {

    public static final boolean GRAB_HATCH = true;
    public static final boolean RELEASE_HATCH = false;

    private final boolean action;
    private boolean isFinished = false;


    public Grab(boolean grabHatch) {
        super();
        action = grabHatch;
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
        isFinished = false;
    }

    @Override
    protected void execute() {
        if (GRAB_HATCH == action) {
            Robot.grabber.grabHatch();
        } else {
            Robot.grabber.releaseHatch();
        }
        isFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
