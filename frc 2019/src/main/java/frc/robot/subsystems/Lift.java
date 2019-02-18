/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import frc.robot.commands.Lifter;

/**
 * The robot lift pneumatics
 */
public class Lift extends Subsystem {
    private final DoubleSolenoid liftSolenoid = new DoubleSolenoid(RobotMap.lift1, RobotMap.lift2);

    public Lift() {
        super();
    }

    @Override
    public void initDefaultCommand() {
    }

    /**
     * Lifts the robot lift_robot. Only needs to be applied for a short time to move the switching piston.
     */
    public void lift_robot() {
        liftSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * Lowers the robot. Only needs to be applied for a short time to move the switching piston.
     */
    public void retract_lifters() {
        liftSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * No power to the cylinder solenoids, the cylinders stay in the position they are
     */
    public void off() {
        liftSolenoid.set(DoubleSolenoid.Value.kOff);
    }

  
}
