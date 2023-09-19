package hu.ak_akademia.mss.model;



import javax.persistence.*;
import java.util.Objects;

@Entity
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_of_week_id")
    private int id;

    @Column(name = "day_name", nullable = false)
    private String dayName; // A nap neve (pl. "Monday", "Tuesday", a gyöngébbek kedviért.)

    public DayOfWeek() {
    }

    public DayOfWeek(int id, String dayName) {
        this.id = id;
        this.dayName = dayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayOfWeek dayOfWeek = (DayOfWeek) o;
        return id == dayOfWeek.id && Objects.equals(dayName, dayOfWeek.dayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayName);
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "id=" + id +
                ", dayName='" + dayName + '\'' +
                '}';
    }
}
