package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Shift extends Command {

    boolean up;
    Timer time = new Timer();
    public Shift(boolean up) {
        this.up = up;

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
        //shifts the motors based on the value of boolean "up"
        Robot.driveTrain.stop();
        if(up){
            //Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
        }else{
            //Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    @Override
    protected boolean isFinished() {
        //returns true after .3 seconds
        if (time.get() > .3){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    protected void end() {
        //calls function to stop the shifter when finished
        //Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kOff);
        time.stop();
        time.reset();
    }
    
    @Override
    protected void interrupted() {
        //runs the end method when another command requests use of the drivetrain
        end();
    }
}