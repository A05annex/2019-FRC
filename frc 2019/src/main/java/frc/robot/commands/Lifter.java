/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Lifter extends Command {

    public static final boolean LIFT_ROBOT = true;
    public static final boolean RETRACT_LIFTERS = false;

    private final boolean action;
    private final double duration;
    //now duration is just however long switchy boi takes
    private static final Timer time = new Timer();

    /**
     * @param lift_robot (boolean) Either {@link #LIFT_ROBOT} or {@link #RETRACT_LIFTERS}.
     */
    public Lifter(boolean lift_robot, double lift_duration) {
        super();
        action = lift_robot;
        duration = lift_duration;

        requires(Robot.lift);

    }


    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        time.reset();
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (LIFT_ROBOT == action) {
            //Robot.lift.lift_robot();

            //for new pneumatics
            Robot.lift.lift_robot();
        } 
        else if(RETRACT_LIFTERS == action){
            Robot.lift.retract_lifters();
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //returns true after .3 seconds (should be long enough to reset the valve piston).
        //return time.get() > .3;
        
        //new isFinnished for new pneumatics
        if (time.get() > duration) {
            return true;
        }
        else{
            return false;
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        time.stop();
        time.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
