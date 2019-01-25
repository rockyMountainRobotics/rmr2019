/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CameraServer;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  //create a component array 
  Component[] parts = new Component[10];



  @Override
  public void robotInit() {

    //Initialize a Drive Component
    parts[0] = new Drive();
    parts[1] = new HatchManip();
    parts[2] = new BallManip();
    parts[3] = new Elevator();
    parts[4] = new SwitchMode();
    camera = CameraServer.getInstance().startAutomaticCapture();

  }

  @Override
  public void teleopPeriodic() {

    //Update the components
    for(int i = 0; i< 5; i++)
    {
      parts[i].update();
    }
  }
}
