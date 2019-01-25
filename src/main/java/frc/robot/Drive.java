package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drive extends Component {
  
  //A multipier for speed
  final double MULTIPLIER = .6;

  //Motors
  private WPI_VictorSPX leftFront;
  private WPI_VictorSPX rightFront;
  private WPI_VictorSPX leftBack;
  private WPI_VictorSPX rightBack;
  

  //Combines the motors into left and right "sides" of the robot
  private SpeedControllerGroup leftDrive;
  private SpeedControllerGroup rightDrive;

  //Convenient for Driving
  private DifferentialDrive m_myRobot;


  //Constructors
  public Drive() {

    //Initialize motors using the ports that are in RobotMap
    leftFront = new WPI_VictorSPX(RobotMap.LEFT_FRONT_MOTOR);
    rightFront = new WPI_VictorSPX(RobotMap.RIGHT_FRONT_MOTOR);
    leftBack = new WPI_VictorSPX(RobotMap.LEFT_BACK_MOTOR);
    rightBack = new WPI_VictorSPX(RobotMap.RIGHT_BACK_MOTOR);

    //invert one motor :D
    leftFront.setInverted(true);
    
    //Initialize speed controllers
    leftDrive = new SpeedControllerGroup(leftFront, leftBack);
    rightDrive = new SpeedControllerGroup(rightFront, rightBack);

    //create differential drive
    m_myRobot = new DifferentialDrive(leftDrive, rightDrive);

    
  }

  @Override
  public void update() {
    //Drive!!
    m_myRobot.arcadeDrive(MULTIPLIER *(-RobotMap.driveController.getY(Hand.kLeft)), MULTIPLIER*(RobotMap.driveController.getX(Hand.kLeft)));
  
  }
   
    @Override
    public void autoUpdate() {
        // probably need to call update in here
    }

    @Override
    public void disable() {

    }

}
