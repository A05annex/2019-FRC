package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmPositions;

public class SetRocketPosition extends Command {

    public final static int LOWER = 0;
    public final static int MIDDLE = 1;
    public final static int UPPER = 2;

    private final ArmPositions hatchPosition;
    private final ArmPositions ballPosition;
    private boolean finished = false;

    public SetRocketPosition(int position) {
        super();
        if (LOWER == position) {
            hatchPosition = ArmPositions.LOW_HATCH;
            ballPosition = ArmPositions.LOW_CARGO;
        } else if (MIDDLE == position) {
            hatchPosition = ArmPositions.MID_HATCH;
            ballPosition = ArmPositions.MID_CARGO;
        } else {
            hatchPosition = ArmPositions.HIGH_HATCH;
            ballPosition = ArmPositions.HIGH_CARGO;
        }
    }


    @Override
    protected void execute() {
        super.execute();
        XboxController xbox = Robot.getOI().getXbox();
        if (xbox.getBumper(GenericHID.Hand.kRight)) {
            Robot.armDriveTrain.setTargetPosition(ballPosition);
        } else {
            Robot.armDriveTrain.setTargetPosition(hatchPosition);
        }
        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
