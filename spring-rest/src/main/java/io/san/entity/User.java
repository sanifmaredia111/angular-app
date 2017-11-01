package io.san.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.UUID;

@Entity
@NamedQueries({
                      @NamedQuery(name = "User.findAll",
                                  query = "SELECT user FROM User user ORDER BY user.email DESC"),
                      @NamedQuery(name = "User.findByEmail",
                                  query = "SELECT user FROM User user WHERE user.email=:paramEmail")
              })
public class User {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    public User() {
        this.id = UUID.randomUUID()
                .toString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
