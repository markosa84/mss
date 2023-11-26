package hu.ak_akademia.mss.model.user;

import javax.persistence.Entity;

@Entity
public class Admin extends MssUser {

    @Override
    public String toString() {
        return "Admin{} " + super.toString();
    }
}
