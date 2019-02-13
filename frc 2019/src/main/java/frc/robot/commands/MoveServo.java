package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class MoveServo extends Command {

    double value;
    Timer time = new Timer();
    public MoveServo(double value) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.bucket);
        this.value = value;
      }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
          time.start();
      }
    
      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
        //Robot.bucket.servo.set(value);
      }
    
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
        if(time.get()>.3){
            return true;
        }else{
            return false;
        }
      }
    
      // Called once after isFinished returns true
      @Override
      protected void end() {
      }
    
      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      @Override
      protected void interrupted() {
          end();
      }
}