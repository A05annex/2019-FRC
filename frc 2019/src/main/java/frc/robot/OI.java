/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveServo;
import frc.robot.commands.Shift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //creation of the joystick
  public Joystick stick = new Joystick(0);

  JoystickButton trigger = new JoystickButton(this.stick, 1);
  JoystickButton thumb = new JoystickButton(this.stick, 2);
  JoystickButton top = new JoystickButton(this.stick, 3);
  JoystickButton top2 = new JoystickButton(this.stick, 4);

  public Joystick getStick(){
  //method to be called by other commands or subsystems to use the joystick
    return(this.stick);
  }

  public OI(){
    trigger.whenPressed(new Shift(true));
    thumb.whenPressed(new Shift(false));
    top.whenPressed(new MoveServo(0));
    top2.whenPressed(new MoveServo(1));
  }
}
