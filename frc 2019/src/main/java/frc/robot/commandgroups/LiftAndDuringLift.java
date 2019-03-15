/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ArmTimedToTarget;
import frc.robot.commands.LiftingPower;
import frc.robot.commands.SynchronisedLift;
import frc.robot.subsystems.ArmPositions;

public class LiftAndDuringLift extends CommandGroup {

//    boolean isFinished = false;
//    // Can be overwritten by teams
//    @Override
//    protected void initialize() {
//        // if we are not at pre-lift position we shouyld not be lifting.
//        isFinished = ArmPositions.PRE_ENDGAME_LIFT != Robot.armDriveTrain.getTargetPosition();
//    }
//
//    @Override
//    protected boolean isFinished() {
//        if (isFinished) {
//            return true;
//        }
//        return super.isFinished();
//    }

    /**
     * Add your docs here.
     */
    public LiftAndDuringLift() {

        //activates pneumatics as arm helps pull robot onto platform
        addSequential(new LiftingPower(true));
        addSequential(new InterpolateAndCheck(ArmPositions.START_LIFT));
        addParallel(new ArmTimedToTarget(ArmPositions.DURING_LIFT,Constants.END_GAME_PNEUMATICS_DROP,
        Constants.END_GAME_PNEUMATICS_LIFT_DURATION));
        addSequential(new SynchronisedLift());
    }
}

