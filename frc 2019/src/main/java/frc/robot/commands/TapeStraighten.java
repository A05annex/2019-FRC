package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.GripDetection;

public class TapeStraighten extends Command{
    
    char direction;

    public TapeStraighten(char direction) {

        //only functions if the drive train is not in use by another command
        requires(Robot.driveTrain);
        requires(Robot.gripDetection);
        this.direction=direction;
      }
      @Override
      protected void initialize() {

      }
    
      @Override
      protected void execute() {
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