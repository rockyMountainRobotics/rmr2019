package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;


public class HatchManip extends Component
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
        if(SwitchMode.mode == 'H')
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
                trackSolenoid.set(!trackSolenoid.get());
                beakExtended = !beakExtended; //Keeps track of whether the beak is extended or not.
            }

            //If X button pressed change the state of beakSolenoid
            if(RobotMap.manipController.getRawButton(XboxMap.A)==true)
            {
                beakSolenoid.set(!beakSolenoid.get());
                beakOpen = !beakOpen; //Keeps track of whether beak is open or closed.
            }    
        }
    }


    public void autoUpdate()
    {


    }


    public void disable()
    {
        reset();

    }

    
    public void reset()
    {
        //reset the solenoids to off and the corresponding variables
        trackSolenoid.set(false);
        beakSolenoid.set(false);
        beakOpen = false;
        beakExtended = false;
    }
}
