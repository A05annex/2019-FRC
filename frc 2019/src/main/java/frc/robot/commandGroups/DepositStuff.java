/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.DepositBall;
import frc.robot.commands.Grab;
import frc.robot.subsystems.ArmPositions;
import frc.robot.subsystems.Bucket;

public class DepositStuff extends CommandGroup {

  public DepositStuff() {
    requires(Robot.bucket);
    if(Robot.bucket.state == Bucket.BALL){
      addSequential(new DepositBall());
    }else if(Robot.bucket.state == Bucket.HATCH){
      addSequential(new Grab(Grab.RELEASE_HATCH));
    }else{

    }
  }

  @Override
  protected void end(){
    new InterpolateAndCheck(ArmPositions.LOW_CARGO);
    new Grab(Grab.GRAB_HATCH);
  }

  @Override
  protected void interrupted(){
    end();
  }

}
