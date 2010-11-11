package net.sequentialmood.midi;

import  net.sequentialmood.socket.Server;
import	javax.sound.midi.MidiMessage;
import	javax.sound.midi.ShortMessage;
import	javax.sound.midi.Receiver;

/**
 * Created by IntelliJ IDEA.
 * User: sequentialmood
 * Date: 14 oct. 2010
 * Time: 22:36:35
 * To change this template use File | Settings | File Templates.
 */
public class MIDIMsgProxy implements Receiver {
    private static final String[]		sm_astrKeyNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private Server _server;
    public MIDIMsgProxy(Server server)
    {
        _server = server;
    }

    public void send(MidiMessage message, long lTimeStamp)
    {
        //System.out.println(message.getMessage().toString());
        String	strMessage = null;
		if (message instanceof ShortMessage)
		{
			strMessage = decodeMessageForFlash((ShortMessage) message);
		}
		else
		{
			strMessage = "unknown message type";
		}
        System.out.println(strMessage);
        _server.sendMessage(strMessage);
    }

    public String decodeMessageForFlash(ShortMessage message)
	{
        String	strMessage = null;
        strMessage = String.valueOf(message.getChannel() + "_" + message.getCommand())+ "_" + message.getData1()+ "_"+ message.getData2();

        return strMessage;
    }

    public String decodeMessage(ShortMessage message)
	{
		String	strMessage = null;
		switch (message.getCommand())
		{
		case 0x80:
			strMessage = "NOTE OFF: " + getKeyName(message.getData1()) + ", VEL: " + message.getData2();
			break;

		case 0x90:
			strMessage = "NOTE ON: " + getKeyName(message.getData1()) + ", VEL: " + message.getData2();
			break;

		case 0xb0:
			strMessage = "CC: " + message.getData1() + ", DATA: " + message.getData2();
			break;

		case 0xc0:
			strMessage = "PROGRAM CHANGE: " + message.getData1();
			break;

		default:
			strMessage = "unknown message: status = " + message.getStatus() + ", byte1 = " + message.getData1() + ", byte2 = " + message.getData2();
			break;
		}

        if (message.getCommand() != 0xF0)
		{
			int	nChannel = message.getChannel() + 1;
			String	strChannel = "-- MIDI: " + nChannel + ", ";
			strMessage = strChannel + strMessage;
		}
        
        return strMessage;
    }

    public static String getKeyName(int nKeyNumber)
	{
		if (nKeyNumber > 127)
		{
			return "illegal value";
		}
		else
		{
			int	nNote = nKeyNumber % 12;
			int	nOctave = nKeyNumber / 12;
			return sm_astrKeyNames[nNote] + (nOctave - 1);
		}
	}
    
    public void close()
	{
	}
}
