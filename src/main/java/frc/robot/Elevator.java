package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Component
{
    private

    //declares motor
    WPI_TalonSRX elevMotor = new WPI_TalonSRX(RobotMap.CHAIN);

    //In situations where limit switches are desired, it is necessary to declare them. YEET SPAGHEET
    public DigitalInput limitSwitchTop;
    public DigitalInput limitSwitchBottom;

    //Declares Encoder
    public Encoder encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    //Gets Encoder Data
    private int encData;

    /*private double distance;
    private double period;
    private double rate;
    private boolean direction;
    private boolean stopped;*/

    //Constants declaring top and bottom values *NOT TESTED(change values or ded)
    private int TOP_MAX = 180;
    private int BOTTOM_MAX = 0;
    private int MIDDLE_MAX = 90;

    //Current position
    private String position = "BOTTOM";
    private boolean isMoving = false;

    //Constructer
    public Elevator()
    {
        limitSwitchTop = new DigitalInput(RobotMap.LIMIT_TOP);
        limtiSwitchBottom = new DigitalInput(RobotMap.LIMIT_BOTTOM);                
    }

    //Updates Robot Position
    public abstract void update()
    {
        //"Robot.driveController.getRawButton(XboxMap.D_PAD_VERT" MAY NOT BE CORRECT DPAD; CHECK IN FUTURE
        //try Robot.driveController.getRawButton(XboxMap.D_PAD_VERT) < 0
        if(Robot.driveController.getRawButton(XboxMap.D_PAD_VERT) && !limitSwitchTop && !isMoving)
		{
            //updates moving and speed
            isMoving = true;
            speed = -0.25;
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
        //"Robot.driveController.getRawButton(XboxMap.D_PAD_VERT" MAY NOT BE CORRECT DPAD; CHECK IN FUTURE
        //try Robot.driveController.getRawButton(XboxMap.D_PAD_VERT) > 0
        if(Robot.driveController.getRawButton(XboxMap.D_PAD_HORIZ) && !limitSwitchBottom && !isMoving)
		{
            //updates moving and speed
            isMoving = true;
            speed = 0.25;
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

        elevMotor.set(speed);
        encData = encoder.get();

        /*distance = encoder.getDistance();
        period = encoder.getPeriod();
        rate = encoder.getRate();
        direction = encoder.getDirection();
        stopped = encoder.getStopped();*/

        
    }

    public abstract void autoUpdate()
    {

    }

    public abstract void disable()
    {

    }
}