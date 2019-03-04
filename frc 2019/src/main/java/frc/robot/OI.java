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
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    //creation of the joystick and xbox Controller object
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
    private final JoystickButton bumperLeft = new JoystickButton(xbox, 5);
    private final JoystickButton bumperRight = new JoystickButton(xbox, 6);
    private final POVButton povUp = new POVButton(xbox, 0);
    private final POVButton povDown = new POVButton(xbox, 180);
    private final POVButton povRight = new POVButton(xbox, 90);
    private final POVButton povLeft = new POVButton(xbox, 270);


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
        button7.whenPressed(new SynchronisedLift());
        button8.whenPressed(new RetractLift());
    }
}
