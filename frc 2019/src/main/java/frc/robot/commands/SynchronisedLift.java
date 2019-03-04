package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
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
        initialRoll = -Robot.getAHRS().getRoll();
        liftCycle = 0;
    }

    @Override
    protected void execute() {
        if (timer.get() > 300.0) {
            // we've been going long enough to be done.
            Robot.lift.lift_robot_left();
            Robot.lift.lift_robot_right();
            isFinished = true;
        } else {
            // in the lift - check roll and pulse leading cylinder as required to get sides synchronised
            // Map so positive roll happens when right side is lower
            double currentRoll = -Robot.getAHRS().getRoll();
            currentRoll -= initialRoll;
            if (currentRoll > Constants.LIFT_CORRECT_MAX_ANGLE) {
                // Right side is low, pulse left every other cycle to stop the roll
                Robot.lift.lift_robot_right();
                if (liftCycle%Constants.LIFT_CORRECT_CYCLES < Constants.LIFT_CORRECT_MAX) {
                    Robot.lift.retract_robot_left();
                } else {
                    Robot.lift.lift_robot_left();
                }
            } else if (currentRoll > Constants.LIFT_CORRECT_MID_ANGLE) {
                // Right is a little low, pulse left every 4th cycle to slow the extension down
                Robot.lift.lift_robot_right();
                if (liftCycle%Constants.LIFT_CORRECT_CYCLES  < Constants.LIFT_CORRECT_MID) {
                    Robot.lift.retract_robot_left();
                } else {
                    Robot.lift.lift_robot_left();
                }
            } else if (currentRoll > Constants.LIFT_CORRECT_MIN_ANGLE) {
                // Right is a little low, pulse left every 4th cycle to slow the extension down
                Robot.lift.lift_robot_right();
                if (liftCycle%Constants.LIFT_CORRECT_CYCLES < Constants.LIFT_CORRECT_MIN) {
                    Robot.lift.retract_robot_left();
                } else {
                    Robot.lift.lift_robot_left();
                }
            } else if (currentRoll < -Constants.LIFT_CORRECT_MAX_ANGLE) {
                // Left side is low, pulse right every other cycle to stop the roll
                if (liftCycle%Constants.LIFT_CORRECT_CYCLES < Constants.LIFT_CORRECT_MAX) {
                    Robot.lift.retract_robot_right();
                } else {
                    Robot.lift.lift_robot_right();
                }
                Robot.lift.lift_robot_left();
            } else if (currentRoll < -Constants.LIFT_CORRECT_MID_ANGLE) {
                // left is a little low, pulse right every 4th cycle to slow the extension down
                if (liftCycle%Constants.LIFT_CORRECT_CYCLES < Constants.LIFT_CORRECT_MID) {
                    Robot.lift.retract_robot_right();
                } else {
                    Robot.lift.lift_robot_right();
                }
                Robot.lift.lift_robot_left();
            } else if (currentRoll < -Constants.LIFT_CORRECT_MIN_ANGLE) {
                // left is a little low, pulse right every 4th cycle to slow the extension down
                if (liftCycle%Constants.LIFT_CORRECT_CYCLES < Constants.LIFT_CORRECT_MIN) {
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
