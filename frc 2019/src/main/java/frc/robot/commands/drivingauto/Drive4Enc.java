package frc.robot.commands.drivingauto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.subsystems.DriveTrain;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;

//import frc.robot.RobotMap;
//import frc.robot.subsystems.DriveTrain;

public class Drive4Enc extends Command {

    public double desiredEnc;
    public double motorPower;
    
    public Drive4Enc(int desiredEnc, double motorPower) {
        this.desiredEnc = desiredEnc;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //SetTargetEnc();
       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //CorrectEnc();
        if (Robot.driveTrain.rightMaster.getSelectedSensorPosition() < desiredEnc) {
            Robot.driveTrain.rightMaster.set(motorPower*0.1*(desiredEnc - Robot.driveTrain.rightMaster.getSelectedSensorPosition()));
            Robot.driveTrain.leftMaster.set(motorPower*0.1*(desiredEnc - Robot.driveTrain.rightMaster.getSelectedSensorPosition()));
        }
        else if (Robot.driveTrain.rightMaster.getSelectedSensorPosition() > desiredEnc) {
            Robot.driveTrain.rightMaster.set(-motorPower*0.1*(Robot.driveTrain.rightMaster.getSelectedSensorPosition() - desiredEnc));
            Robot.driveTrain.leftMaster.set(-motorPower*0.1*(Robot.driveTrain.rightMaster.getSelectedSensorPosition() - desiredEnc));
        }
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.driveTrain.rightMaster.getSelectedSensorPosition() - desiredEnc) < 5) {
            Robot.driveTrain.rightMaster.set(0);
            Robot.driveTrain.leftMaster.set(0);
            return true;
        }

            return false;
    
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}