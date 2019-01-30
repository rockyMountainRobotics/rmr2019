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




import frc.robot.CasseroleRIOLoadMonitor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;







public class Robot extends TimedRobot {

  //camera init
  JeVoisInterface cam = null;
  Boolean teleopsent = false;
  
  //UsbCamera jevois = CameraServer.getInstance().startAutomaticCapture();

  @Override
  public void robotInit() {
   //cam.setCameraStreamActive(true);
  }
  //this also repeats
  @Override
  public void teleopPeriodic() {
    
    if (cam == null)
    {
      cam = new JeVoisInterface();
    }
    else
    {
      cam.start();
    }
    teleopsent = true;
    // System.out.println("telelop");
  }
  @Override
  public void disabledPeriodic()
  {
    if ((cam != null) && (teleopsent == true) )
    {
      cam.stop();
      teleopsent = false;
    }
  }
}

