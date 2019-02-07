package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.GripDetection;

public class TapeStraighten extends Command{
    
    public TapeStraighten() {

        //only functions if the drive train is not in use by another command
        requires(Robot.driveTrain);
        requires(Robot.gripDetection);
      }
      @Override
      protected void initialize() {
          Robot.gripDetection.startVision();
      }
    
      @Override
      protected void execute() {
        Robot.driveTrain.inputDrive(0,0);
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