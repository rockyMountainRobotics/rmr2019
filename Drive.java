package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive extends Component {
  
  //A multipier for speed - CHANGE BACK LOWER
  double MULTIPLIER = 1.0;

  DoubleSolenoid shifter;
	boolean current = true;
	boolean past = false;

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

    shifter = new DoubleSolenoid(RobotMap.SHIFTER_HIGH, RobotMap.SHIFTER_LOW);
    
  }

  @Override
  public void update() {
    //Drive!!
    m_myRobot.arcadeDrive(MULTIPLIER *(-RobotMap.driveController.getY(Hand.kLeft)), (RobotMap.driveController.getX(Hand.kLeft)));
    current = RobotMap.driveController.getRawButton(XboxMap.B);
		
		if(current == true && past == false){
            if(shifter.get() == DoubleSolenoid.Value.kForward)
            {
              shifter.set(DoubleSolenoid.Value.kReverse);
              System.out.println("HIGH");
              MULTIPLIER = .8;
            }
            else
            {
              shifter.set(DoubleSolenoid.Value.kForward);
              System.out.println("LOW");
              MULTIPLIER = 1;
            }
		}
		SmartDashboard.putBoolean("Gear", shifter.get().equals(DoubleSolenoid.Value.kReverse));
    past = current;
    
    System.out.println("Gear" + shifter.get().equals(DoubleSolenoid.Value.kReverse));
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
/*
public class Shifter extends Component
{
	DoubleSolenoid shifter;
	boolean current = true;
	boolean past = false;

	//Constructor
    public Shifter()
    {
        shifter = new DoubleSolenoid(RobotMap.SHIFTER_HIGH, RobotMap.SHIFTER_LOW);
    }
	
	public void update()
	{
		current = RobotMap.driveController.getRawButton(XboxMap.B);
		
		if(current == true && past == false){
            if(shifter.get() == DoubleSolenoid.Value.kForward)
            {
                shifter.set(DoubleSolenoid.Value.kReverse);
                Drive.MULTIPLIER = 1;
            }
            else
            {
                shifter.set(DoubleSolenoid.Value.kForward);
            }
		}
		SmartDashboard.putBoolean("Gear", shifter.get().equals(DoubleSolenoid.Value.kForward));
		past = current;
	}


	@Override
	public void autoUpdate() 
	{
		
	}


	@Override
	public void disable() 
	{
		
	}
	
	
}
*/