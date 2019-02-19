package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class FinishCollecting extends Command{
    
    public FinishCollecting(){
        super();
    }

    @Override
    protected void execute() {
        super.execute();
        new SetArmTarget(ArmPositions.LOW_CARGO);
        Robot.bucket.stopWheels();
    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}