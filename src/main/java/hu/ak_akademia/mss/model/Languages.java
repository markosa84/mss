package hu.ak_akademia.mss.model;

import hu.ak_akademia.mss.model.user.MssUser;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Languages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lang;

    @ManyToMany(mappedBy = "languages")
    private List<MssUser> users;

    public Languages() {
    }

    public Languages(int id, String lang, List<MssUser> users) {
        this.id = id;
        this.lang = lang;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Languages{" +
                "id=" + id +
                ", language='" + lang + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Languages languages = (Languages) o;
        return id == languages.id && Objects.equals(lang, languages.lang) && Objects.equals(users, languages.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lang, users);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<MssUser> getUsers() {
        return users;
    }

    public void setUsers(List<MssUser> users) {
        this.users = users;
    }
}
