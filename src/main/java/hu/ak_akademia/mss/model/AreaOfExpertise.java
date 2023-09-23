package hu.ak_akademia.mss.model;

import hu.ak_akademia.mss.model.user.MssUser;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class AreaOfExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_of_expertise_id")
    private int id;
    private String qualification;

    @ManyToMany(mappedBy = "areaOfExpertise")
    private List<MssUser> users;

    private String basicInformation;

    public AreaOfExpertise() {
    }

    public AreaOfExpertise(int id, String qualification, List<MssUser> users, String basicInformation) {
        this.id = id;
        this.qualification = qualification;
        this.users = users;
        this.basicInformation = basicInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<MssUser> getUsers() {
        return users;
    }

    public void setUsers(List<MssUser> users) {
        this.users = users;
    }

    public String getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(String basicInformation) {
        this.basicInformation = basicInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaOfExpertise that)) return false;
        return getId() == that.getId() && Objects.equals(getQualification(), that.getQualification()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getBasicInformation(), that.getBasicInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQualification(), getUsers(), getBasicInformation());
    }

    @Override
    public String toString() {
        return "AreaOfExpertise{" +
                "id=" + id +
                ", qualification='" + qualification + '\'' +
                ", users=" + users +
                ", basicInformation='" + basicInformation + '\'' +
                '}';
    }
}
