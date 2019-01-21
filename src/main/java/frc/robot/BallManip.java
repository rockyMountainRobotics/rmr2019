package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.sun.xml.internal.ws.api.Component;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class BallManip implements Component
{
    //Constants
    final double LIMIT_SWITCH_BACK;
    final static double DEADZONE_1;
    final static double DEADZONE_2;
    
    //Multiplier
    final double MULTIPLIER;

    //Motors (left side and right side of ball manipulator) CANTalon or SRX?????
    private CANTalon leftSuck;
    private CANTalon rightSuck;
    private CANTalon armMover;

    private double speed;

    

    public DigitalInput limitSwitchBack = new DigitalInput(LIMIT_SWITCH_BACK);


    public BallManip()
    {
        leftSuck = new CANTalon(RobotMap.TOP_LEFT_MOTOR);
        rightSuck = new CANTalon(RobotMap.TOP_RIGHT_MOTOR);
        armMover = new CANTalon(RobotMap.TOP_CENTER_MOTOR);
        speed = 0.0;

        MULTIPLIER = .9;
        DEADZONE_1 = -.5;
        DEADZONE_2 = .5;

        //INVERTS the RIGHT side motor so that both sides spin inwards. Might need to change this if
        //A) inverting doesn't do what I thought it did or B) the LEFT side needs to be inverted.
        rightSuck.setInverted(true);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update()
    {
        //Stuff only works when in ball mode
        if(SwitchMode.mode.equals("C"))
        {
            //When left trigger is pressed and right trigger isn't being pressed
            if(RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) == true || RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) == true)
            {
                ballSuck();
            }
            
            //When they push the joystick
            if(Math.abs(RobotMap.manipController.getRawAxis(XboxMap.RIGHT_JOY_VERT)) > DEADZONE_1)
            {
                armMover.set(RobotMap.manipController.getRawAxis(XboxMap.RIGHT_JOY_VERT));
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void autoUpdate()
    {
        //Will probably need to call update()

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void disable()
    {
        leftSuck.set(0);
        rightSuck.set(0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void ballSuck()
    {
        //If there is a ball and its trying to SUCC, stops it from burning a hole in the ball. (limitSwitchBack.get() may need to be inversed)
        if(limitSwitchBack.get() && Math.abs(leftSuck.get()) > 0 || Math.abs(rightSuck.get() < 0) )
        {
            leftSuck.set(0);
            rightSuck.set(0);
        }

        //When left trigger is pressed and right trigger isn't being pressed
        if(RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) != true)
         {
             leftSuck.set(1 * MULTIPLIER);
             rightSuck.set(1 * MULTIPLIER);
         }

        //When right trigger is pressed and left trigger isn't being pressed
        if(RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) != true)
        {
            //is this how it works? who knows : )
            leftSuck.set(-1 * MULTIPLIER);
            rightSuck.set(-1 * MULTIPLIER);
        }
    }

    public void reset()
    {
        //Need to fix for encoder, so that the motors don't just grind the arm into the robot.
        armMover.set(-.5);
        
    }

}
