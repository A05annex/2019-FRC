package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TapeFind extends Command {

    char direction;

    public TapeFind(char direction) {

        //only functions if the drive train is not in use by another command
        requires(Robot.driveTrain);
        requires(Robot.gripDetection);
        this.direction = direction;
    }

    @Override
    protected void initialize() {
        Robot.gripDetection.startVision();
    }

    @Override
    protected void execute() {
        Robot.driveTrain.inputDrive(Robot.gripDetection.findTape(direction));
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
        Robot.gripDetection.stopVision();
    }

    @Override
    protected void interrupted() {
        //runs the end method when another command requests use of the drivetrain
        end();
    }
}