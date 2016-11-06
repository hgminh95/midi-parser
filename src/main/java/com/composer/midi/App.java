package com.composer.midi;

import com.composer.midi.event.TrackEvent;

/**
 * Created by hgminh95 on 11/01/16
 */
public class App {

    public static void main(String[] args) throws Exception {

        MidiFile file = MidiFile.fromFile("/home/hgminh95/light-of-the-seven.mid");

        System.out.println("Number of track: " + file.size());
        MidiTrack track = file.getTrack(0);

        System.out.println("Number of event: " + track.size());

        int cnt = 0;
        for (int i = 0; i < track.size(); i++) {
            if (track.getEvent(i).getType() == TrackEvent.CONTROL_EVENT) {
                cnt++;
            }
            //System.out.println(track.getEvent(i));
        }

        System.out.println("Number of note: " + cnt / 2);
    }
}
