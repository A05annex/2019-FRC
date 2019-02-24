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
    private static final Timer time = new Timer();

    /**
     * @param lift_robot (boolean) Either {@link #LIFT_ROBOT} or {@link #RETRACT_LIFTERS}.
     */
    public Lifter(boolean lift_robot) {
        super();
        action = lift_robot;
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (LIFT_ROBOT == action) {
            Robot.lift.lift_robot();
        } else {
            Robot.lift.retract_lifters();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //returns true after .3 seconds (should be long enough to reset the valve piston).
        return time.get() > .3;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.lift.off();
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
