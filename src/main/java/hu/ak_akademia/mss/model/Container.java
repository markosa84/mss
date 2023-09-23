package hu.ak_akademia.mss.model;

import hu.ak_akademia.mss.model.user.Doctor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Container {

    private String name;
    private String age;
    private String text;
    private String test;
    private Doctor user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Doctor getUser() {
        return user;
    }

    public void setUser(Doctor user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Container{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", text='" + text + '\'' +
                ", test='" + test + '\'' +
                ", user=" + user +
                '}';
    }
}
