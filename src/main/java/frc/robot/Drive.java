package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drive extends Component {
  
  //A multipier for speed - CHANGE BACK LOWER
  final double MULTIPLIER = 1;

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
    leftFront = new WPI_VictorSPX(RobotMap.LEFT_FRONT_DRIVE);
    rightFront = new WPI_VictorSPX(RobotMap.RIGHT_FRONT_DRIVE);
    leftBack = new WPI_VictorSPX(RobotMap.LEFT_BACK_DRIVE);
    rightBack = new WPI_VictorSPX(RobotMap.RIGHT_BACK_DRIVE);

    //invert one motor :D - usually at least one is mounted/wired weirdly? idk
    leftFront.setInverted(true);
    rightFront.setInverted(true);
    rightBack.setInverted(true);
    
    //Initialize speed controllers
    leftDrive = new SpeedControllerGroup(leftFront, leftBack);
    rightDrive = new SpeedControllerGroup(rightFront, rightBack);

    //create differential drive
    m_myRobot = new DifferentialDrive(leftDrive, rightDrive);

    
  }

  @Override
  public void update() {
    
    //Drive Based on Vision Code (Will only trigger if RB is held)
    if (RobotMap.driveController.getRawButton(XboxMap.RB))//If RB Button Pressed
    {
      if (dir1 == "RIGHT") //If told to go left by vision code...
      {
        m_myRobot.arcadeDrive(MULTIPLIER * 3, 0);// Arbitrary Number, fix later
      } 
      else if(dir1 == "LEFT")//If told to go left by vision code...
      {
        m_myRobot.arcadeDrive(0, MULTIPLIER * 3);// Arbitrary Number, fix later
      } 
      else if (dir1 == "STRAIGHT")//If told to go Straight by vision code...
      {
        m_myRobot.arcadeDrive(MULTIPLIER * 3, MULTIPLITER * 3);// 3is an arbitrary Number, fix later
      } 
    }
    // End of Vision Code Addon
    
    
    //Drive!! (Normally)
    m_myRobot.arcadeDrive(MULTIPLIER *(-RobotMap.driveController.getY(Hand.kLeft)), MULTIPLIER*(RobotMap.driveController.getX(Hand.kLeft)));
    
  }
   
    @Override
    public void autoUpdate() {
        
        update();
    }

    @Override
    public void disable() {
      leftBack.set(0);
      leftFront.set(0);
      rightBack.set(0);
      rightFront.set(0);
    }

}
