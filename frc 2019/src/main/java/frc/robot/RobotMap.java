/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    /*
    defines the ports of each part of the robot
    r/lm1, 2, and 3 are the right/left drivetrain motors, with r/lm1 as the top & lead motor
    shifter 0 & 1 are the double solenoid ports for pneumatic shifting
    */
    public static int
            rm1 = 2,
            rm2 = 3,
            rm3 = 6,
            lm1 = 5,
            lm2 = 7,
            lm3 = 0,
            arm1 = 4,
            arm2 = 1,
            bucket = 8,
            lift = 0,
            shifter = 3,
            grabber = 2,
            servo = 1,
            cargoMotor = 0, //plugged into PWM port 0
            limitSwitch = 0, //need to get correct port number
            limitSwitch2 = 1; //need to get correct port number
}
