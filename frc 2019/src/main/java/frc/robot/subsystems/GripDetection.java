/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.GripPipeline;

import java.sql.Array;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * Add your docs here.
 */
public class GripDetection extends Subsystem {
  
	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;
	
  private VisionThread visionThread;
  private boolean tapeSeen = false;

  private double 
    centerX1 = 0.0,
    centerX2 = 0.0,
    centerY1 = 0.0,
    centerY2 = 0.0,
    Height1,
    Height2,
    Width1,
    Width2;
  
  private double[] 
    coords1 = new double[]{0.0,0.0},
    coords2 = new double[]{0.0,0.0};
  UsbCamera camera;
  double[] motorPower = new double[]{0,0};
	
  private final Object 
    imgLockCX1 = new Object(),
    imgLockCX2 = new Object(),
    imgLockCY1 = new Object(),
    imgLockCY2 = new Object(),
    
    imgLockCW1 = new Object(),
    imgLockCW2 = new Object(),
    imgLockCH1 = new Object(),
    imgLockCH2 = new Object(),
    
    imgLockSEEN = new Object();
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public GripDetection(){
    camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    //dont know why this is deprecated. help? it works, but i really hate the green lines.
    visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
        if (!pipeline.filterContoursOutput().isEmpty()) {
          if(pipeline.filterContoursOutput().size()>=2){
            Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
            synchronized (imgLockCX1) {centerX1 = r1.x + (r1.width / 2);}
            synchronized (imgLockCX2) {centerX2 = r2.x + (r2.width / 2);}
            synchronized (imgLockCY1) {centerY1 = r1.y + (r1.height / 2);}
            synchronized (imgLockCY2) {centerY2 = r2.y + (r2.height / 2);}

            synchronized (imgLockCW1) {Width1 = r1.width;}
            synchronized (imgLockCW2) {Width2 = r2.width;}
            synchronized (imgLockCH1) {Height1 = r1.height;}
            synchronized (imgLockCH2) {Height2 = r2.height;}

            synchronized (imgLockSEEN) {tapeSeen=(pipeline.filterContoursOutput().size()>1);};
            if(pipeline.filterContoursOutput().size()>2){
              System.out.print("seeing more than 2");
              System.out.println();
            }
          }
        }
      });

    }
  public void startVision(){
    visionThread.start();
  }
  public void stopVision(){
    visionThread.stop();
  }
  public double[] findTape(char direction){
    double mlPower=0;
    double mrPower=0;
    boolean tapeSeen;
    synchronized(imgLockSEEN){tapeSeen=this.tapeSeen;};
    if(tapeSeen){
      if(direction=='L'){
        mlPower=-.2;
        mrPower= .2;
      }
      if(direction=='R'){
        mlPower= .2;
        mrPower=-.2;
      }
    }
    else{
      mlPower=0;
      mrPower=0;
    }
    motorPower[0]=mlPower;
    motorPower[1]=mrPower;
    return(motorPower);

  }
  private double[] sendWidthHeight1(){
    double Width1;
    double Height1;
    synchronized (imgLockCW1) {Width1=this.Width1;}
    synchronized (imgLockCH1) {Height1=this.Height1;}
    double[] WidthHeight1 = new double[]{Width1,Height1};
    return WidthHeight1;
  }
  private double[] sendWidthHeight2(){
    double Width2;
    double Height2;
    synchronized (imgLockCW2) {Width2=this.Width2;}
    synchronized (imgLockCH2) {Height2=this.Height2;}
    double[] WidthHeight1 = new double[]{Width2,Height2};
    return WidthHeight1;
  }
  public double[] sendXY1(){
    double centerX1;
    double centerY1;
    synchronized (imgLockCX1){centerX1=this.centerX1;}
    synchronized (imgLockCY1){centerY1=this.centerY1;}
    coords1[0]=centerX1;
    coords1[1]=centerY1;
    return(coords1);
  }
  public double[] sendXY2(){
    double centerX2;
    double centerY2;
    synchronized (imgLockCX2){centerX2=this.centerX2;}
    synchronized (imgLockCY2){centerY2=this.centerY2;}
    coords2[0]=centerX2;
    coords2[1]=centerY2;
    return(coords2);
  }
  public void initialRect(){

  }
  public double slowToRect(){
    double width1;
    double height1;
    double width2;
    double height2;
    double powerRectH;
    double powerRectW;
    double powerRectM;
    double rectWT=IMG_WIDTH/3;
    double rectHT=IMG_HEIGHT/3;
    synchronized(imgLockCH1){height1=this.Height1;};
    synchronized(imgLockCW1){width1=this.Width1;};
    synchronized(imgLockCH2){height2=this.Height2;};
    synchronized(imgLockCW2){width2=this.Width2;};
    powerRectH=((((height1+height2)/2)-rectHT)/rectHT);
    powerRectW=((((width1+width2)/2)-rectWT)/rectWT);
    powerRectM=((powerRectH+powerRectW)/2);
    return(powerRectM);
  }
  @Override
  public void initDefaultCommand() {
  }
}