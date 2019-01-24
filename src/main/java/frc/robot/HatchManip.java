import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.sun.xml.internal.ws.api.Component;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class HatchManip implements Component
{    
    //Beak position variables
    boolean beakExtended;
    boolean beakOpen;


    //Constructing Solenoids
    Solenoid trackSolenoid;
    Solenoid beakSolenoid;
    
    //Constructor
    public HatchManip()
    {
        beakExtended = false;
        beakOpen = false;
        trackSolenoid = new Solenoid(RobotMap.TRACK_SOLENOID);
        beakSolenoid = new Solenoid(RobotMap.BEAK_SOLENOID);
    }

    public void update()
    {
        //Only does stuff when in "H"atch mode
        if(SwitchMode.mode == "H")
        {
            /*
            set(boolean) - 
            true turns solenoid on and pushes beak foward, false retracts
            true turns solenoid on and opens beak, false closes
            */
            
            //Not sure if thats how solenoids work.
            
            //Changing solenoid states
            //If A button pressed change trackSolenoid
            if(RobotMap.manipController.getRawButton(XboxMap.A)==true)
            {
                beakExtended = !beakExtended; //Keeps track of whether the beak is extended or not.
                trackSolenoid.set(beakExtended); //Extends/retracts beak based on beakExtended.
            }

            //If X button pressed change the state of beakSolenoid
            if(RobotMap.manipController.getRawButton(XboxMap.A)==true)
            {
                beakOpen = !beakOpen; //Keeps track of whether beak is open or closed.
                beakSolenoid.set(beakOpen); //Opens/closes beak based on beakOpen.
            }    
        }
    }

    public void autoUpdate()
    {


    }

    public void disable()
    {
        trackSolenoid = false;
        beakSolenoid = false
        beakOpen = false;
        beakExtended = false;

    }
    
    public void reset()
    {
        trackSolenoid = false;
        beakSolenoid = false;
        beakOpen = false;
        beakExtended = false;
    }
}
