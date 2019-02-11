package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
          XboxController xbox = Robot.oi.getXbox();
          // left stick is lower arm up-down, right stick is upper arm up-down
          Robot.armDriveTrain.inputDriveLowArm(xbox.getY(GenericHID.Hand.kLeft) * 0.75);
          Robot.armDriveTrain.inputDriveUppArm(xbox.getY(GenericHID.Hand.kRight) * 0.75);
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