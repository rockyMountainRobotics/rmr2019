package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drive implements Component {
  
    //A multipier for speed
    final double MULTIPLIER = .6;

    //Motors
  private WPI_TalonSRX leftFront;
  private WPI_TalonSRX rightFront;
  private WPI_TalonSRX leftBack;
  private WPI_TalonSRX rightBack;
  

  //Combines the motors
  private SpeedControllerGroup leftDrive;
  private SpeedControllerGroup rightDrive;

  //Convenient for Driving
  private DifferentialDrive m_myRobot;


  //Constructors
  public Drive() {

    //Initialize motors
    leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
    rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
    leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
    rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);

    //invert one motor :D
    leftFront.setInverted(true);
    
    //Initialize speed controllers
    leftDrive = new SpeedControllerGroup(leftFront, leftBack);
    rightDrive = new SpeedControllerGroup(rightFront, rightBack);

    //create differential drives
    m_myRobot = new DifferentialDrive(leftDrive, rightDrive);

    
  }

  @Override
  public void update() {
    //Drive!!
    m_myRobot.arcadeDrive(MULTIPLIER *(-RobotMap.driveController.getY(Hand.kLeft)), MULTIPLIER*(RobotMap.driveController.getX(Hand.kLeft)));
  
  }
   
    @Override
    public void autoUpdate() {

    }

    @Override
    public void disable() {

    }

}
