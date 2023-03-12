package hu.ak_akademia.mss.model.user;

import hu.ak_akademia.mss.model.Languages;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Client extends MssUser {

    @ManyToMany
    private List<Languages> languages;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    @Column(nullable = false)
    private String mothersName;
    @Column(length = 11)
    private String TAJNumber;

    public Client() {
    }

    public Client(List<Languages> languages, LocalDate dateOfBirth, String placeOfBirth, String mothersName, String TAJNumber) {
        this.languages = languages;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.mothersName = mothersName;
        this.TAJNumber = TAJNumber;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
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
    public String toString() {
        return "Client{" +
                "languages=" + languages +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", TAJNumber='" + TAJNumber + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(languages, client.languages) && Objects.equals(dateOfBirth, client.dateOfBirth) && Objects.equals(placeOfBirth, client.placeOfBirth) && Objects.equals(mothersName, client.mothersName) && Objects.equals(TAJNumber, client.TAJNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), languages, dateOfBirth, placeOfBirth, mothersName, TAJNumber);
    }
}
