package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class SynchronisedLift extends Command {

    private final Timer timer = new Timer();
    private boolean isFinished = false;
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
        initialRoll = Robot.ahrs.getRoll();
        liftCycle = 0;
    }

    @Override
    protected void execute() {
        if (timer.get() > 5.0) {
            // we've been going long enough to be done.
            Robot.lift.lift_robot_left();
            Robot.lift.lift_robot_right();
            isFinished = true;
        } else {
            // in the lift - check roll and pulse leading cylinder as required to get sides synchronised
            // Map so positive roll happens when right side is lower
            double currentRoll = Robot.ahrs.getRoll();
            currentRoll -= initialRoll;
            if (currentRoll > 10.0) {
                // Right side is low, pulse left every other cycle to stop the roll
                Robot.lift.lift_robot_right();
                if (liftCycle%2 == 0) {
                    Robot.lift.retract_robot_left();
                } else {
                    Robot.lift.lift_robot_left();
                }
            } else if (currentRoll > 5.0) {
                // Right is a little low, pulse left every 4th cycle to slow the extension down
                Robot.lift.lift_robot_right();
                if (liftCycle%4 == 0) {
                    Robot.lift.retract_robot_left();
                } else {
                    Robot.lift.lift_robot_left();
                }
            } else if (currentRoll < -10.0) {
                // Left side is low, pulse right every other cycle to stop the roll
                if (liftCycle%2 == 0) {
                    Robot.lift.retract_robot_right();
                } else {
                    Robot.lift.lift_robot_right();
                }
                Robot.lift.lift_robot_left();
            } else if (currentRoll < -5.0) {
                // left is a little low, pulse right every 4th cycle to slow the extension down
                if (liftCycle%4 == 0) {
                    Robot.lift.retract_robot_right();
                } else {
                    Robot.lift.lift_robot_right();
                }
                Robot.lift.lift_robot_left();
            } else {
                // Every this is AOK, full lift Mr. Zulu
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
        timer.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
