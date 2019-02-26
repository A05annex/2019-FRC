package frc.robot.commands.drivingauto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.subsystems.DriveTrain;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Turn4Enc extends Command {

    public int desiredDeg;
    public double motorPower;
    public int AHRS;
    public AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
    
    public Turn4Enc(int desiredDeg, double motorPower) {
        this.desiredDeg = desiredDeg;
        requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //SetTargetDeg(); 
        //Robot.driveTrain.ahrs.isCalibrating();  
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //CorrectEnc();
    if (Robot.DriveTrain.AHRS.getAngle() < desiredDeg) {
        Robot.DriveTrain.rightMaster.set((-motorPower*0.1*(desiredDeg - Robot.DriveTrain.AHRS.getAngle())));
        Robot.DriveTrain.leftMaster.set((motorPower*0.1*(desiredDeg - Robot.DriveTrain.AHRS.getAngle())));
    }
    else if (Robot.DriveTrain.ahrs.getAngle() > desiredDeg) {
        Robot.DriveTrain.rightMaster.set((motorPower*0.1*(Robot.driveTrain.AHRS.getAngle() - desiredDeg)));
        Robot.DriveTrain.leftMaster.set((-motorPower*0.1*(Robot.driveTrain.AHRS.getAngle() - desiredDeg)));
    }

   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.DriveTrain.AHRS.getAngle() - desiredDeg) < 5) {
            Robot.DriveTrain.rightMaster.set(0);
            Robot.DriveTrain.leftMaster.set(0);
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