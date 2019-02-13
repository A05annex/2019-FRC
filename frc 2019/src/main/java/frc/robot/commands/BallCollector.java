package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallCollector extends Command {

    public BallCollector() {
        super();
        requires(Robot.bucketWheelz);

    }

    @Override
    protected void execute() {
        Joystick stick = Robot.oi.getStick();
        if (stick.getRawButton(11)) {
            Robot.bucketWheelz.collect();
        } else if (stick.getRawButton(12)) {
            Robot.bucketWheelz.eject();
        } else {
            Robot.bucketWheelz.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}