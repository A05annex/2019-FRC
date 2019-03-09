package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
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
        Joystick stick = Robot.getOI().getStick();
        // get stick values and set the signs to match the arcade drive forward,rotate conventions
        double stickY = -stick.getY();
        double twist = -stick.getTwist();
        // subtract the dead band and scale what is left outside the dead band
        double ySignMult = (stickY > 0.0) ? 1.0 : -1.0;
        double twistSignMult = (twist > 0.0) ? 1.0 : -1.0;
        double useY = (Math.abs(stickY) <= Constants.DRIVE_DEADBAND) ? 0.0 :
                (Math.abs(stickY) - Constants.DRIVE_DEADBAND) / (1.0 - Constants.DRIVE_DEADBAND);
        double useTwist = (Math.abs(twist) <= Constants.DRIVE_DEADBAND) ? 0.0 :
                (Math.abs(twist) - Constants.DRIVE_DEADBAND) / (1.0 - Constants.DRIVE_DEADBAND);
        // do the sensitivity power function
        useY = Math.pow(useY, Constants.DRIVE_SENSITIVITY);
        useTwist = Math.pow(useTwist, Constants.DRIVE_SENSITIVITY);
        // apply the gains
        double forward = useY * Constants.DRIVE_FORWARD_GAIN * ySignMult;
        double rotate = ((useTwist * Constants.DRIVE_TURN_GAIN) -
                (useY * (Constants.DRIVE_TURN_GAIN - Constants.DRIVE_TURN_AT_SPEED_GAIN))) * twistSignMult;
        // Now set the speeds
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