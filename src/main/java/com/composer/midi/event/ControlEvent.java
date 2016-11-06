package com.composer.midi.event;

import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hgminh95 on 11/01/16
 */
public class ControlEvent extends TrackEvent {

    public static final int NOTE_OFF = 0x80;
    public static final int NOTE_ON = 0x90;
    public static final int POLYPHONIC_AFTERTOUCH = 0xa0;
    public static final int CONTROL_CHANGE = 0xb0;
    public static final int PROGRAM_CHANGE = 0xc0;
    public static final int CHANNEL_AFTERTOUCH = 0xd0;
    public static final int PITCH_WHEEL = 0xe0;

    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public static TrackEvent fromMidiEvent(MidiEvent event) {
        MidiMessage message = event.getMessage();

        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;

            return new ControlEvent()
                    .setCommand(sm.getCommand())
                    .setChannel(sm.getChannel())
                    .setData1(sm.getData1())
                    .setData2(sm.getData2())
                    .setTick(event.getTick());
        }

        // TODO
        return new ControlEvent();
    }

    private int command;
    private int channel;
    private Map<String, Object> data = new HashMap<>();

    public ControlEvent() {

    }

    public ControlEvent setCommand(int command) {
        this.command = command;

        return this;
    }

    public int getCommand() {
        return command;
    }

    public String getCommandName() {
        switch (command) {
            case NOTE_OFF:
                return "Note off";
            case NOTE_ON:
                return "Note on";
            case POLYPHONIC_AFTERTOUCH:
                return "Polyphonic aftertouch";
            case CONTROL_CHANGE:
                return "Control change";
            case PROGRAM_CHANGE:
                return "Program change";
            case CHANNEL_AFTERTOUCH:
                return "Channel aftertouch";
            case PITCH_WHEEL:
                return "Pitch wheel";
            default:
                return "Unknown";
        }
    }

    public ControlEvent setChannel(int channel) {
        this.channel = channel;

        return this;
    }

    public int getChannel() {
        return channel;
    }

    public Object getProperty(ControlEventProperty property) {
        switch (property) {
            case COMMAND:
                return getCommand();
            case CHANNEL:
                return getChannel();
            case NOTE:
                return (int) data.get("key") % 12;
            case NOTE_NAME:
                return getNoteName();
            case OCTAVE:
                return (int) data.get("key") / 12 - 1;
            case VELOCITY:
                return data.get("velocity");
            case PRESSURE:
                return 0;
        }
        return -1;
    }

    public String getNoteName() {
        return NOTE_NAMES[(int) getProperty(ControlEventProperty.NOTE)];
    }

    public ControlEvent setData1(int data1) {
        if (getCommand() == NOTE_ON || getCommand() == NOTE_OFF)
            data.put("key", data1);

        return this;
    }

    public ControlEvent setData2(int data2) {
        if (getCommand() == NOTE_ON || getCommand() == NOTE_OFF)
            data.put("velocity", data2);

        return this;
    }

    @Override
    public int getType() {
        return TrackEvent.CONTROL_EVENT;
    }

    private String toNoteString() {
        return getProperty(ControlEventProperty.NOTE_NAME) + "" +
                getProperty(ControlEventProperty.OCTAVE) + " - " +
                getProperty(ControlEventProperty.VELOCITY);
    }

    @Override
    public String toString() {
        String content = "";

        if (getCommand() == NOTE_ON || getCommand() == NOTE_OFF)
            content = toNoteString();

        return "<ControlEvent:@" + getTick() + " - C" + getChannel() + " - " + getCommandName() +
                "(" + content +")>";
    }
}
