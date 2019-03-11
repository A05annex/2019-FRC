package frc.robot.commands.drivingauto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IUseDriveTrain;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;

//import frc.robot.RobotMap;
//import frc.robot.subsystems.DriveTrain;

public class Drive4Enc extends Command {

    public double desiredEnc;
    public double motorPower;
    
    public Drive4Enc(int desiredEnc, double motorPower) {
        this.desiredEnc = desiredEnc;
    	requires((Subsystem)Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //SetTargetEnc();
       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //CorrectEnc();
        if (Robot.driveTrain.getRightPosition() < desiredEnc) {
            Robot.driveTrain.inputDriveSG(motorPower*0.1*(desiredEnc - Robot.driveTrain.getRightPosition()));
        }
        else if (Robot.driveTrain.getRightPosition() > desiredEnc) {
            Robot.driveTrain.inputDriveSG(motorPower*-0.1*(Robot.driveTrain.getRightPosition() - desiredEnc));
        }
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.driveTrain.getRightPosition() - desiredEnc) < 5) {
            /*Robot.driveTrain.rightMaster.set(0);
            Robot.driveTrain.leftMaster.set(0);*/
            return true;
        }

            return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.inputDriveSG(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}