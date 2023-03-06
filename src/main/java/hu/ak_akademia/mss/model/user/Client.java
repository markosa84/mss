package hu.ak_akademia.mss.model.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Client extends MssUser {

   private String language;
   private int financial_balanse_huf;
   private String tajNumber;
   private String mothersName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private String placeOfBirth;
   public Client(){}
    public Client(String preferable_language,int financial_balanse_huf, String tajNumber,String mothersName, LocalDate dateOfBirth, String placeOfBirth){
       this.language = preferable_language;
       this.financial_balanse_huf = financial_balanse_huf;
       this.tajNumber = tajNumber;
       this.mothersName = mothersName;
       this.dateOfBirth = dateOfBirth;
       this.placeOfBirth = placeOfBirth;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFinancial_balanse_huf() {
        return financial_balanse_huf;
    }

    public void setFinancial_balanse_huf(int financial_balanse_huf) {
        this.financial_balanse_huf = financial_balanse_huf;
    }

    public String getTajNumber() {
        return tajNumber;
    }

    public void setTajNumber(String tajNumber) {
        this.tajNumber = tajNumber;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        if (!super.equals(o)) return false;
        return getFinancial_balanse_huf() == client.getFinancial_balanse_huf() && Objects.equals(getLanguage(), client.getLanguage()) && Objects.equals(getTajNumber(), client.getTajNumber()) && Objects.equals(getMothersName(), client.getMothersName()) && Objects.equals(getDateOfBirth(), client.getDateOfBirth()) && Objects.equals(getPlaceOfBirth(), client.getPlaceOfBirth());
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLanguage(), getFinancial_balanse_huf(), getTajNumber(), getMothersName(), getDateOfBirth(), getPlaceOfBirth());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                .add("language='" + language + "'")
                .add("financial_balanse_huf=" + financial_balanse_huf)
                .add("tajNumber='" + tajNumber + "'")
                .add("mothersName='" + mothersName + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("placeOfBirth='" + placeOfBirth + "'")
                .toString();
    }
}
