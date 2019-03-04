/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import frc.robot.commands.Lifter;

/**
 * The robot lift pneumatics
 */
public class Lift extends Subsystem {
    private final Solenoid leftSolenoid = new Solenoid(RobotMap.liftLeft);
    private final Solenoid rightSolenoid = new Solenoid(RobotMap.liftRight);

    public Lift() {
        super();
    }

    @Override
    public void initDefaultCommand() {
    }

    public void lift_robot_left() {
        leftSolenoid.set(true);
    }
    public void retract_robot_left() {
        leftSolenoid.set(false);
    }

    public void lift_robot_right() {
        rightSolenoid.set(true);
    }

    public void retract_robot_right() {
        rightSolenoid.set(false);
    }

    public void retract_lifters() {
        leftSolenoid.set(false);
        rightSolenoid.set(false);
    }

}
