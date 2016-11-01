package com.composer.midi.event;

/**
 * Created by hgminh95 on 11/01/16
 */
public class MetaEvent extends TrackEvent {

    public MetaEvent() {

    }

    @Override
    public int getType() {
        return TrackEvent.META_EVENT;
    }

}
