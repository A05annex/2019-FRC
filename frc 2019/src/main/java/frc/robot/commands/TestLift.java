package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TestLift extends Command {

    static final public boolean LEFT = false;
    static final public boolean RIGHT = true;

    static final public boolean RETRACT = false;
    static final public boolean EXTEND = true;

    boolean side;
    boolean direction;
    boolean isFinished = false;

    public TestLift(boolean side, boolean direction) {
        super();
        requires(Robot.lift);
        this.side = side;
        this.direction = direction;
    }

    @Override
    protected void initialize() {
        isFinished = false;
    }

    @Override
    protected void execute() {
        if (LEFT == side) {
            if (EXTEND == direction) {
                Robot.lift.lift_robot_left();
            } else {
                Robot.lift.retract_robot_left();
            }
        } else {
            if (EXTEND == direction) {
                Robot.lift.lift_robot_right();
            } else {
                Robot.lift.retract_robot_right();
            }
        }
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
