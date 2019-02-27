/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Lifter;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;

public class DriveAndPullIn extends CommandGroup {
    /**
     * Add your docs here.
     */
    public DriveAndPullIn() {

    //the arm pulls the robot up as the wheels begin to drive forward
    addParallel(new TimedDrive(1.0, 0.2));

    //this lift bit is untested. for new pneumatics bc need to be constantly applying pressure.
    //time will need to be how long it takes this lil group to complete
    addParallel(new Lifter(Lifter.LIFT_ROBOT, 1.0));

    addSequential(new InterpolateAndCheck(ArmPositions.PULL_IN));
  
  }
}
