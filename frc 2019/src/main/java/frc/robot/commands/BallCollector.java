package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Robot;

public class BallCollector extends Command {

    double wheelz;
    Timer timer = new Timer();
    public BallCollector(double wheelz){
        requires(Robot.bucketWheelz);
        this.wheelz = wheelz;

    }

    /*@Override
    protected void initialize() {
        timer.start();
    }
    */
    @Override
    protected void execute(){
        Robot.bucketWheelz.wheelsServo.set(wheelz);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    //if need to change to whenPressed() and only have it go for a hot sec
    /*@Override
    protected boolean isFinished() {
        if(Timer.get()>2){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected void end(){

    } */



}