package hu.ak_akademia.mss.model.user;

import javax.persistence.Entity;

@Entity
public class Assistant extends MssUser {

    @Override
    public String toString() {
        return "Assistant{} " + super.toString();
    }
}
