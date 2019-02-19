/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMapORG;

/**
 * Add your docs here.
 */
public class BucketLimitSwitch extends Subsystem {
  
  public DigitalInput bucketSwitch = new DigitalInput(RobotMapORG.limitSwitch);

  public void BucketLimitSwitch(){


  }

  @Override
  public void initDefaultCommand() {
    
  }
}

