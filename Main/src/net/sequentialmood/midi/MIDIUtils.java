package net.sequentialmood.midi;

/**
 * Created by IntelliJ IDEA.
 * User: sequentialmood
 * Date: 14 oct. 2010
 * Time: 22:26:56
 * To change this template use File | Settings | File Templates.
 */
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

public class MIDIUtils {
    public static void getDevicesList()
    {
        // LOOP IN MIDI DEVICE
        MidiDevice.Info[]	aInfos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < aInfos.length; i++)
		{
			try
			{
				MidiDevice	device = MidiSystem.getMidiDevice(aInfos[i]);
				boolean		bAllowsInput = (device.getMaxTransmitters() != 0);
				boolean		bAllowsOutput = (device.getMaxReceivers() != 0);
				if (bAllowsInput || bAllowsOutput)
				{

					System.out.println(i + "" + "  "
						+ (bAllowsInput?"IN ":"   ")
						+ (bAllowsOutput?"OUT ":"    ")
						+ aInfos[i].getName() + ", "
						+ aInfos[i].getVendor() + ", "
						+ aInfos[i].getVersion() + ", "
						+ aInfos[i].getDescription());
					}
					else
					{
					System.out.println("" + i + "  " + aInfos[i].getName());
					}

			}
			catch (MidiUnavailableException e)
			{
				// device is obviously not available...
				// out(e);
			}
		}
		if (aInfos.length == 0)
		{
			System.out.println("[No devices available]");
		}
		//System.exit(0);
    }

    public static MidiDevice.Info getMidiDeviceInfoByIndex(int index)
	{
		MidiDevice.Info[]	aInfos = MidiSystem.getMidiDeviceInfo();
		if ((index < 0) || (index >= aInfos.length)) {
			return null;
		}
		return aInfos[index];
	}
}
