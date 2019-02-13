package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * This command runs the arm from the driver station using buttons on the joystick for testing.
 */
public class ArmTeleop extends Command{

    Joystick stick;

    public ArmTeleop() {
        // This command can only function if the robot arm is NOT being controlled by something else.
        requires(Robot.armDriveTrain);
        this.stick = Robot.oi.getStick();
      }
    
      @Override
      protected void initialize() {
        // Sets the arms to brake when assigned a power of 0.0 (does this
        // apply when you don't have encoders?)
        Robot.armDriveTrain.setNeutralMode(NeutralMode.Brake);

      }
    
      @Override
      protected void execute() {
        if(stick.getRawButton(9)){
          Robot.armDriveTrain.armMotorLower.set(.5);
        }
        else if(Robot.oi.stick.getRawButton(10)){
            Robot.armDriveTrain.armMotorLower.set(-.3);
        }
        else{
            Robot.armDriveTrain.armMotorLower.set(0);
        }

        if(stick.getRawButton(7)){
            Robot.armDriveTrain.armMotorUpper.set(.3);
        }
        else if(stick.getRawButton(8)){
            Robot.armDriveTrain.armMotorUpper.set(-.5);
        }else if(stick.getRawButton(11)){
            Robot.armDriveTrain.armMotorUpper.set(-.8);
        }else{
            Robot.armDriveTrain.armMotorUpper.set(0);
        }

        if(stick.getRawButton(1)){
            Robot.armDriveTrain.bucket.set(.3);
            SmartDashboard.putString("DB/String 8", "1");
        }else if(stick.getRawButton(2)){
            Robot.armDriveTrain.bucket.set(-.3);
            SmartDashboard.putString("DB/String 8", "2");
        }else{
            Robot.armDriveTrain.bucket.set(0);
            SmartDashboard.putString("DB/String 8", "0");
        }
          //runs the arcadeDrive function from the drive train
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