/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSource;


public class Robot extends TimedRobot {

  //camera init
  SerialPort cam;
  
  //UsbCamera jevois = CameraServer.getInstance().startAutomaticCapture();

  @Override
  public void robotInit() {
    cam = new SerialPort(115200, SerialPort.Port.kUSB);
  }
  //this also repeats
  @Override
  public void teleopPeriodic() {
    System.out.println("pinging Jevois");
    String cmd = "ping";
    int bytes = cam.writeString(cmd + "\n");
    System.out.println("wrote  "+ bytes + "/" + cmd.length() + "bytes");
    //try to read the string
      try{
        if (cam.getBytesReceived() > 0)
        {
          System.out.println(cam.readString());
        }
        else
        {
          System.out.println("No data read");
        }
      } catch (Exception e) {
        //if it fails, print an error message
        System.out.println("Error: " + e);
      }
  }
}

