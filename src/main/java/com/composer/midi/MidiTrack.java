package com.composer.midi;

import java.util.*;

import javax.sound.midi.*;

import com.composer.midi.event.*;

/**
 * Created by hgminh95 on 11/01/16
 */
public class MidiTrack {

    public static MidiTrack fromTrack(Track track) {
        return new MidiTrack(track);
    }

    private List<TrackEvent> events;

    public MidiTrack(Track track) {
        for (int i = 0; i < track.size(); i++) {
            addEvent(TrackEvent.fromMidiEvent(track.get(i)));
        }
    }

    public MidiTrack addEvent(TrackEvent event) {
        if (events == null) events = new ArrayList<>();

        events.add(event);

        return this;
    }

    public TrackEvent getEvent(int index) {
        return events.get(index);
    }

}
