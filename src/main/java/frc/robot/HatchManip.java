package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class HatchManip extends Component
{    
    //Beak position variables
    boolean beakExtended;
    boolean beakOpen;

    //Constructing Solenoids
    //TODO: update to double solenoids
    DoubleSolenoid trackSolenoid;
    DoubleSolenoid beakSolenoid;

    //Solenoid Vaues so I don't have to use the whole long name every time :D
    DoubleSolenoid.Value reverse = DoubleSolenoid.Value.kReverse;
    DoubleSolenoid.Value forward = DoubleSolenoid.Value.kForward;
    DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;
    


    //"past" and "current" values -> checks whether button is being pressed, but ensures the setting will only occur once
    boolean pastA;
    boolean currentA;
    boolean pastX;
    boolean currentX;



    //Constructor
    public HatchManip()
    {
        beakExtended = false;
        beakOpen = false;
        trackSolenoid = new DoubleSolenoid(RobotMap.TRACK_SOLENOID_OUT, RobotMap.TRACK_SOLENOID_IN);
        trackSolenoid.set(reverse);
        beakSolenoid = new DoubleSolenoid(RobotMap.BEAK_SOLENOID_OUT, RobotMap.BEAK_SOLENOID_IN);
        beakSolenoid.set(forward);
    }


    public void update()
    {
        //Only does stuff when in "H"atch mode
        if(SwitchMode.mode == 'H')
        {
            
            //Changing solenoid states
            //If A button pressed change trackSolenoid
            currentA = RobotMap.manipController.getRawButton(XboxMap.A)==true;
            if(currentA && !pastA)
            {
                //If beak isn't extended, extend it.
                if(trackSolenoid.get() == forward)
                {
                    trackSolenoid.set(reverse);
                }
                //Else retract it.
                else
                {
                    trackSolenoid.set(forward);
                }
            }

            pastA = currentA;

            //If X button pressed change the state of beakSolenoid
            currentX = RobotMap.manipController.getRawButton(XboxMap.X)==true;
            if(currentX && !pastX)
            {
                //If beak isn't open, open it.
                if(beakSolenoid.get() == forward)
                {
                    beakSolenoid.set(reverse);
                }
                //Else retract it.
                else
                {
                    beakSolenoid.set(forward);
                }
            }    
            pastX = currentX;
        }

        //TODO: this will drop the hatch when we switch modes. make sure thats what we want. (Prevent accidents by not resetting beak)
        else
        {
            reset();
        }
    }


    public void autoUpdate()
    {
        update();
    }


    public void disable()
    {
        reset();

    }

    
    public void reset()
    {
        //reset the solenoids to off and the corresponding variables
        trackSolenoid.set(reverse);
        beakSolenoid.set(reverse);
        beakOpen = false;
        beakExtended = false;
    }
}
