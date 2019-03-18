package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class HatchManip extends Component
{    
    //Beak position variables
    boolean beakExtended;
    boolean beakOpen;

    //Constructing Solenoids
    
    Solenoid trackSolenoid;
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
        trackSolenoid = new Solenoid(RobotMap.TRACK_SOLENOID);
        beakSolenoid = new DoubleSolenoid(RobotMap.BEAK_SOLENOID_OUT, RobotMap.BEAK_SOLENOID_IN);
        beakSolenoid.set(forward);
        trackSolenoid.set(false);
    }


    public void update()
    {
        //Only does stuff when in "H"atch mode
        //if(SwitchMode.mode == 'H')
        //{
            
            //Changing solenoid states
            //If A button pressed change trackSolenoid
            currentA = RobotMap.manipController.getRawButton(XboxMap.A)==true;
            
		
		    if(currentA == true && pastA == false){
			    trackSolenoid.set(!trackSolenoid.get());
            }

            SmartDashboard.putBoolean("Track", trackSolenoid.get());

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
        //}

        /*else
        {
            reset();
        }*/
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
        //TODO: find which value is which
        trackSolenoid.set(false);
        beakSolenoid.set(reverse);

    }
}
