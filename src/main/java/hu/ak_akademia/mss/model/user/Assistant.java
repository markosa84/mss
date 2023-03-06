package hu.ak_akademia.mss.model.user;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Assistant extends MssUser {
    private byte foreign_Language;
    private int area_of_expertise;

    public Assistant() {
    }

    public Assistant(byte foreign_Language, int area_of_expertise) {
        this.foreign_Language = foreign_Language;
        this.area_of_expertise = area_of_expertise;
    }

    public byte getForeign_Language() {
        return foreign_Language;
    }

    public void setForeign_Language(byte foreign_Language) {
        this.foreign_Language = foreign_Language;
    }

    public int getArea_of_expertise() {
        return area_of_expertise;
    }

    public void setArea_of_expertise(int area_of_expertise) {
        this.area_of_expertise = area_of_expertise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assistant assistant)) return false;
        if (!super.equals(o)) return false;
        return getForeign_Language() == assistant.getForeign_Language() && getArea_of_expertise() == assistant.getArea_of_expertise();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getForeign_Language(), getArea_of_expertise());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Assistant.class.getSimpleName() + "[", "]")
                .add("foreign_Language=" + foreign_Language)
                .add("area_of_expertise=" + area_of_expertise)
                .toString();
    }
}
