package com.composer.midi.event;

import javax.sound.midi.*;

/**
 * Created by hgminh95 on 11/01/16
 */
public class ControlEvent extends TrackEvent {

    public static ControlEvent  fromMidiEvent(MidiEvent event) {
        MidiMessage message = event.getMessage();

        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;

            return new ControlEvent()
                    .setKey(sm.getData1())
                    .setVelocity(sm.getData2());
        }

        // TODO
        return new ControlEvent();
    }

    private int key;
    private int velocity;

    public ControlEvent() {

    }

    public int getKey() {
        return key;
    }

    public ControlEvent setKey(int key) {
        this.key = key;

        return this;
    }

    public int getOctave() {
        return key / 12 - 1;
    }

    public ControlEvent setOctave(int octave) {
        this.key = 0;

        return this;
    }

    public int getNote() {
        return key % 12;
    }

    public String getNoteName() {
        return "";
    }

    public ControlEvent setNote(int note) {
        this.key = this.key - getNote() + note;

        return this;
    }

    public ControlEvent setNote(String note) {
        // TODO

        return this;
    }

    public int getVelocity() {
        return velocity;
    }

    public ControlEvent setVelocity(int velocity) {
        this.velocity = velocity;

        return this;
    }

    @Override
    public int getType() {
        return TrackEvent.CONTROL_EVENT;
    }

    @Override
    public String toString() {
        return "@" + getTick() + " - " + getNote() + " - " + getVelocity();
    }
}
