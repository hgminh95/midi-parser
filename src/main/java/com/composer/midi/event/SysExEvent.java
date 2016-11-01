package com.composer.midi.event;

/**
 * Created by hgminh95 on 11/01/16
 */
public class SysExEvent extends TrackEvent {

    public SysExEvent() {

    }

    @Override
    public int getType() {
        return TrackEvent.SYSEX_EVENT;
    }
}
