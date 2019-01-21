package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class RobotMap
{
    ////////////THESE ARE THE SAME PORTS AS LAST YEAR, NOT NECESSARIlY CORRECT!///////////
    //Drive motor ports
	  public final static int RIGHT_FRONT_MOTOR = 6;
  	public final static int LEFT_FRONT_MOTOR = 7;
	  public final static int RIGHT_BACK_MOTOR = 5;
    public final static int LEFT_BACK_MOTOR = 8;
	
	  public final static int TOP_LEFT_MOTOR = 1;
	  public final static int TOP_RIGHT_MOTOR = 1;
	  public final static int TOP_CENTER_MOTOR = 1;
	  public final static int ELEVATOR = 0;
	
	  public final static int BEAK_SOLENOID = 0;
	  public final static int TRACK_SOLENOID = 1;
	
	  public final static int LIMIT_TOP = 0;
	  public final static int LIMIT_BOTTOM = 0;
    
    //Controllers
    public static XboxController driveController = new XboxController(0);
    public static XboxController manipController = new XboxController(1);
}
