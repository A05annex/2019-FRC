package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetArmHeight extends Command{
    public SetArmHeight() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armDriveTrain);
        }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
          Robot.armDriveTrain.setHeight(26);
      }
    
      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
          Robot.armDriveTrain.moveToHeight();
      }
    
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
          if(Math.abs(Robot.armDriveTrain.baseAngle.get()-Robot.armDriveTrain.angle1)<5){
              return(true);
          }else{
              return(false);
          }
      }
    
      // Called once after isFinished returns true
      @Override
      protected void end() {
          Robot.armDriveTrain.lockPosition();
      }
    
      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      @Override
      protected void interrupted() {
          this.end();
      }
}