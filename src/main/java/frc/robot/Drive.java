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
import org.usfirst.frc.team662.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.IterativeRobot;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Drive implements Component {
  
    //A multipier for speed
    final double MULTIPLIER = .6;

  private WPI_TalonSRX leftFront;
  private WPI_TalonSRX rightFront;
  private WPI_TalonSRX leftBack;
  private WPI_TalonSRX rightBack;
  

  private SpeedControllerGroup leftDrive;
  private SpeedControllerGroup rightDrive;

  private DifferentialDrive m_myRobot;

  @Override
  public Drive() {
    leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
    rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
    leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
    rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);

    leftFront.setInverted(true);
    //leftBack.setInverted(true);
    //rightFront.setInverted(true);
    
    leftDrive = new SpeedControllerGroup(leftFront, leftBack);
    rightDrive = new SpeedControllerGroup(rightFront, rightBack);


    m_myRobot = new DifferentialDrive(leftDrive, rightDrive);

    
  }

  @Override
  public void update() {
    m_myRobot.arcadeDrive(MULTIPLIER *(-robotmap.driveController.getY(Hand.kLeft)), MULTIPLIER*(robotmap.driveController.getX(Hand.kLeft)));
    //m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
  }
   
    @Override
    public void autoUpdate() {

    }

    @Override
    public void disable() {

    }

}
