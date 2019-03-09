/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.LiftingPower;
import frc.robot.commands.RetractLift;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class DownerAndLand extends CommandGroup {
    /**
     * Add your docs here.
     */
    public DownerAndLand() {

    //robot drives forward as arm retracts so it won't hit edge
    //as cylanders get to the platform, they retract

    addSequential(new LiftingPower(false));
    addParallel(new TimedDrive(1.0, 0.2));
    addSequential(new InterpolateAndCheck(ArmPositions.ENDGAME_PARK));
    addSequential(new RetractLift(Constants.END_GAME_PNEUMATICS_RETRACT_DURATION));
  
  }
}
