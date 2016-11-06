package com.composer.midi.event;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import java.util.Arrays;

/**
 * Created by hgminh95 on 11/01/16
 */
public class MetaEvent extends TrackEvent {

    private byte[] data;
    private int length;

    public static TrackEvent fromMidiEvent(MidiEvent event) {
        MidiMessage message = event.getMessage();

        if (message instanceof MetaMessage) {
            MetaMessage mt = (MetaMessage) message;

            return new MetaEvent(mt.getData())
                    .setTick(event.getTick());
        }
        else return null;
    }

    public MetaEvent() {

    }

    public MetaEvent(byte[] data) {
        setData(data);
    }

    public void setData(byte[] data) {
        setData(data, data.length);
    }

    public void setData(byte[] data, int length) {
        this.data = Arrays.copyOf(data, length);
        this.length = length;
    }

    public String getMessage() {
        return new String(this.data);
    }

    @Override
    public int getType() {
        return TrackEvent.META_EVENT;
    }

    @Override
    public String toString() {
        return "<MetaEvent:@" + getTick() + " - " + getMessage() + ">";
    }

}
