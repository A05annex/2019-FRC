/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;


public class LiftToPlatform extends CommandGroup {
    /**
     * Add your docs here.
     *
     * @return
     */
    public LiftToPlatform() {


        //Lifts robot to platform with one button.
        //Uses command groups that we have tested to make one button that does it all.
        //Robot uses arm, pneumatics, and wheels to lift itself onto the platform and drive on.

        
        addSequential(new InterpolateAndCheck(ArmPositions.START_LIFT));
        addSequential(new LiftAndDuringLift());
        addSequential(new DriveAndPullIn());
        addSequential(new DriveAndLand());
        addSequential(new DownerAndLand());
        addSequential(new TimedDrive(1.0, 0.15));



    //front lift not in command groups. timed drive numbers are probably off

    /*addSequential(new SetAndWaitForArmPosition(ArmPositions.PRE_ENDGAME_LIFT));
    addParallel(new EndGameLifter());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.DURING_LIFT));
    addParallel(new TimedDrive(1.0, -0.2));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.PULL_IN));
    addParallel(new TimedDrive(1.0, -0.2));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LAND));
    addParallel(new EndGameDowner());
    addParallel(new TimedDrive(1.0, -0.2));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_PARK));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.POST_ENDGAME_PARK));
    addSequential(new TimedDrive(0.5, -0.2)); */

    //rear lift
    //no longer using

    /*addParallel(new EndGameDrive());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.PRE_ENDGAME_LIFT));
    addParallel(new SetAndWaitForArmPosition(ArmPositions.DURING_LIFT));
    //still need to make DURING_LIFT

    addSequential(new EndGameLifter());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LIFT));
    addParallel(new EndGameDowner());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LAND));
    addParallel(new EndGameDrive());
    addSequential(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_PARK));
    addSequential(new SetAndWaitForArmPosition(ArmPositions.POST_ENDGAME_PARK)); */

    //NOTES
    //drive a bit more before pneumatics come up
    //lift arm a bit after we pull in so we can drive easier


    }
}
