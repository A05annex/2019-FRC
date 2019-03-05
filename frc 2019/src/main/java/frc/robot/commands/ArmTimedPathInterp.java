package frc.robot.commands;

import frc.robot.subsystems.ArmPositions;

/**
 * This does the path interpolation in a specified time. Originally to match the end lift to the speed of the
 * lift cylinders.
 */
public class ArmTimedPathInterp extends ArmPathInterpToTarget {

    private double seconds;

    // This is code that tests the linear path interpolation formulation.
//    public static void main(final String[] args) {
//        double[] start = {100, 43, 270};
//        double[] end = {91, 134, 790};
//
//        double[] startPosition = anglesToPosition(start, new double[2]);
//        double[] startAngles = positionToAngles(startPosition, new double[3]);
//        startAngles[BUCKET] = start[BUCKET];
//        System.out.println(String.format(
//                "start angles: %3.3f, %3.3f; position: %3.3f, %3.3f; back transform: %3.3f, %3.3f",
//                start[LOWER], start[UPPER], startPosition[X], startPosition[Y], startAngles[LOWER], startAngles[UPPER]));
//        double[] endPosition = anglesToPosition(end, new double[2]);
//        double[] endAngles = positionToAngles(endPosition, new double[2]);
//        System.out.println(String.format(
//                "start angles: %3.3f, %3.3f; position: %3.3f, %3.3f; back transform: %3.3f, %3.3f",
//                end[LOWER], end[UPPER], endPosition[X], endPosition[Y], endAngles[LOWER], endAngles[UPPER]));
//
//        // setup the interpolation
//        double deltaX = endPosition[X] - startPosition[X];
//        double deltaY = endPosition[Y] - startPosition[Y];
//        double testIncs = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY)) /
//                Constants.ARM_INCHES_PER_CYCLE;
//        double[] incs = {(endPosition[X] - startPosition[X]) / testIncs,
//                         (endPosition[Y] - startPosition[Y]) / testIncs,
//                         (end[BUCKET] - start[BUCKET]) / testIncs};
//
//        // run the interpolation
//        System.out.println("   X        Y       lower    upper   bucket");
//        for (int inc = 0; inc < testIncs; inc++) {
//            System.out.println(String.format(
//                    "%7.3f, %7.3f; %7.3f, %7.3f, %4d",
//                    startPosition[X], startPosition[Y],
//                    startAngles[LOWER], startAngles[UPPER], (int)startAngles[BUCKET]));
//            startPosition[X] += incs[X];
//            startPosition[Y] += incs[Y];
//            positionToAngles(startPosition, startAngles);
//            startAngles[BUCKET] += incs[BUCKET];
//
//        }
//        System.out.println(String.format(
//                "%7.3f, %7.3f; %7.3f, %7.3f, %4d",
//                endPosition[X], endPosition[Y], end[LOWER], end[UPPER], (int)end[BUCKET]));
//    }

    public ArmTimedPathInterp(ArmPositions newTarget, double seconds) {
        super(newTarget);
        this.seconds = seconds;
    }

    @Override
    protected void setIncs(double[] endPosition) {
        useIncrements = seconds / 0.02; // 20 ms per control cycle
    }

}
