/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class NewLift extends Subsystem {
  
  //new subsystem for the new pneumatics, which are single solenoids
  //not operated by pulses, but sustained pressure
  //to keep on, must have pressure going to solenoids

  private final Solenoid liftSolenoid = new Solenoid(RobotMap.lift1);

  public NewLift() {
    super();
}

  public void addPressure(){
    //pressure
    liftSolenoid.set(true);
  }

  public void ventPressure(){
    //no pressure, gravity will make robot go down
    liftSolenoid.set(false);
  }

  @Override
  public void initDefaultCommand() {
  
  }
}
