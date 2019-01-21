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
    final double MULTIPLIER = .9;

    //Motors (left side and right side of ball manipulator) CANTalon or SRX?????
    private CANTalon leftSide;
    private CANTalon rightSide;

    

    public DigitalInput limitSwitchBack = new DigitalInput(LIMIT_SWITCH_BACK);


    public BallManip()
    {
        leftSide = new CANTalon(RobotMap.TOP_LEFT_MOTOR);
        rightSide = new CANTalon(RobotMap.TOP_RIGHT_MOTOR);

        DEADZONE_1 = -.5;
        DEADZONE_2 = .5;

        //INVERTS the RIGHT side motor so that both sides spin inwards. Might need to change this if
        //A) inverting doesn't do what I thought it did or B) the LEFT side needs to be inverted.
        rightSide.setInverted(true);

    }

    public void update()
    {
        //If the limit switch is not pressed (no ball there)
        if(!limitSwitchBack.get())
        {
            //When left trigger is pressed and right trigger isn't being pressed
            if(RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) != true)
            {
                leftSide.set(1 * MULTIPLIER);
                rightSide.set(1 * MULTIPLIER);
            }
        }

        //When right trigger is pressed and left trigger isn't being pressed
        if(RobotMap.manipController.getRawButton(XboxMap.R_ANALOG) == true && RobotMap.manipController.getRawButton(XboxMap.L_ANALOG) != true)
        {
            //is this how it works? who knows : )
            leftSide.set(-1 * MULTIPLIER);
            rightSIde.set(-1 * MULTIPLIER);
        }
        
        if(RobotMap.manipController.getRawAxis(XboxMap.RIGHT_JOY_VERT))
    }

    public void autoUpdate()
    {
        //Will probably need to call update()

    }

    public void disable()
    {
        leftSide.set(0);
        rightSide.set(0);
    }

}
