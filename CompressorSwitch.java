package frc.robot;
//TODO Add code here :D

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

public class CompressorSwitch extends Component
{
    DigitalInput compressorInput = new DigitalInput(RobotMap.COMPRESSOR_ENABLE);
    Compressor compressor = new Compressor();

    @Override
    public void update()
    {
        if(!compressorInput.get())
        {
            compressor.start();
        }
        else 
        {
            compressor.stop();
        }
    }

    @Override
    public void autoUpdate() {
        update();
    }

    @Override
    public void disable() {
        compressor.stop();
    }

}