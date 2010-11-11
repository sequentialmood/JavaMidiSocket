/**
 * Created by IntelliJ IDEA.
 * User: sequentialmood
 * Date: 14 oct. 2010
 * Time: 22:17:24
 * To change this template use File | Settings | File Templates.
 */

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import net.sequentialmood.midi.MIDIUtils;
import net.sequentialmood.midi.MIDIMsgProxy;
import javax.sound.midi.Transmitter;
import net.sequentialmood.socket.Server;

public class MIDISocketServer {
    private static boolean  DEBUG = true;
    
    public static void main(String[] args)  throws Exception
	{
        //CREATE SERVER
        Server server = new Server();

        
        //LOOP IN DEVICES LIST
        MIDIUtils.getDevicesList();

        //GET LPD8
        MidiDevice.Info LPD8DeviseInfo  = MIDIUtils.getMidiDeviceInfoByIndex(0);
        MidiDevice inputDevice = MidiSystem.getMidiDevice(LPD8DeviseInfo);

        //OPEN IT
		inputDevice.open();

        //GET MIDI IN TRANSMITTER
        Transmitter	t = inputDevice.getTransmitter();
        MIDIMsgProxy proxy = new MIDIMsgProxy(server);

        //SET THE RECEIVER
        t.setReceiver(proxy);

        server.run();
    }


}
