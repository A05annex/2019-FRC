package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

public class SynchronisedLift extends Command {

    private final Timer timer = new Timer();
    private boolean isFinished = false;
    private boolean useNavx;
    private double initialRoll = 0.0;
    private int liftCycle = 0;

    public SynchronisedLift() {
        super();
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        isFinished = false;
        timer.reset();
        timer.start();
        // Map so positive roll happens when right side is lower
        useNavx = (null != Robot.getAHRS());
        if (useNavx) {
            initialRoll = -Robot.getAHRS().getYaw();
        }
        liftCycle = 0;
    }

    @Override
    protected void execute() {
        if (timer.get() > Constants.LIFT_TIME) {
            // we've been going long enough to be done - just lock them on and finish.
            isFinished = true;
        } else {
            // in the lift - check roll and pulse leading cylinder as required to get sides synchronised
            // Map so positive roll happens when right side is lower
            if (useNavx) {
                double currentRoll = -Robot.getAHRS().getYaw();
                currentRoll -= initialRoll;
                if (currentRoll > 0) {
                    // Right side is low, pulse left to slow it down
                    Robot.lift.lift_robot_right();
                    if (liftCycle%Constants.LIFT_CORRECT_CYCLES <
                            (((currentRoll * Constants.LIFT_CORRECT_CYCLES)/(2.0 * Constants.LIFT_CORRECT_MAX_ANGLE)) - 2)) {
                        Robot.lift.retract_robot_left();
                    } else {
                        Robot.lift.lift_robot_left();
                    }
                } else {
                    // Left side is low, pulse right to slow it down
                    if (liftCycle%Constants.LIFT_CORRECT_CYCLES <
                            ((((-currentRoll) * Constants.LIFT_CORRECT_CYCLES)/(2.0 * Constants.LIFT_CORRECT_MAX_ANGLE)) - 2)) {
                        Robot.lift.retract_robot_right();
                    } else {
                        Robot.lift.lift_robot_right();
                    }
                    Robot.lift.lift_robot_left();
                }
            } else {
                Robot.lift.lift_robot_left();
                Robot.lift.lift_robot_right();
            }
        }
        liftCycle++;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    @Override
    protected void end() {
        Robot.lift.lift_robot_left();
        Robot.lift.lift_robot_right();
        timer.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
