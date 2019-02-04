package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This command runs the arm from the driver station using buttons on the joystick for testing.
 */
public class ArmTeleop extends Command{

    public ArmTeleop() {
        // This command can only function if the robot arm is NOT being controlled by something else.
        requires(Robot.armDriveTrain);
      }
    
      @Override
      protected void initialize() {
        // Sets the arms to brake when assigned a power of 0.0 (does this
        // apply when you don't have encoders?)
        Robot.armDriveTrain.setNeutralMode(NeutralMode.Brake);
      }
    
      @Override
      protected void execute() {
        Joystick stick = Robot.oi.getStick();
        double lowerArmPower = 0.0;
        double upperArmPower = 0.0;
        // Adjust the arm power if any arm power controls are activated.
        if(stick.getRawButton(5)){
            lowerArmPower = 0.3;

        }
        else if(stick.getRawButton(6)){
            lowerArmPower = -0.3;
        }
        // TODO: add upper arm controls

        // set the arm lower and upper power components
        Robot.armDriveTrain.setArmPower(lowerArmPower, upperArmPower);
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