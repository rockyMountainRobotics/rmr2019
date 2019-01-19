import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.sun.xml.internal.ws.api.Component;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class HatchManip implements Component
{
    //Xbox controller
    XboxController manipController = new XboxController(1);
    
    //Beak position variables
    boolean beakPosition = false;
    boolean beakOpen = false;


    //Constructing Solenoids
    Solenoid trackSolenoid = new Solenoid(RobotMap.SOLENOID_PORT);
    Solenoid beakSolenoid = new Solenoid(RobotMap.SOLENOID_PORT);


    public void update()
    {
        /*
        set(boolean) - 
        true turns solenoid on and pushes beak foward, false retracts
        true turns solenoid on and opens beak, false closes
        */

        //Changing solenoid states
        //If A button pressed change trackSolenoid
        if(manipController.getRawButton(XboxMap.A)==true)
            trackSolenoid = !trackSolenoid;

        //If X button pressed change the state of beakSolenoid
        if(manipController.getRawButton(XboxMap.A)==true)
            beakSolenoid = !beakSolenoid; 
    }

    public void autoUpdate()
    {


    }

    public void disable()
    {


    }
}
