package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Teleop extends Command {

    public Teleop() {

        //only functions if the drive train is not in use by another command
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        //sets the wheels to brake when assigned a motor power of 0
        Robot.driveTrain.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    protected void execute() {
        Joystick stick = Robot.oi.getStick();
        double forward = stick.getRawAxis(1) / 3.0;
        double rotate = stick.getRawAxis(2) / 6.0;
        Robot.driveTrain.setArcadePower(forward, rotate);
    }

    @Override
    protected boolean isFinished() {
        //only returns false because the command cannot end without being interrupted
        return false;
    }

    @Override
    protected void end() {
        //calls function to stop the motors
        Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted() {
        //runs the end method when another command requests use of the drivetrain
        end();
    }
}