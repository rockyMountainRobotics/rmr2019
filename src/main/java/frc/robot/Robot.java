/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  //create a component array 
  Component[] parts = new Component[10];
  //The number of parts
  int numParts = 0;

  //The camera
  UsbCamera driverCamUp;
  UsbCamera driverCamDown;
  CompressorSwitch cs;
  CameraServer cameraServer;

  @Override
  public void robotInit() {
    //Create the camera
    cameraServer = CameraServer.getInstance();
    driverCamUp = cameraServer.startAutomaticCapture(0);
    driverCamDown = cameraServer.startAutomaticCapture(1);
    System.out.println("Robot Init");

    //Initialize the components
    parts[numParts] = new Drive();
    numParts++;
    parts[numParts] = new CompressorSwitch();
    numParts++;
    parts[numParts] = new SwitchMode();
    numParts++;
    parts[numParts] = new BallManip();
    numParts++;
    parts[numParts] = new HatchManip();
    numParts++;
    parts[numParts] = new Elevator();
    numParts++;
    parts[numParts] = new Shifter();
    numParts++;
    parts[numParts] = new Wheelie();
    numParts++;
    
    //camera = CameraServer.getInstance().startAutomaticCapture();

  }


  @Override
  public void teleopPeriodic() {
    //Update the components
    for(int i = 0; i< numParts; i++)
    {
      parts[i].update();
    }
  }

  @Override
  public void autonomousPeriodic()
  {
    for(int i = 0; i < numParts; i++)
    {
      parts[i].autoUpdate();
    }
  }

}
