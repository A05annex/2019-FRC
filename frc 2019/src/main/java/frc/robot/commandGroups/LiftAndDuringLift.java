/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EndGameLifter;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.subsystems.ArmPositions;

public class LiftAndDuringLift extends CommandGroup {
    /**
     * Add your docs here.
     */
    public LiftAndDuringLift() {


        addParallel(new SetAndWaitForArmPosition(ArmPositions.DURING_LIFT));
        //still need to make DURING_LIFT
        addSequential(new EndGameLifter());

    }
}
