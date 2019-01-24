package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;


public class Elevator extends Component
{
    //declares motor
    WPI_TalonSRX elevMotor = new WPI_TalonSRX(RobotMap.ELEVATOR);

    //Variable used to set speed
    double speed = 0.0;

    //In situations where limit switches are desired, it is necessary to declare them. YEET SPAGHEET
    public DigitalInput limitSwitchTop;
    public DigitalInput limitSwitchBottom;

    //Declares Encoder
    public Encoder encoder;

    //Gets Encoder Data
    private int encData;

    /*private double distance;
    private double period;
    private double rate;
    private boolean direction;
    private boolean stopped;*/

    //Constants declaring top and bottom values for encoder *NOT TESTED(change values or ded)
    private int TOP_MAX = 180;
    private int BOTTOM_MAX = 0;
    private int MIDDLE_MAX = 90;

    //Current position and whether or not we are moving
    private String position = "BOTTOM";
    private boolean isMoving = false;


    //Constructor
    public Elevator()
    {
        limitSwitchTop = new DigitalInput(RobotMap.LIMIT_TOP);
        limitSwitchBottom = new DigitalInput(RobotMap.LIMIT_BOTTOM);                
	    encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    }


    //Updates Robot Position every frame
    public void update()
    {
        //TODO: test Robot.driveController.getRawButton(XboxMap.D_PAD_VERT)" MAY NOT BE CORRECT DPAD; CHECK IN FUTURE - try Robot.driveController.getRawButton(XboxMap.D_PAD_VERT) < 0
        //if the D-pad is pressed, we're not already at the top, and not already moving somewhere, then move UP ONE POSITION
        if(RobotMap.manipController.getRawButton(XboxMap.D_PAD_VERT) && !limitSwitchTop.get() && !isMoving)
		{
            //updates moving and speed
            isMoving = true;
            speed = 0.25;
            //updates the position of the elevator
            if (position.equals("BOTTOM"))
            {
                position = "MIDDLE";
            }
            else if(position.equals("MIDDLE"))
            {
                position = "TOP";
            }
        }
        
        //TODO: probably needs to be changed to D_PAD_VERT somehow
        //move DOWN ONE POSITION
        if(RobotMap.manipController.getRawButton(XboxMap.D_PAD_HORIZ) && !limitSwitchBottom.get() && !isMoving)
		{
            //updates moving and speed
            isMoving = true;
            speed = -0.25;
            //updates the position of the elevator
            if (position.equals("TOP"))
            {
                position = "MIDDLE";
            }
            else if(position.equals("MIDDLE"))
            {
                position = "BOTTOM";
            }
        }
        
        //if position matches encoder, then stop
        if ((position.equals("TOP") && encData == TOP_MAX) || (position.equals("MIDDLE") && encData == MIDDLE_MAX) || (position.equals("BOTTOM") && encData == BOTTOM_MAX))
        {
            speed = 0;
        }

        //Set the motor to the speed as defined by the if's above
        elevMotor.set(speed);
        encData = encoder.get();

        /*distance = encoder.getDistance();
        period = encoder.getPeriod();
        rate = encoder.getRate();
        direction = encoder.getDirection();
        stopped = encoder.getStopped();*/

    }


    public void autoUpdate()
    {
        //TODO: Probably needs update() here
    }

    
    public void disable()
    {

    }
}
