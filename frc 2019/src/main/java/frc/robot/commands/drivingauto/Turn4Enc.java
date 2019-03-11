package frc.robot.commands.drivingauto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.subsystems.DriveTrain;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.IUseDriveTrain;


public class Turn4Enc extends Command {

    public int desiredDeg;
    public double motorPower;
    public int AHRS;
    public AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
    
    public Turn4Enc(int desiredDeg, double motorPower) {
        this.desiredDeg = desiredDeg;
        requires((Subsystem)Robot.driveTrain);
    }

    protected void initialize() {
        //SetTargetDeg(); 
        //Robot.driveTrain.ahrs.isCalibrating();
        //Robot.driveTrain.AHRS.getActualUpdateRate();
        }  
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //CorrectEnc();
    if (Robot.driveTrain.getHeading() < desiredDeg) {
        Robot.driveTrain.inputDrive((-motorPower*0.1*(desiredDeg - Robot.driveTrain.getHeading())), motorPower*0.1*(desiredDeg - Robot.driveTrain.getHeading()));
    }
    else if (Robot.driveTrain.getHeading() > desiredDeg) {
        Robot.driveTrain.inputDrive((motorPower*0.1*(desiredDeg - Robot.driveTrain.getHeading())), -motorPower*0.1*(desiredDeg - Robot.driveTrain.getHeading()));
    }
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.driveTrain.getHeading() - desiredDeg) < 5) {
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