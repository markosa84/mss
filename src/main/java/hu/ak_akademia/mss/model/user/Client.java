package hu.ak_akademia.mss.model.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Client extends MssUser {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String placeOfBirth;

    private String mothersName;
    @Column(length = 11)
    private String TAJNumber;

    public Client() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getTAJNumber() {
        return TAJNumber;
    }

    public void setTAJNumber(String TAJNumber) {
        this.TAJNumber = TAJNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDateOfBirth(), client.getDateOfBirth()) && Objects.equals(getPlaceOfBirth(), client.getPlaceOfBirth()) && Objects.equals(getMothersName(), client.getMothersName()) && Objects.equals(getTAJNumber(), client.getTAJNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDateOfBirth(), getPlaceOfBirth(), getMothersName(), getTAJNumber());
    }

    @Override
    public String toString() {
        return "Client{" +
                "dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", TAJNumber='" + TAJNumber + '\'' +
                "} " + super.toString();
    }
}
