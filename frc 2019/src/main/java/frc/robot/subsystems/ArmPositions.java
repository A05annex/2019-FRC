package frc.robot.subsystems;

/**
 * This enumeration is a set of predefined positions that we will want to move the arm to in order to:
 * <ul>
 * <li><tt>PREGAME</tt> - The pregame position of the arm/bucket that fits in the robot cube;</li>
 * <li><tt>HOME</tt> - The stable drive position for the empty arm, arm and cargo, or arm and hatch
 * configuration;</li>
 * <li><tt>LOW_HATCH</tt> - The position for pickup of the hatch from the depot or deliver the hatch
 * to the cargo ship or lower rocket hatch port;</li>
 * <li><tt>LOW_CARGO</tt> - The position to deliver the low rocket port cargo ball;</li>
 * <li><tt>MID_HATCH</tt> - The position to deliver the hatch to the mid rocket hatch port;</li>
 * <li><tt>MID_CARGO</tt> - The position to deliver the mid rocket port cargo ball;</li>
 * <li><tt>HIGH_HATCH</tt> - The position to deliver the hatch to the high rocket hatch port;</li>
 * <li><tt>HIGH_CARGO</tt> - The position to deliver the high rocket port cargo ball;</li>
 * <li><tt>PICKUP_FROM_FLOOR</tt> - The position to pickup a cargo ball from the floor;</li>
 * <li><tt>PRE_ENDGAME_LIFT</tt> - The position of the arm before the lift cylinders are extended;</li>
 * <li><tt>DURING_LIFT</tt> - Repositioning the arm as the pneumatics are extending;</li>
 * <li><tt>ENDGAME_LIFT</tt> - The position of the arm that pushes the robot onto the platform post lift
 * and pre-lift retraction;</li>
 * <li><tt>ENDGAME_LAND</tt> - The position to land the rear wheels of the robot on the platform;</li>
 * <li><tt>ENDGAME_PARK</tt> - The position that parks the robot on the platform;</li>
 * <li><tt>POST_ENDGAME_PARK</tt> - The position for the arm once the robot is parked on the platform</li>
 * </ul>
 */
public enum ArmPositions {
    PREGAME(0),
    HOME(1),
    LOW_HATCH(2),
    LOW_CARGO(3),
    MID_HATCH(4),
    MID_CARGO(5),
    HIGH_HATCH(6),
    HIGH_CARGO(7),
    PICKUP_FROM_FLOOR(8),
    
    //Endgame lift positions
    PRE_ENDGAME_LIFT(9),
    START_LIFT(10),
    DURING_LIFT(11),
    PULL_IN(12),
    ENDGAME_LIFT(13),
    ENDGAME_LAND(14),
    ENDGAME_PARK(15),
    POST_ENDGAME_PARK(16),

    //Low lift positions
    PRE_LOW_LIFT(17),
    START_LOW_LIFT(18),
    DURING_LOW_LIFT(19),
    PULL_IN_LOW(20),
    LIFT_ARM(21),

    CARGO_BAY(22);


    public final int value;

    ArmPositions(int value) {
        this.value = value;
    }
}
