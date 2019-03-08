/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ArmInterpolateToTarget;
import frc.robot.commands.SynchronisedLift;
import frc.robot.subsystems.ArmPositions;

public class LiftAndDuringLift extends CommandGroup {
    /**
     * Add your docs here.
     */
    public LiftAndDuringLift() {

    //activates pneumatics as arm helps pull robot onto platform
    addParallel(new ArmInterpolateToTarget(ArmPositions.DURING_LIFT));
    addSequential(new SynchronisedLift());

    //will need to retest with new pneumatics. timing may be off.
    
  }
}
