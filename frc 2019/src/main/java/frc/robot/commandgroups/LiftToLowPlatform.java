/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LiftingPower;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class LiftToLowPlatform extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LiftToLowPlatform() {

        //A commandgroup to lift the robot onto the lower platform- the 6" one. 
        //Can be hooked to one button. 
        //Is a few commandgroups strung together so that the code can be tested in chunks. 
        //Easy to modify. 
    
        addSequential(new LiftPowerAndStart());
        addSequential(new DriveAndDuringlow());
        addSequential(new DriveAndPullInLow());
        
        addSequential(new LiftArmAndDriveOn());
    
        //new plan
        //pull up with arm until wheels up to box
        //drive, lift arm, and timed pneumatics at same time
        //(fire pneumatics for like 6 seconds)
        //drive and pull arm in to home/pregame




  }
}
