/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// i gotta figure out enc to inch with test and do the math 
package frc.robot.commands.drivingauto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.drivingauto.Drive4Enc;
import frc.robot.commands.drivingauto.Turn4Enc;
/**
 * An example command.  You can replace me with your own command.
 */
public class getDrivin extends CommandGroup {
  public getDrivin() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
    // requires(robot.commands.RobotMap);
    // requires(drivingauto.Drive4Enc);
    // requires(drivingauto.Turn4Enc);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.AHRS.getActualUpdateRate();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    addSequential(new Drive4Enc(300, 0.7));
    addSequential(new Turn4Enc(180, 0.5));
    addSequential(new Drive4Enc(0, -0.7));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.rightMaster.set(0);
    Robot.driveTrain.leftMaster.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}