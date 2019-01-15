/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.IterativeRobot;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  
  private WPI_TalonSRX leftFront;
  private WPI_TalonSRX rightFront;
  private WPI_TalonSRX leftBack;
  private WPI_TalonSRX rightBack;
  

  private SpeedControllerGroup leftDrive;
  private SpeedControllerGroup rightDrive;
  
  XboxController driveController;

  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  @Override
  public void robotInit() {
    leftFront = new WPI_TalonSRX(7);
    rightFront = new WPI_TalonSRX(6);
    leftBack = new WPI_TalonSRX(8);
    rightBack = new WPI_TalonSRX(5);

    leftFront.setInverted(true);
    //leftBack.setInverted(true);
    //rightFront.setInverted(true);
    
    leftDrive = new SpeedControllerGroup(leftFront, leftBack);
    rightDrive = new SpeedControllerGroup(rightFront, rightBack);


    m_myRobot = new DifferentialDrive(leftDrive, rightDrive);

    driveController = new XboxController(0);
    //m_leftStick = new Joystick(0);
    //m_rightStick = new Joystick(1);

    
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.arcadeDrive(.6 *(-driveController.getY(Hand.kLeft)), .6*(driveController.getX(Hand.kLeft)));
    //m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
  }
}
