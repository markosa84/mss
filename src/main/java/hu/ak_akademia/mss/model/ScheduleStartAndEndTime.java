package hu.ak_akademia.mss.model;

import java.time.LocalTime;
import java.util.StringJoiner;

public record ScheduleStartAndEndTime(LocalTime startTime, LocalTime endTime) {

    @Override
    public String toString() {
        return new StringJoiner(", ", ScheduleStartAndEndTime.class.getSimpleName() + "[", "]")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }
}
