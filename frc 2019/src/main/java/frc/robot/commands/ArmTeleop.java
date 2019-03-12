package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Is the default command for the arm that lets the driver control the arm position from the joysticks of the
 * arm-control gamepad. While manual control is good for testing. It sucks for competition. This command mode
 * is the default; but, is immediately interrupted be any commands to set the arm to any specific position.
 * <p>
 * A button/trigger/signal could be set to return control to the operator.
 */
public class ArmTeleop extends Command {

    static final double s_maxSpeed = 0.75;

    Joystick stick;

    public ArmTeleop() {
        super();
        // This command can only function if the robot arm is NOT being controlled by
        // something else.
        requires((Subsystem) Robot.armDriveTrain);
    }

    @Override
    protected void execute() {
        /*XboxController xbox = Robot.getOI().getXbox();
        // left stick is lower arm lift_robot-retract_lifters, right stick is upper arm lift_robot-retract_lifters
        Robot.armDriveTrain.inputDriveLowArm(xbox.getY(GenericHID.Hand.kLeft) * s_maxSpeed);
        Robot.armDriveTrain.inputDriveUppArm(xbox.getY(GenericHID.Hand.kRight) * s_maxSpeed);*/
    }

    @Override
    protected boolean isFinished() {
        // Returns false because the command cannot end without being interrupted
        return false;
    }

    @Override
    protected void end() {
        // Calls function to stop the motors
        Robot.armDriveTrain.stop();
    }

    @Override
    protected void interrupted() {
        // Runs the end method when another command requests use of the robot arm.
        end();
    }
}