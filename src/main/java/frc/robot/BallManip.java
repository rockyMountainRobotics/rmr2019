package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;

public class BallManip extends Component
{
    //Constants for the deadzones of the controller - I think we're gonna have to lower these but it's cool for now
    final static double DEADZONE_1 = -.5;
    final static double DEADZONE_2 = .5;
    
    //Multiplier (will never be over 1)
    final double MULTIPLIER = .9;

    //Motors (left side and right side of ball manipulator)
    private WPI_VictorSPX leftSuck;
    private WPI_VictorSPX rightSuck;
    private WPI_TalonSRX armMover;

    
    //Limit Switch - checks if there's a ball in the grabber
    public DigitalInput limitSwitchBack;
    
    //Encoder (has Quadrature support)
    Encoder steve;

    //Constructor
    public BallManip()
    {

        //Creates the motors as VictorSPX's
        leftSuck = new WPI_VictorSPX(RobotMap.TOP_LEFT_MOTOR);
        rightSuck = new WPI_VictorSPX(RobotMap.TOP_RIGHT_MOTOR);
        armMover = new WPI_TalonSRX(RobotMap.TOP_CENTER_MOTOR);
        
        //INitializes Limit Switch
        limitSwitchBack = new DigitalInput(RobotMap.LIMIT_BACK);

        //INVERTS the RIGHT side motor so that both sides spin inwards. Might need to change this if
        //A) inverting doesn't do what I thought it did or B) the LEFT side needs to be inverted.
        rightSuck.setInverted(true);
        
        //Creates Steve the encoder
        steve = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        
        //Changes settings on the encoder. More specifics here: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599717-encoders-measuring-rotation-of-a-wheel-or-other-shaft
        
        //steve.setMaxPeriod(.1); //This is the maximum time in seconds before the device is considered not moving - it's recommended to substitute minRate for this
        steve.setMinRate(10); //minimum rate before device is considered stopped; accounts for distance per pulse and scale factor
        //steve.setDistancePerPulse(5); //scale factor - this is contained in the 4x part of the constructor
        //steve.setReverseDirection(true); //Sets the direction the encoder counts - this is already done in the constructor
        steve.setSamplesToAverage(7); //Sets number of averages to sample to determine period.
        
        //Gonna be honest, i don't know what the hell those things are ^
        
        //TO PREVENT JITTERS: - increase parameter of setSamplesToAverage
        //                    - change encoding type in constructor from 4x to 1x or 2x
        
        //SET DISTANCE PER PULSE: As of right now, this method is commented out b/c we don't need it
        //but we may decide to use it with Pulses per Revolution or if there's extra gearing added after the encoder

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update()
    {
        //Stuff only works when in ball mode
        if(SwitchMode.mode == 'C')
        {
            //When left trigger is pressed and right trigger isn't being pressed
            if(RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) == true || RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) == true)
            {
                ballSuck();
            }
            
            //When they push the joystick further than the deadzone, set the arm to the value of the joystick (in testing may want to multiply by .5 or something to allow more precise controls)
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
        //THIS CODE IS WRONG!! Math.abs is always going to be >0, so this won't work as intended
        if(limitSwitchBack.get() && (Math.abs(leftSuck.get()) > 0 || Math.abs(rightSuck.get()) < 0))
        {
            leftSuck.set(0);
            rightSuck.set(0);
        }

        //When left trigger is pressed and right trigger isn't being pressed
        if(RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) != true)
         {
            //Succ ball in
             leftSuck.set(1 * MULTIPLIER);
             rightSuck.set(1 * MULTIPLIER);
         }
        //When right trigger is pressed and left trigger isn't being pressed
        else if(RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) != true)
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
        //TODO: Need to fix for encoder, so that the motors don't just grind the arm into the robot.
        armMover.set(-.5);
        
    }

}
