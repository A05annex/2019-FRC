/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Lifter;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    public final static IUseDriveTrain driveTrain = Constants.COMPETITION_ROBOT ? 
            new DriveTrain() : new DriveTrainPractice();
    public final static IUseArm armDriveTrain =  Constants.COMPETITION_ROBOT ? new ArmDriveTrain() : null;
    //public static IUseArm armDriveTrain = new ArmDriveSrx();
    public final static GripDetection gripDetection = new GripDetection();
    private static OI oi;
    public final static Bucket bucket = Constants.COMPETITION_ROBOT ? new Bucket() : null;
    public final static GripDetection grip = new GripDetection();
    public final static BucketWheelz bucketWheelz = new BucketWheelz();
    public final static Grabber grabber = new Grabber();
    public final static Lift lift = Constants.COMPETITION_ROBOT ? new Lift() : null;
    public final static ArmInterpolate armInterpolate = new ArmInterpolate();
    public final static BucketLimitSwitch bucketLimitSwitch = new BucketLimitSwitch();
    private Command m_autonomousCommand;
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        // chooser.addOption("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", m_chooser);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().removeAll();
        for (int i = 0; i < 10; i++) {
            SmartDashboard.putString("DB/String " + Integer.toString(i), " ");
        }
        if (null != armDriveTrain) {
            SmartDashboard.putString("DB/String 2", Double.toString(armDriveTrain.getLowerArmAngle()));
            SmartDashboard.putString("DB/String 3", Double.toString(armDriveTrain.getUpperArmAngle()));
        }
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        //driveTrain.rightMotor.setSelectedSensorPosition(0);
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        // Make sure the lifters are retracted before we start moving around.
        if (Constants.COMPETITION_ROBOT) {
            new Lifter(Lifter.RETRACT_LIFTERS).start();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if (null != armDriveTrain) {
            SmartDashboard.putString("DB/String 2", Double.toString(armDriveTrain.getLowerArmAngle()));
            SmartDashboard.putString("DB/String 3", Double.toString(armDriveTrain.getUpperArmAngle()));
        }
        //SmartDashboard.putString("DB/String 4", Double.toString(armDriveTrain.getBucketAngle()));
        //SmartDashboard.putString("DB/String 5", Boolean.toString(armDriveTrain.isAtTargetPosition()));

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    public static OI getOI() {
        return oi;
    }
}
