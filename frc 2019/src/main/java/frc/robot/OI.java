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
import frc.robot.commandgroups.LiftToPlatform;
import frc.robot.commands.BumpTargetPosition;
import frc.robot.commands.MoveArmToTarget;
import frc.robot.commands.SetArmTarget;
import frc.robot.subsystems.ArmPositions;

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
    private XboxController xbox = null;



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
        /*
        trigger.whenPressed(new Shift(true));
        thumb.whenPressed(new Shift(false));
        top.whenPressed(new MoveServo(0));
        top2.whenPressed(new MoveServo(1));
        //button5.whenPressed(new TapeStraighten('L'));
        //button6.whenPressed(new TapeStraighten('R'));
        button7.whileHeld(new TapeStraighten('L'));
        button8.whileHeld(new TapeStraighten('R'));
        button9.whenPressed(new Lifter(Lifter.LIFT_ROBOT));
        button10.whenPressed(new Lifter(Lifter.RETRACT_LIFTERS));
        */

        trigger.whenPressed(new SetArmTarget(ArmPositions.HOME));
        thumb.whenPressed(new SetArmTarget(ArmPositions.MID_CARGO));
        button5.whenPressed(new MoveArmToTarget());
        button12.whenPressed(new LiftToPlatform());

        // These are test and calibration initializations - they are NOT required for competition.
        /*if (Constants.ENABLE_CALIBRATION) {
            xbox = new XboxController(1);
            final POVButton decArmAngle = new POVButton(xbox, xbox.getPOV(0));
            decArmAngle.whenPressed(xbox.getBumper(GenericHID.Hand.kLeft) ?
                    new BumpTargetPosition(0.0, -Constants.BUMP_INCREMENT, 0.0) :
                    new BumpTargetPosition(-Constants.BUMP_INCREMENT, 0.0, 0.0));

            final POVButton incArmAngle = new POVButton(xbox, xbox.getPOV(180));
            incArmAngle.whenPressed(xbox.getBumper(GenericHID.Hand.kLeft) ?
                    new BumpTargetPosition(0.0, Constants.BUMP_INCREMENT, 0.0) :
                    new BumpTargetPosition(Constants.BUMP_INCREMENT, 0.0, 0.0));

            final POVButton decBucketAngle = new POVButton(xbox, xbox.getPOV(90));
            decBucketAngle.whenPressed(new BumpTargetPosition(0.0, 0.0, -Constants.BUMP_INCREMENT));

            final POVButton incBucketAngle = new POVButton(xbox, xbox.getPOV(270));
            incBucketAngle.whenPressed(new BumpTargetPosition(0.0, 0.0, Constants.BUMP_INCREMENT));
        }*/

    }
}
