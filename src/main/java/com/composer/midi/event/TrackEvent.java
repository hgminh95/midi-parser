package com.composer.midi.event;

import javax.sound.midi.*;

/**
 * Created by hgminh95 on 11/01/16
 */
public abstract class TrackEvent {

    public static final int CONTROL_EVENT = 1;
    public static final int SYSEX_EVENT = 2;
    public static final int META_EVENT = 3;

    public static TrackEvent fromMidiEvent(MidiEvent event) {
        if (event.getMessage() instanceof ShortMessage) {
            return ControlEvent.fromMidiEvent(event);
        }
        else if (event.getMessage() instanceof MetaMessage) {
            return MetaEvent.fromMidiEvent(event);
        }
        else {
            return new MetaEvent();
        }
    }

    private long tick;

    public long getTick() {
        return tick;
    }

    public TrackEvent setTick(long tick) {
        this.tick = tick;

        return this;
    }

    public abstract int getType();
}
