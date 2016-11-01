package com.composer.midi;

/**
 * Created by hgminh95 on 11/01/16
 */
public class App {

    public static void main(String[] args) throws Exception {

        MidiFile file = MidiFile.fromFile("/home/hgminh95/light-of-the-seven.mid");

        MidiTrack track = file.getTrack(0);

        for (int i = 0; i <= 1000; i++) {
            System.out.println(track.getEvent(i));
        }
    }
}
