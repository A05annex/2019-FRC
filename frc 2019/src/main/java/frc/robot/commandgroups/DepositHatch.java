/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Grab;
import frc.robot.commands.TimedDrive;

public class DepositHatch extends CommandGroup {
  
  public DepositHatch() {

    //Runs release hatch and automatically drives backwards.
    //Designed as fix for driver control hatch release. 
    //Never used. 
    addSequential(new Grab(Grab.RELEASE_HATCH));
    addSequential(new TimedDrive(0.5, -0.2));
    
  }
}
