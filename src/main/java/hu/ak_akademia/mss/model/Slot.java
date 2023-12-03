package hu.ak_akademia.mss.model;

import java.time.LocalTime;
import java.util.StringJoiner;

public record Slot(int slotId, LocalTime startTime, LocalTime endTime) {


    @Override
    public String toString() {
        return new StringJoiner(", ", Slot.class.getSimpleName() + "[", "]")
                .add("slotId=" + slotId)
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }
}
