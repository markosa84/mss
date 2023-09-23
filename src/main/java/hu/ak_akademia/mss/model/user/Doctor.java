package hu.ak_akademia.mss.model.user;


import javax.persistence.Entity;

@Entity
public class Doctor extends MssUser {

    @Override
    public String toString() {
        return "Doctor{} " + super.toString();
    }
}
