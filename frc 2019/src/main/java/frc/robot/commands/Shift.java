package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Shift extends Command {

    
    Timer time = new Timer();
    public Shift() {

        //only functions if the drive train is not in use by another command
        requires(Robot.driveTrain);
    }
    
    @Override
    protected void initialize() {

        time.start();

        //sets the wheels to brake when assigned a motor power of 0
        Robot.driveTrain.setNeutralMode(NeutralMode.Coast);

    }
    
    @Override
    protected void execute() {
        //runs the arcadeDrive function from the drive train
        Robot.driveTrain.stop();
        Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
    }
    
    @Override
    protected boolean isFinished() {
        //only returns false because the command cannot end without being interrupted
        if (time.get() > .3){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    protected void end() {
        //calls function to stop the motors
        Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kOff);
        time.stop();
        time.reset();
    }
    
    @Override
    protected void interrupted() {
        //runs the end method when another command requests use of the drivetrain
        end();
    }
}