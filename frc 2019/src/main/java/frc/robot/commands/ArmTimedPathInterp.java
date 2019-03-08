package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.ArmPositions;

/**
 * This does the path interpolation in a specified time. Originally to match the end lift to the speed of the
 * lift cylinders.
 */
public class ArmTimedPathInterp extends ArmPathInterpToTarget {

    private final double seconds;
    private final double delay;
    private final Timer timer = new Timer();

    /**
     * Perform a move that takes a specified delay in the time the move starts, and lasts for a specified duration
     * after the start.
     * @param newTarget The target position for the end of the move.
     * @param delay the delay, in seconds, before the move starts.
     * @param seconds the duration of the move in seconds.
     */
    public ArmTimedPathInterp(ArmPositions newTarget, double delay, double seconds) {
        super(newTarget);
        this.seconds = seconds;
        this.delay = delay;
    }

    @Override
    protected void initialize() {
        super.initialize();
        timer.reset();
        timer.start();
    }
    @Override
    protected void setIncs(double[] endPosition) {
        useIncrements = seconds / 0.02; // 20 ms per control cycle
    }

    @Override
    protected void execute() {
        if (timer.get() < delay) {
            return;
        }
        super.execute();
    }
    @Override
    protected void end() {
        super.end();
        timer.stop();
    }
}
