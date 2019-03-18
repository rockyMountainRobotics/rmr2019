package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Wheelie extends Component
{
	Solenoid lifter = new Solenoid(RobotMap.LIFTER_SOLENOID);
	boolean current = true;
	boolean past = false;
	
	
	public void update()
	{
		current = RobotMap.driveController.getRawButton(XboxMap.A);
		
		if(current == true && past == false){
			lifter.set(!lifter.get());
		}
		past = current;
	}


	@Override
	public void autoUpdate() 
	{
		update();
	}


	@Override
	public void disable() 
	{
		reset();
    }
    
    public void reset()
    {
        //resets everything to default (not extended and upOrDown false)
        //TODO: Check which direction is which :)
        lifter.set(false);
    }
	
	
}
