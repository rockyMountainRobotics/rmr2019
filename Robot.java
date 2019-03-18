/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.video.Video;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoSink;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  //create a component array 
  Component[] parts = new Component[10];
  //The number of parts
  int numParts = 0;

  //Boolean to keep track of camera. False is 
  boolean cameraType = false;

  //The camera
  UsbCamera driverCamUp;
  UsbCamera driverCamDown;
  CompressorSwitch cs;
  CameraServer cameraServer;
  VideoSink server;

  boolean currentPress;
  boolean pastPress;
  
  @Override
  public void robotInit() {
    //Creates Cameras
    cameraServer = CameraServer.getInstance();
    driverCamUp = cameraServer.startAutomaticCapture(0);
    driverCamDown = cameraServer.startAutomaticCapture(1);
    //Creates server with ability to switch between two camera feeds
    server = CameraServer.getInstance().addSwitchedCamera("Switched camera");
    //Allows the cameras to be switched between.
    driverCamUp.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    driverCamDown.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);

    server.setSource(driverCamDown);

    System.out.println("Robot Init");

    //Initialize the components
    parts[numParts] = new Drive();
    numParts++;
    parts[numParts] = new CompressorSwitch();
    numParts++;
    //parts[numParts] = new SwitchMode();
    //numParts++;
    parts[numParts] = new BallManip();
    numParts++;
    parts[numParts] = new HatchManip();
    numParts++;
    parts[numParts] = new Elevator();
    numParts++;
    //parts[numParts] = new Shifter();
    //numParts++;
    parts[numParts] = new Wheelie();
    numParts++;
    
    //camera = CameraServer.getInstance().startAutomaticCapture();

  }


  @Override
  public void teleopPeriodic() {
    //Changes cameras

    currentPress = RobotMap.driveController.getRawButton(XboxMap.RB)==true;      
		
		  if(currentPress == true && pastPress == false){
			  cameraType = !cameraType;
		  }

      pastPress = currentPress;


    /*if(RobotMap.driveController.getRawButton(XboxMap.RB)==true)
    {
      cameraType = !cameraType;
    }*/
    if(cameraType)
    {
      
      System.out.println("Set to camera down");
      server.setSource(driverCamDown);
    }
    else
    {
      System.out.println("Setting to camera up");
      server.setSource(driverCamUp);
    }
    //Updates components
    for(int i = 0; i< numParts; i++)
    {
      parts[i].update();
    }
  }

  @Override
  public void autonomousPeriodic()
  {
    teleopPeriodic();
    /*for(int i = 0; i < numParts; i++)
    {
      parts[i].autoUpdate();
    }*/
  }

}
