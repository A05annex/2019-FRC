/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class DriveAndLand extends CommandGroup {
    /**
     * Add your docs here.
     */
    public DriveAndLand() {

        //robot drives forward as arm moves into landing position
        addParallel(new TimedDrive(2.0, 0.2));
        addSequential(new InterpolateAndCheck(ArmPositions.ENDGAME_LAND));

    }
}
