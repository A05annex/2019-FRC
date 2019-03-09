package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class BumpTargetPosition extends Command {

    public static final boolean BUMP_ARM_ANGLE = true;
    public static final boolean BUMP_BUCKET_ANGLE = false;

    public static final boolean INCREMENT = true;
    public static final boolean DECREMENT = false;

    private final boolean bumpArm;
    private final double inc;


    /**
     * A calibration command used to bump the arm target by a small delta to aid in refining rough target
     * positions determined through hand positioning to the actual positions that will work in competition.
     */
    public BumpTargetPosition(boolean bumpArm, boolean incDec) {
        super();
        this.bumpArm = bumpArm;
        this.inc = incDec ? Constants.BUMP_INCREMENT : -Constants.BUMP_INCREMENT;
    }

    @Override
    protected void execute() {
        super.execute();
        /*XboxController xbox = Robot.getOI().getXbox();
        if (bumpArm) {
            if (xbox.getBumper(GenericHID.Hand.kLeft)) {
                Robot.armDriveTrain.bumpTargetPosition(0.0, inc, 0.0);
            } else {
                Robot.armDriveTrain.bumpTargetPosition(inc, 0.0, 0.0);

            }
        } else {
            Robot.armDriveTrain.bumpTargetPosition(0.0, 0.0, inc);

        }*/
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
