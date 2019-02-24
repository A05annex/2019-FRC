/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.commands.SetArmTarget;
import frc.robot.subsystems.ArmPositions;

public class DepositBallHigh extends CommandGroup {

    public DepositBallHigh() {

        //will hook up to button if deemed useful and button efficient
        //addSequential(new Centering());
        //no way to center yet

        addSequential(new SetArmTarget(ArmPositions.HIGH_CARGO));
        addSequential(new SetAndWaitForArmPosition(ArmPositions.HIGH_CARGO));


    }
}
