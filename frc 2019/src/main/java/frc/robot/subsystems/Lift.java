/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Lifter;

public class Lift extends Subsystem {
  DoubleSolenoid liftSolenoid = new DoubleSolenoid(RobotMap.shifter1, RobotMap.shifter2);
 
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Lifter());
  }

  public void up(){
    liftSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void down(){
    liftSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
}
