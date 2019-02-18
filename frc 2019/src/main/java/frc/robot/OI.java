/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commandgroups.DownerAndLand;
import frc.robot.commandgroups.DriveAndPark;
import frc.robot.commandgroups.LiftAndDuringLift;
import frc.robot.commands.*;
import frc.robot.commands.BumpTargetPosition;
import frc.robot.subsystems.ArmPositions;
import frc.robot.commands.MoveServo;
import frc.robot.commands.SetArmHeight;
import frc.robot.commands.Shift;
import frc.robot.commands.TapeFind;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    //creation of the joystick and xbox Controller object
    private final Joystick stick = new Joystick(0);

    private final JoystickButton trigger = new JoystickButton(this.stick, 1);
    private final JoystickButton thumb = new JoystickButton(this.stick, 2);
    private final JoystickButton top = new JoystickButton(this.stick, 3);
    private final JoystickButton top2 = new JoystickButton(this.stick, 4);
    private final JoystickButton button5 = new JoystickButton(this.stick, 5);
    private final JoystickButton button6 = new JoystickButton(this.stick, 6);
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
    private final JoystickButton xboxY= new JoystickButton(xbox, 4);

    public Joystick getStick() {
        //method to be called by other commands or subsystems to use the joystick
        return (stick);
    }

    /**
     * Get the gamepad
     * @return (XboxController) The gamepad if calibration is enabled, <tt>null</tt> otherwise.
     */
    public XboxController getXbox() {
        return (xbox);
    }

    public OI() {
        trigger.whenPressed(new Shift(true));
        thumb.whenPressed(new Shift(false));
        top.whenPressed(new MoveServo(0));
        top2.whenPressed(new MoveServo(1));
//        button5.whenPressed(new TapeStraighten('L'));
//        button6.whenPressed(new TapeStraighten('R'));
//        button7.whileHeld(new TapeStraighten('L'));
//        button8.whileHeld(new TapeStraighten('R'));
        /*button9.whenPressed(new Lifter(Lifter.LIFT_ROBOT));
        button10.whenPressed(new Lifter(Lifter.RETRACT_LIFTERS)); */
        

        //buttons for testing all end game lift code
        button7.whenPressed(new SetAndWaitForArmPosition(ArmPositions.PRE_ENDGAME_LIFT));
        button8.whenPressed(new LiftAndDuringLift());
        button9.whenPressed(new SetAndWaitForArmPosition(ArmPositions.ENDGAME_LIFT));
        button10.whenPressed(new DownerAndLand());
        button11.whenPressed(new DriveAndPark());
        button12.whenPressed(new SetAndWaitForArmPosition(ArmPositions.POST_ENDGAME_PARK));
        

        // Controlling position selection
        // A - low hatch      A+bumber - low ball
        // B - mid hatch      B+bumber - mid ball
        // Y - high hatch     Y+bumber - high ball
        xboxA.whenPressed(new SetRocketPosition(SetRocketPosition.LOWER));
        xboxB.whenPressed(new SetRocketPosition(SetRocketPosition.MIDDLE));
        xboxY.whenPressed(new SetRocketPosition(SetRocketPosition.UPPER));
        xboxX.whenPressed(new SetArmTarget(ArmPositions.HOME));
        //
        // These are test and calibration initializations - they are NOT required for competition.
        xbox = new XboxController(1);
        final POVButton decArmAngle = new POVButton(xbox, 0);
        decArmAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_ARM_ANGLE,BumpTargetPosition.DECREMENT));

        final POVButton incArmAngle = new POVButton(xbox,180);
        incArmAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_ARM_ANGLE,BumpTargetPosition.INCREMENT));

        final POVButton decBucketAngle = new POVButton(xbox, 90);
        decBucketAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_BUCKET_ANGLE,BumpTargetPosition.DECREMENT));

        final POVButton incBucketAngle = new POVButton(xbox,270);
        incBucketAngle.whileHeld(
                new BumpTargetPosition(BumpTargetPosition.BUMP_BUCKET_ANGLE,BumpTargetPosition.INCREMENT));
    
            }
}
