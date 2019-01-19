package frc.robot;

public class SwitchMode extends Component
{
    public char mode;
    
    public SwitchMode()
    {
      //Automatically sets the mode to hatch mode on initialization
      mode = "H";
    }
    
    @Override
    public void update()
    {
      //If the Y button is pressed, switch modes.
      if(RobotMap.manipController.getRawButton(XboxMap.Y))
      {
        if(mode == "H")
          mode = "C";
        else
          mode = "H";
      }
          
    }
    
    @Override
    public void autoUpdate()
    {
    }
    
    @Override
    public void disable()
    {
    }

}
