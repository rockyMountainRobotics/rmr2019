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
    //public Encoder encoder;

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
    private String position = "BOTTOM"; //TODO: Need to reset the elevator to the bottom or else this will be inaccurate
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;


    //Constructor
    public Elevator()
    {
        limitSwitchTop = new DigitalInput(RobotMap.LIMIT_TOP);
        limitSwitchBottom = new DigitalInput(RobotMap.LIMIT_BOTTOM);                
	    //encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    }


    //Updates Robot Position every frame
    public void update()
    {

        //System.out.println("POV: " + RobotMap.manipController.getPOV());
        //TODO: test Robot.driveController.getRawButton(XboxMap.D_PAD_VERT)" MAY NOT BE CORRECT DPAD; CHECK IN FUTURE - try Robot.driveController.getRawButton(XboxMap.D_PAD_VERT) < 0
        //if the D-pad is pressed, we're not already at the top, and not already moving somewhere, then move UP ONE POSITION
        if(RobotMap.manipController.getPOV() == 0 && limitSwitchTop.get() && !isMovingUp)
		{
            //updates moving and speed
            isMovingDown = false;
            isMovingUp = true;
            speed = 0.2;
        }
        
        //TODO: probably needs to be changed to D_PAD_VERT somehow
        //move DOWN ONE POSITION

        if(RobotMap.manipController.getPOV() == 180 && limitSwitchBottom.get() && !isMovingDown)
		{
            //updates moving and speed
            isMovingUp = false;
            isMovingDown = true;
            speed = -0.1;
        }
        
        //if position matches encoder, then stop
        //TODO: add something that allows the driver to override the elevator motor and move manually.
        /*if ((isMovingUp && limitSwitchTop.get() == true) ||  (isMovingDown && limitSwitchBottom.get() == true))
        {
            speed = 0;
        }
        */

        if((isMovingUp && RobotMap.manipController.getRawButton(XboxMap.X)) || (isMovingDown && RobotMap.manipController.getRawButton(XboxMap.X))) 
        {
            speed = 0;
        }
        //Set the motor to the speed as defined by the if's above
        elevMotor.set(speed);
        //encData = encoder.get();

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
