package com.composer.midi;

import java.util.*;
import java.io.File;

import javax.sound.midi.*;

/**
 * Created by hgminh95 on 11/01/16
 */
public class MidiFile {

    List<MidiTrack> tracks;

    public static MidiFile fromFile(String path) throws Exception {
        return new MidiFile(path);
    }

    public MidiFile(String path) throws Exception {
        Sequence sequence = MidiSystem.getSequence(new File(path));

        for (Track track :  sequence.getTracks()) {
            addTrack(MidiTrack.fromTrack(track));
        }
    }

    public MidiFile addTrack(MidiTrack track) {
        if (tracks == null) tracks = new ArrayList<>();

        tracks.add(track);

        return this;
    }

    public MidiTrack getTrack(int index) {
        return tracks.get(index);
    }
}
