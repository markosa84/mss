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
    private int areaOfExpertiseId;
    private String name;

    @ManyToMany(mappedBy = "areaOfExpertise")
    private List<MssUser> users;

    private String description;

    public AreaOfExpertise() {
    }

    public AreaOfExpertise(int areaOfExpertiseId, String name, List<MssUser> users, String description) {
        this.areaOfExpertiseId = areaOfExpertiseId;
        this.name = name;
        this.users = users;
        this.description = description;
    }

    public int getAreaOfExpertiseId() {
        return areaOfExpertiseId;
    }

    public void setAreaOfExpertiseId(int areaOfExpertiseId) {
        this.areaOfExpertiseId = areaOfExpertiseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MssUser> getUsers() {
        return users;
    }

    public void setUsers(List<MssUser> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaOfExpertise that)) return false;
        return getAreaOfExpertiseId() == that.getAreaOfExpertiseId() && Objects.equals(getName(), that.getName()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAreaOfExpertiseId(), getName(), getUsers(), getDescription());
    }

    @Override
    public String toString() {
        return "AreaOfExpertise{" +
                "id=" + areaOfExpertiseId +
                ", qualification='" + name + '\'' +
                ", users=" + users +
                ", basicInformation='" + description + '\'' +
                '}';
    }
}
