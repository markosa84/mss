package hu.ak_akademia.mss.model;

import java.time.LocalTime;
import java.util.StringJoiner;

public class Slot {

    private int slotId;

    private LocalTime startTime;

    private LocalTime endTime;

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Slot.class.getSimpleName() + "[", "]")
                .add("id=" + slotId)
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }
}
