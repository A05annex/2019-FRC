/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EndGameDowner;
import frc.robot.commands.Lifter;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class DownerAndLand extends CommandGroup {
    /**
     * Add your docs here.
     */
    public DownerAndLand() {

    //robot drives forward as arm retracts so it won't hit edge
    //as cylanders get to the platform, they retract


    //this lift bit is untested. for new pneumatics bc need to be constantly applying pressure.
    //time will need to be how long it takes this lil group to complete
    addParallel(new Lifter(true, 1.0));

    addParallel(new TimedDrive(1.0, 0.2));
    addSequential(new InterpolateAndCheck(ArmPositions.ENDGAME_PARK));

    //this lift bit is untested. for new pneumatics bc need to be constantly applying pressure.
    //time will need to be how long it takes this lil group to complete
    addSequential(new Lifter(false, 2.0));
    //addSequential(new EndGameDowner());
  }
}
