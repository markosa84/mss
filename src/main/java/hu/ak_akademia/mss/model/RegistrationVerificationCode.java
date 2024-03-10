package hu.ak_akademia.mss.model;

import hu.ak_akademia.mss.model.user.MssUser;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class RegistrationVerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "verificationCode", unique = true)
    private String verificationCode;


    @OneToOne(targetEntity = MssUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private MssUser user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    public RegistrationVerificationCode() {
    }

    public RegistrationVerificationCode(String verificationCode, MssUser user, LocalDate expiryDate) {
        this.verificationCode = verificationCode;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public MssUser getUser() {
        return user;
    }

    public void setUser(MssUser user) {
        this.user = user;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationVerificationCode that = (RegistrationVerificationCode) o;
        return Objects.equals(verificationCode, that.verificationCode) && Objects.equals(user, that.user) && Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(verificationCode, user, expiryDate);
    }

    @Override
    public String toString() {
        return "RegistrationVerificationCode{" +
                "verificationCode='" + verificationCode + '\'' +
                ", user=" + user +
                ", expiryDate=" + expiryDate +
                '}';
    }
}

