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
    
    //Controllers
    public static XboxController driveController = new XboxController(0);
    public static XboxController manipController = new XboxController(1);
}