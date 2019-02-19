/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.robot;
import edu.wpi.first.wpilibj.command;

  
public class CollectCargoWithLimit extends Command {
  
    private final Timer time = new Timer();
    rm1 = new VictorSP(RobotMapORG.rm1);
  
    public CollectCargoWithLimit() {
      requires(Robot.bucketLimitSwitch);

      //this will be used on actual robot, not during test on old robot
      //for test the victor above will be used
      //requires(Robot.BucketWheelz);
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    
    
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
  
      if(Robot.bucketLimitSwitch.bucketSwitch.get()){

        this.rm1.set(0);

        //used for actual robot, not for testing on old robot
        //Robot.bucketWheelz.stop();

        time.reset();
        time.start();
        //making time start here so then it returns true when 0.2 secs elapsed so wheels have time to stop
        //may be uneccessary? idk lol
      }
      else{

        this.rm1.set(0.5);

        //used for actual robot
        //Robot.bucketWheelz.collect();
      }
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
  
      if(Robot.bucketLimitSwitch.bucketSwitch.get() && time.get()>0.2){
        return true;
      }
      else{
        return false;
      }
  
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
