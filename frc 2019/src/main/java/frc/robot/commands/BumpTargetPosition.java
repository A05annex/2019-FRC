package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class BumpTargetPosition extends Command {
    private static final int LOWER = 0;
    private static final int UPPER = 1;

    private static final int X = 0;
    private static final int Y = 1;

    public static final int BUMP_ARM_UP = 0;
    public static final int BUMP_ARM_DOWN = 1;
    public static final int BUMP_ARM_IN = 2;
    public static final int BUMP_ARM_OUT = 3;

    public static final boolean INCREMENT = true;
    public static final boolean DECREMENT = false;

    private final int moveType;


    /**
     * A calibration command used to bump the arm target by a small delta to aid in refining rough target
     * positions determined through hand positioning to the actual positions that will work in competition.
     *
     * @param moveType
     */
    public BumpTargetPosition(int moveType) {
        super();
        this.moveType = moveType;
    }

    @Override
    protected void execute() {
        super.execute();
        XboxController xbox = Robot.getOI().getXbox();

        // with bumper is always the bucket
        if (xbox.getBumper(GenericHID.Hand.kLeft)) {
            Robot.armDriveTrain.bumpTargetAngles(0.0, 0.0,
                    (BUMP_ARM_UP == moveType || BUMP_ARM_IN == moveType) ?
                            Constants.BUMP_INC_BUCKET : -Constants.BUMP_INC_BUCKET);
        } else {
            double[] targetAngles = Robot.armDriveTrain.getCurrentTargetAngles();
            double[] position = ArmPathInterpToTarget.anglesToPosition(targetAngles, new double[2]);
            position[(BUMP_ARM_UP == moveType || BUMP_ARM_DOWN == moveType) ? Y : X] +=
                    (BUMP_ARM_UP == moveType || BUMP_ARM_OUT == moveType) ?
                            Constants.BUMP_INC_POSITION : -Constants.BUMP_INC_POSITION;
            double[] newAngles = ArmPathInterpToTarget.positionToAngles(position, new double[2]);
            Robot.armDriveTrain.bumpTargetAngles(newAngles[LOWER] - targetAngles[LOWER],
                    newAngles[UPPER] - targetAngles[UPPER], 0.0);

        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
