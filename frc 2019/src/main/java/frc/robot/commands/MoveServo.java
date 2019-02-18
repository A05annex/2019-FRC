package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MoveServo extends Command {

    private final double value;
    private final Timer time = new Timer();

    public MoveServo(double value) {
        super();
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.bucket);
        this.value = value;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        SmartDashboard.putString("DB/String 6", Double.toString(value));
        //Robot.bucket.servo.set(value);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // we are assuming the command is finished in 0.3sec.
        return time.get() > 0.3;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}