package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class SetArmHeight extends Command{
    public int height;

    public SetArmHeight(int height) {
        this.height = height;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armDriveTrain);
        }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
          Robot.armDriveTrain.setHeight(height);
      }
    
      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
          SmartDashboard.putString("DB/String 9", Double.toString((-Robot.oi.stick.getRawAxis(3)+1)/2));
          int flipper = (int)(Math.round(((-Robot.oi.stick.getRawAxis(3)+1)/2)*55) + 5);
          Robot.armDriveTrain.setHeight(flipper);
          Robot.armDriveTrain.moveToHeight();
      }
    
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
          SmartDashboard.putString("DB/String 0", Double.toString(Math.abs(Robot.armDriveTrain.baseAngle.get()-Robot.armDriveTrain.angle1)));
          SmartDashboard.putString("DB/String 1", Double.toString(Math.abs(Robot.armDriveTrain.secondAngle.get()-Robot.armDriveTrain.angle2)));
          /*if(Math.abs(Robot.armDriveTrain.baseAngle.get()-Robot.armDriveTrain.angle1) < 5 && Math.abs(Robot.armDriveTrain.secondAngle.get()-Robot.armDriveTrain.angle2)< 5){
            return(true);
          }else{
              return(false);
          }*/
          return false;
      }
    
      // Called once after isFinished returns true
      @Override
      protected void end() {

      }
    
      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      @Override
      protected void interrupted() {
          this.end();
      }
}