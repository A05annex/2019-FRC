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
import frc.robot.commands.MoveServo;
import frc.robot.commands.Shift;
import frc.robot.commands.TapeStraighten;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    //creation of the joystick and xbox Controller object
    private final Joystick stick = new Joystick(0);
    private final XboxController xbox = new XboxController(1);

    JoystickButton trigger = new JoystickButton(this.stick, 1);
    JoystickButton thumb = new JoystickButton(this.stick, 2);
    JoystickButton top = new JoystickButton(this.stick, 3);
    JoystickButton top2 = new JoystickButton(this.stick, 4);
    JoystickButton button5 = new JoystickButton(this.stick, 5);
    JoystickButton button6 = new JoystickButton(this.stick, 6);
    JoystickButton button7 = new JoystickButton(this.stick, 7);
    JoystickButton button8 = new JoystickButton(this.stick, 8);
    JoystickButton button9 = new JoystickButton(this.stick, 9);
    JoystickButton button10 = new JoystickButton(this.stick, 10);

    public Joystick getStick() {
        //method to be called by other commands or subsystems to use the joystick
        return (stick);
    }

    public XboxController getXbox() {
        return (xbox);
    }

    public OI() {
        trigger.whenPressed(new Shift(true));
        thumb.whenPressed(new Shift(false));
        top.whenPressed(new MoveServo(0));
        top2.whenPressed(new MoveServo(1));
        button7.whileHeld(new TapeStraighten('L'));
        button8.whileHeld(new TapeStraighten('R'));
        //button5.whenPressed(new TapeStraighten('L'));
        //button6.whenPressed(new TapeStraighten('R'));

    }
}
