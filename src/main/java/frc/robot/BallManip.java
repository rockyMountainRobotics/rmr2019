package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class BallManip extends Component
{
    //Constants for the deadzones of the controller - I think we're gonna have to lower these but it's cool for now
    final static double DEADZONE_1 = -.3;
    final static double DEADZONE_2 = .3;

    //Constants for pushing or pulling ball (may need to switch them)
    final static int pulling = -1;
    final static int pushing = 1;
    
    //Multiplier (will never be over 1)
    final double MULTIPLIER = .7;

    //Motors (left side and right side of ball manipulator)
    private WPI_VictorSPX leftSuck;
    private WPI_VictorSPX rightSuck;
    private WPI_TalonSRX armMover;
    
    //Limit Switch - checks if there's a ball in the grabber
    public DigitalInput limitSwitchBack;
    public DigitalInput limitSwitchTopArmStop;
    
    //Encoder (has Quadrature support)
    //Encoder encArmHeight;


    //Constructor
    public BallManip()
    {
        //Creates the motors as VictorSPX's
        leftSuck = new WPI_VictorSPX(RobotMap.LEFT_BALL_GRABBER);
        rightSuck = new WPI_VictorSPX(RobotMap.RIGHT_BALL_GRABBER);
        armMover = new WPI_TalonSRX(RobotMap.BALL_ARM_PIVOT);
        
        //Initializes Limit Switch
        limitSwitchBack = new DigitalInput(RobotMap.LIMIT_BACK);
        limitSwitchTopArmStop = new DigitalInput(RobotMap.LIMIT_TOP_ARM);


        //INVERTS the RIGHT side motor so that both sides spin inwards. Might need to change this if
        //A) inverting doesn't do what I thought it did or B) the LEFT side needs to be inverted.
        rightSuck.setInverted(true);
        
        //Creates encArmHeight the encoder
       // encArmHeight = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        
        //Changes settings on the encoder. More specifics here: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599717-encoders-measuring-rotation-of-a-wheel-or-other-shaft
        
        //encArmHeight.setMaxPeriod(.1); //This is the maximum time in seconds before the device is considered not moving - it's recommended to substitute minRate for this
        //encArmHeight.setMinRate(10); //minimum rate before device is considered stopped; accounts for distance per pulse and scale factor
        //encArmHeight.setDistancePerPulse(5); //scale factor - this is contained in the 4x part of the constructor
        //encArmHeight.setReverseDirection(true); //Sets the direction the encoder counts - this is already done in the constructor
        //encArmHeight.setSamplesToAverage(7); //Sets number of averages to sample to determine period.
        
        //Gonna be honest, i don't know what the hell those things are ^
        
        //TO PREVENT JITTERS: - increase parameter of setSamplesToAverage
        //                    - change encoding type in constructor from 4x to 1x or 2x
        
        //SET DISTANCE PER PULSE: As of right now, this method is commented out b/c we don't need it
        //but we may decide to use it with Pulses per Revolution or if there's extra gearing added after the encoder

        //Reset all motors
        disable();

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update()
    {
        //Stuff only works when in ball mode
        if(SwitchMode.mode == 'C')
        {
            ballPushPull();
            
            //When they push the joystick further than the deadzone, set the arm to the value of the joystick (in testing may want to multiply by .5 or something to allow more precise controls)
            if(Math.abs(RobotMap.manipController.getRawAxis(XboxMap.RIGHT_JOY_VERT)) > DEADZONE_2)
            {
                //Checks if you are 1) hitting the limit switch at the top of the robot and 2)tring to go further in that
                //Direction. If you are, it stops you.
                if(limitSwitchTopArmStop.get() && RobotMap.manipController.getRawAxis(XboxMap.RIGHT_JOY_VERT) < 0)
                {
                    armMover.set(-.5*RobotMap.manipController.getRawAxis(XboxMap.RIGHT_JOY_VERT));
                }
                else
                {
                    armMover.set(0);
                }
            }
            else
            {
                armMover.set(0);
            }
        }
        else
        {
            reset();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void autoUpdate()
    {
        update();
        //Will probably need to call update(), for the auto lineup + shoot (if we are having the auto also shoot the ball)

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void disable()
    {
        leftSuck.set(0);
        rightSuck.set(0);
        armMover.set(0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void ballPushPull()
    {
        //TODO - does this need to override the next two ifs
        
        //When left trigger is pressed and right trigger isn't being pressed
        if(limitSwitchBack.get() && (RobotMap.manipController.getTriggerAxis(Hand.kLeft) > DEADZONE_2 && RobotMap.manipController.getTriggerAxis(Hand.kRight) < DEADZONE_2))
         {
            //Succ ball in
            leftSuck.set(RobotMap.manipController.getTriggerAxis(Hand.kLeft) * MULTIPLIER);
            rightSuck.set(RobotMap.manipController.getTriggerAxis(Hand.kLeft) * MULTIPLIER);
         }
        
        //When right trigger is pressed and left trigger isn't being pressed
        else if(RobotMap.manipController.getTriggerAxis(Hand.kRight) > DEADZONE_2 && RobotMap.manipController.getTriggerAxis(Hand.kLeft) < DEADZONE_2)
        {
            //Should make the motors push the ball out.
            leftSuck.set(-RobotMap.manipController.getTriggerAxis(Hand.kRight) * MULTIPLIER);
            rightSuck.set(-RobotMap.manipController.getTriggerAxis(Hand.kRight) * MULTIPLIER);
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
        //armMover.set(-.5);
        
    }

}
