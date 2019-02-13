package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class RobotMap
{
    
    //Drive motor ports
	public final static int RIGHT_FRONT_DRIVE = 4;
  	public final static int LEFT_FRONT_DRIVE = 1;
	public final static int RIGHT_BACK_DRIVE = 5;
    public final static int LEFT_BACK_DRIVE = 2;
	
	//Arms motors
	public final static int LEFT_BALL_GRABBER = 3;
	public final static int RIGHT_BALL_GRABBER = 6;
	public final static int BALL_ARM_PIVOT = 7;
	public final static int ELEVATOR = 8;
	
	//Solenoids
	public final static int BEAK_SOLENOID_IN = 3;
	public final static int BEAK_SOLENOID_OUT = 2;
	public final static int TRACK_SOLENOID_IN = 1;
	public final static int TRACK_SOLENOID_OUT = 0;
	public final static int LIFTER_SOLENOID = 6;
	public final static int SHIFTER_LOW = 4;
	public final static int SHIFTER_HIGH = 5;
	
	//Limit Switches
	public final static int LIMIT_TOP = 3;
	public final static int LIMIT_BOTTOM = 2;
	public final static int LIMIT_BACK = 8;
	public final static int LIMIT_TOP_ARM = 1;
    
    //Controllers
    public static XboxController driveController = new XboxController(0);
	public static XboxController manipController = new XboxController(1);
	
	//Switches (turn on Compressor
	public final static int COMPRESSOR_ENABLE = 0;
}
