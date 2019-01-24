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
    private VictorSPX leftSuck;
    private VictorSPX rightSuck;
    private VictorSPX armMover;
    
    //Limit Switch
    public DigitalInput limitSwitchBack;

    //Constructor
    public BallManip()
    {
        //Creates the motors as CANTalons, may need to be changed.
        leftSuck = new VictorSPX(RobotMap.TOP_LEFT_MOTOR);
        rightSuck = new VictorSPX(RobotMap.TOP_RIGHT_MOTOR);
        armMover = new VictorSPX(RobotMap.TOP_CENTER_MOTOR);
        
        //Limit Switch
        limitSwitchBack = new DigitalInput(LIMIT_SWITCH_BACK);
        
        //Initialize constants
        MULTIPLIER = .9; //Motor speed multiplier
        DEADZONE_1 = -.5; //Deadzone in negative direction on xbox joystick
        DEADZONE_2 = .5; //Deadzone in positive direction on xbox joystick

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
        //Will probably need to call update(), for the auto lineup + shoot (if we are having the auto also shoot the ball)

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
         //Stops the motors from moving once the buttons aren't being pressed.
         else
         {
            leftSuck.set(0);
            rightSuck.set(0);
         }

        //When right trigger is pressed and left trigger isn't being pressed
        if(RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) != true)
        {
            //Should make the motors push the ball out.
            leftSuck.set(-1 * MULTIPLIER);
            rightSuck.set(-1 * MULTIPLIER);
        }
        //Stops the motors from moving once the buttons aren't being pressed.
        else
        {
            leftSuck.set(0);
            rightSuck.set(0);
        }
    }
    
    //Resets the arm to its default position so that the beak can extend and not hit the arm.
    public void reset()
    {
        //Need to fix for encoder, so that the motors don't just grind the arm into the robot.
        armMover.set(-.5);
        
    }

}
