import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.sun.xml.internal.ws.api.Component;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class HatchManip implements Component
{    
    //Beak position variables
    boolean beakExtended = false;
    boolean beakOpen = false;


    //Constructing Solenoids
    Solenoid trackSolenoid;
    Solenoid beakSolenoid;

    public HatchManip()
    {
        beakExtended = false;
        beakOpen = false;
        trackSolenoid = new Solenoid(RobotMap.TRACK_SOLENOID);
        beakSolenoid = new Solenoid(RobotMap.BEAK_SOLENOID);
    }

    public void update()
    {
        if(SwitchMode.mode == "H")
        {
            /*
            set(boolean) - 
            true turns solenoid on and pushes beak foward, false retracts
            true turns solenoid on and opens beak, false closes
            */

            //Changing solenoid states
            //If A button pressed change trackSolenoid
            if(RobotMap.manipController.getRawButton(XboxMap.A)==true)
                trackSolenoid = !trackSolenoid;

            //If X button pressed change the state of beakSolenoid
            if(RobotMap.manipController.getRawButton(XboxMap.A)==true)
                beakSolenoid = !beakSolenoid; 
        }
    }

    public void autoUpdate()
    {


    }

    public void disable()
    {


    }
    
    public void reset()
    {
        trackSolenoid = false;
        beakSolenoid = false;
    }
}
