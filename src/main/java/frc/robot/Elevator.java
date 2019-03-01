package frc.robot;

import java.awt.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;


public class Elevator extends Component
{
    //declares motor
    WPI_TalonSRX elevMotor = new WPI_TalonSRX(RobotMap.ELEVATOR);

    //Variable used to set speed
    double speed = 0.0;

    int enc = 0;

    //In situations where limit switches are desired, it is necessary to declare them. YEET SPAGHEET
    public DigitalInput limitSwitchTop;
    public DigitalInput limitSwitchBottom;


    //Constants declaring top and bottom values for encoder *NOT TESTED(change values or ded)
    private int TOP_UP = 41000;
    private int TOP_DOWN = 0;
    private int BOTTOM_UP = 0;
    private int BOTTOM_DOWN = -48000;
    private int MIDDLE_UP = 30000;
    private int MIDDLE_DOWN = -23500;

    //Current position and whether or not we are moving
    private String gotoPos = "NONE"; //TODO: Need to reset the elevator to the bottom or else this will be inaccurate
    private String currentPos = "NONE";
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;


    //Constructor
    public Elevator()
    {
        limitSwitchTop = new DigitalInput(RobotMap.LIMIT_TOP);
        limitSwitchBottom = new DigitalInput(RobotMap.LIMIT_BOTTOM);   
        //Configure the encoder - feedback device, Feedback Loop ID, timeout MS
        elevMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);             
    }


    //Updates Robot Position every frame
    public void update()
    {

        System.out.println("Velocity: " + elevMotor.getSelectedSensorVelocity());
        //get the encoder position
        enc = elevMotor.getSelectedSensorPosition();

        //MOVE MANUALLY
        //If we press the joystick up
        if(-RobotMap.manipController.getRawAxis(XboxMap.LEFT_JOY_VERT) > .1)
        {
            System.out.println("Hi");
            //move it up
            elevMotor.set(-RobotMap.manipController.getRawAxis(XboxMap.LEFT_JOY_VERT));
        }
        //if we press it down
        else if (-RobotMap.manipController.getRawAxis(XboxMap.LEFT_JOY_VERT) < -.1)
        {
            System.out.println("Down");
            //Move it down with a multiplier of .4
            elevMotor.set(-.4*RobotMap.manipController.getRawAxis(XboxMap.LEFT_JOY_VERT));
        }
        //otherwise
        else
        {
            //don't move it
            elevMotor.set(0);
        }
        //Output the encoder value
        System.out.println(elevMotor.getSelectedSensorPosition());




        //If limit switch is hit, set position
        if(!limitSwitchBottom.get() /*|| !limitSwitchTop.get()*/)
        {
            //reset the encoder
            elevMotor.setSelectedSensorPosition(0, 0, 30);
            System.out.println("Reset");
            currentPos = !limitSwitchBottom.get() ? "BOTTOM" : "TOP";
        }
        

        //if we press the DPAD, set where it's going
        if(RobotMap.manipController.getPOV() == 90 || RobotMap.manipController.getPOV() == 270)
        {
            gotoPos = "MIDDLE";
            System.out.println("Middle");            
        }
        else if(RobotMap.manipController.getPOV() == 0)
        {
            gotoPos = "TOP";
            System.out.println("Top");
        }
        else if (RobotMap.manipController.getPOV() == 180)
        {
            gotoPos = "BOTTOM";
            System.out.println("Bottom");
        }
        

        if(Math.abs(elevMotor.getSelectedSensorVelocity()) >= 2500)
        {
            System.out.println("Too Fast :d");
            speed = .1;
        }
        else
        {
            speed = 0;
        }


        //GO TO EACH POSITION
        if(gotoPos.equals("TOP"))
        {
            if(/*enc < (TOP_UP-1000)*/ limitSwitchTop.get())
            {
                System.out.println("Top, moving up");
                elevMotor.set(.70 - speed);
            }
            else
            {
                currentPos = "TOP";
                System.out.println("Reached Top");
                gotoPos = "NONE";
                elevMotor.set(0);
            }
        }

        else if(gotoPos.equals("MIDDLE"))
        {
            if(enc < MIDDLE_UP-1000)
            {
                System.out.println("Middle, going up");
                elevMotor.set(.70 - speed);
            }
            else if(enc > MIDDLE_UP+1000)
            {
                System.out.println("Middle, going down");
                elevMotor.set(-.35 + speed);
            }
            else
            {
                currentPos = "MIDDLE";
                elevMotor.set(0);
                System.out.println("Reached Middle");
                gotoPos = "NONE";
            }
        }
        else if(gotoPos.equals("BOTTOM"))
        {
            if(enc > (BOTTOM_UP + 1000))
            {
                System.out.println("Bottom, moving down");
                elevMotor.set(-.35 + speed);
            }
            else
            {
                currentPos = "MIDDLE";
                System.out.println("Reached Bottom");
                gotoPos = "NONE";
                elevMotor.set(0);
            }
        }

    }


    public void autoUpdate()
    {
        update();
    }

    
    public void disable()
    {
        elevMotor.set(0);
        gotoPos = "NONE";
        currentPos = "NONE";
    }

    public void encoderUpdate()
    {
        System.out.println("Pos:" +  elevMotor.getSelectedSensorPosition());
    }
}
