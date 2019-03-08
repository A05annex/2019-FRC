package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TapeStraighten extends Command {

    public TapeStraighten() {
/*
        //only functions if the drive train is not in use by another command
        requires(Robot.driveTrain);
        requires(Robot.gripDetection);*/
    }/*

    @Override
    protected void initialize() {
        Robot.gripDetection.startVision();
    }

    @Override
    protected void execute() {
        Robot.driveTrain.inputDriveSG(Robot.gripDetection.slowToRect());
    }

    @Override
    protected boolean isFinished() {
        //only returns false because the command cannot end without being interrupted
        if (Robot.gripDetection.slowToRect() == 0) {
            return true;
        } else {
            return false;

        }
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
    }*/
    @Override
    public boolean isFinished(){
        return false;
    }
}