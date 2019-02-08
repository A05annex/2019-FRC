package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmTeleop extends Command{

    Joystick stick;

    public ArmTeleop() {

        //only functions if the drive train is not in use by another command
        requires(Robot.armDriveTrain);
        this.stick = Robot.oi.getStick();
      }
    
      @Override
      protected void initialize() {
          //sets the wheels to brake when assigned a motor power of 0
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
          //runs the arcadeDrive function from the drive train
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