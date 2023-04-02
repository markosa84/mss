package hu.ak_akademia.mss.model;

import hu.ak_akademia.mss.model.user.MssUser;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int id;

    private String name;

    @ManyToMany(mappedBy = "languages")
    private List<MssUser> users;

    public Language() {
    }

    public Language(int id, String name, List<MssUser> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Languages{" +
                "id=" + id +
                ", language='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return id == language.id && Objects.equals(name, language.name) && Objects.equals(users, language.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
