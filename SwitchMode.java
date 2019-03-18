package frc.robot;
//TODO: change "C" to "B" to make more sense

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Changes modes between Hatches and Balls (so buttons will do different things); makes sure the beak and the ball arm don't hit each other
public class SwitchMode extends Component
{
    public static char mode;
    String modeStr = "H";

    boolean pastY = false;
    boolean currentY;
    
    //TODO: PUT RESET IN TELEOP INIT (stays in C if not turned off)
    public SwitchMode()
    {
      //Automatically sets the mode to hatch mode on initialization
      mode = 'H';
    }
    
    @Override
    public void update()
    {
      //If the Y button is pressed, switch modes and reset previously used manipulator.

      currentY = RobotMap.manipController.getRawButton(XboxMap.Y);
      if(currentY && !pastY)
      {
        if(mode == 'H')
        {
          mode = 'C';
          System.out.println("Mode: " + mode);
        }
        else
        {
          mode = 'H';
          System.out.println("Mode: " + mode);
        }
      }
      pastY = currentY;

      modeStr.replace(modeStr.charAt(0), mode);
      
      //SmartDashboard.putBoolean("SwitchMode", mode == 'H');
          
    }
    
    @Override
    public void autoUpdate()
    {
      update();
    }
    
    @Override
    public void disable()
    {
    }

}
