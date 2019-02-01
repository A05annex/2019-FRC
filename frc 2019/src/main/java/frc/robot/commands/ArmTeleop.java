package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmTeleop extends Command{

    public ArmTeleop() {

        //only functions if the drive train is not in use by another command
        requires(Robot.armDriveTrain);
      }
    
      @Override
      protected void initialize() {
          //sets the wheels to brake when assigned a motor power of 0
        Robot.armDriveTrain.setNeutralMode(NeutralMode.Brake);
      }
    
      @Override
      protected void execute() {
          //runs the arcadeDrive function from the drive train
          Robot.armDriveTrain.stickDrive(Robot.oi.getStick());
      }
    
      @Override
      protected boolean isFinished() {
        //only returns false because the command cannot end without being interrupted
        return false;
      }
    
      @Override
      protected void end() {
          //calls function to stop the motors
          Robot.armDriveTrain.stop();
      }
    
      @Override
      protected void interrupted() {
          //runs the end method when another command requests use of the drivetrain
          end();
      }
}