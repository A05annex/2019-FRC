/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commandgroups.DepositBallAtTarget;
import frc.robot.commandgroups.DownerAndLand;
import frc.robot.commandgroups.DriveAndLand;
import frc.robot.commandgroups.DriveAndPullIn;
import frc.robot.commandgroups.InterpolateAndCheck;
import frc.robot.commandgroups.LiftAndDuringLift;
import frc.robot.commandgroups.LiftToLowPlatform;
import frc.robot.commandgroups.LiftToPlatform;
import frc.robot.commands.ArmInterpolateToTarget;
import frc.robot.commands.BallCollector;
import frc.robot.commands.BumpTargetPosition;
import frc.robot.commands.Grab;
import frc.robot.commands.SetAndWaitForArmPosition;
import frc.robot.commands.Shift;
import frc.robot.commands.TimedDrive;
import frc.robot.subsystems.ArmPositions;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private final Joystick stick = new Joystick(0);

    private final JoystickButton trigger = new JoystickButton(this.stick, 1);
    private final JoystickButton thumb = new JoystickButton(this.stick, 2);
    private final JoystickButton topLL = new JoystickButton(this.stick, 3);
    private final JoystickButton topLR = new JoystickButton(this.stick, 4);
    private final JoystickButton topUL = new JoystickButton(this.stick, 5);
    private final JoystickButton topUR = new JoystickButton(this.stick, 6);
    private final JoystickButton button7 = new JoystickButton(this.stick, 7);
    private final JoystickButton button8 = new JoystickButton(this.stick, 8);
    private final JoystickButton button9 = new JoystickButton(this.stick, 9);
    private final JoystickButton button10 = new JoystickButton(this.stick, 10);
    private final JoystickButton button11 = new JoystickButton(this.stick, 11);
    private final JoystickButton button12 = new JoystickButton(this.stick, 12);

    // These are test and calibration initializations - they are NOT required for competition.
    private XboxController xbox = new XboxController(1);

    private final JoystickButton xboxA = new JoystickButton(xbox, 1);
    private final JoystickButton xboxB = new JoystickButton(xbox, 2);
    private final JoystickButton xboxX = new JoystickButton(xbox, 3);
    private final JoystickButton xboxY = new JoystickButton(xbox, 4);

    public Joystick getStick() {
        //method to be called by other commands or subsystems to use the joystick
        return (stick);
    }

    /**
     * Get the gamepad
     *
     * @return (XboxController) The gamepad if calibration is enabled, <tt>null</tt> otherwise.
     */
    public XboxController getXbox() {
        return (xbox);
    }

    public OI() {
        trigger.whenPressed(new Shift(true));
        thumb.whenPressed(new Shift(false));
        topUL.whileHeld(new BallCollector(BallCollector.GRAB_BALL));
        topLL.whileHeld(new BallCollector(BallCollector.EJECT_BALL));
        //topUR.whenPressed(new Grab(Grab.GRAB_HATCH));
        topLR.whenPressed(new Grab(Grab.RELEASE_HATCH));

//        topUL.whenPressed(new TapeStraighten('L'));
//        topUR.whenPressed(new TapeStraighten('R'));
//        button7.whileHeld(new TapeStraighten('L'));
//        button8.whileHeld(new TapeStraighten('R'));

        //END GAME LIFT
        //gets robot in position to drive up to platform and lift
        //topUR.whenPressed(new ArmInterpolateToTarget(ArmPositions.PRE_ENDGAME_LIFT));
        //robot lifts itself onto the platform
        //button8.whenPressed(new LiftToPlatform());
        //button9.whenPressed(new TimedDrive(2.0, 0.2));

        //CHUNKS OF END GAME LIFT
        button7.whenPressed(new InterpolateAndCheck(ArmPositions.PRE_ENDGAME_LIFT));
        button8.whenPressed(new InterpolateAndCheck(ArmPositions.START_LIFT));
        button9.whenPressed(new LiftAndDuringLift());
        button10.whenPressed(new DriveAndPullIn()); 
        button11.whenPressed(new DriveAndLand()); 
        button12.whenPressed(new DownerAndLand());
        //button12.whenPressed(new TimedDrive(0.5, -0.2));

        
        
        //LOW LIFT
        //button7.whenPressed(new InterpolateAndCheck(ArmPositions.PRE_LOW_LIFT));
        //button8.whenPressed(new LiftToLowPlatform());
        
        //CHUNKS OF LOW LIFT
        /*topUR.whenPressed(new InterpolateAndCheck(ArmPositions.PRE_LOW_LIFT));
        button7.whenPressed(new InterpolateAndCheck(ArmPositions.START_LOW_LIFT));
        button8.whenPressed(new InterpolateAndCheck(ArmPositions.DURING_LOW_LIFT));
        button9.whenPressed(new InterpolateAndCheck(ArmPositions.PULL_IN_LOW));
        button10.whenPressed(new InterpolateAndCheck(ArmPositions.LIFT_ARM));
        button11.whenPressed(new TimedDrive(1.0, 0.5));
        //Go to HOME here
        //should we go to home while driving? or drive first? should it really be HOME?
        button12.whenPressed(new TimedDrive(1.5, 0.5)); */


        
        //MORE AUTONOMOUS-ISH STFF
        //uses limit switches to deposit balls and pick them up
        //button9.whenPressed(new DepositBallAtTarget(ArmPositions.LOW_CARGO));
        //button10.whenPressed(new DepositBallAtTarget(ArmPositions.MID_CARGO));
        //button11.whenPressed(new DepositBallAtTarget(ArmPositions.HIGH_CARGO));
        //topUR.whenPressed(new frc.robot.commandgroups.PickUpBallFromGround()); 
        


        // Controlling position selection
        // A - low hatch      A+bumber - low ball
        // B - mid hatch      B+bumber - mid ball
        // Y - high hatch     Y+bumber - high ball
        //xboxA.whenPressed(new SetRocketPosition(SetRocketPosition.LOWER));
        //xboxB.whenPressed(new SetRocketPosition(SetRocketPosition.MIDDLE));
        //xboxY.whenPressed(new SetRocketPosition(SetRocketPosition.UPPER));
        xboxA.whenPressed(new ArmInterpolateToTarget(ArmPositions.LOW_CARGO));
        xboxB.whenPressed(new ArmInterpolateToTarget(ArmPositions.MID_CARGO));
        xboxY.whenPressed(new ArmInterpolateToTarget(ArmPositions.HIGH_CARGO));
        xboxX.whenPressed(new ArmInterpolateToTarget(ArmPositions.HOME));
        //
        // These are test and calibration initializations - they are NOT required for competition.
        xbox = new XboxController(1);
        final POVButton decArmAngle = new POVButton(xbox, 0);
        decArmAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_ARM_ANGLE, BumpTargetPosition.DECREMENT));

        final POVButton incArmAngle = new POVButton(xbox, 180);
        incArmAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_ARM_ANGLE, BumpTargetPosition.INCREMENT));

        final POVButton decBucketAngle = new POVButton(xbox, 90);
        decBucketAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_BUCKET_ANGLE, BumpTargetPosition.DECREMENT));

        final POVButton incBucketAngle = new POVButton(xbox, 270);
        incBucketAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_BUCKET_ANGLE, BumpTargetPosition.INCREMENT));

    }
}
