/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Mat;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;

/**
 * Add your docs here.
 */
public class GripDetection extends Subsystem {
  
	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;
	UsbCamera camera;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public GripDetection(){
    camera = CameraServer.getInstance().startAutomaticCapture();
  }
  public void startVision() {
    //dont know why this is deprecated. help? it works, but i really hate the green lines.
    HttpCamera piCam = new HttpCamera("piCam", "http://10.23.170.203:1180/?action=stream", HttpCameraKind.kMJPGStreamer);
    
    CvSink cvSink1 = CameraServer.getInstance().getVideo(piCam);
    
    CvSource outputStream = CameraServer.getInstance().putVideo("Camera Stream", 640, 480);
    
    Mat image = new Mat();

    cvSink1.grabFrame(image);

    outputStream.putFrame(image);
  }
  @Override
  public void initDefaultCommand() {
  }
}