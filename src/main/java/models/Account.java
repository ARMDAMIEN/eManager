package models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idaccount;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;

    @NotNull
    private boolean newsletter;

    @NotNull
    private boolean sexe;

    @OneToOne(mappedBy = "account")
    User user;

    public Account() {
    }

    public Account(int idaccount, String name, String lastname, LocalDate birthdate, boolean newsletter, boolean sexe, User user) {
        this.idaccount = idaccount;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.newsletter = newsletter;
        this.sexe = sexe;
        this.user = user;
    }

    public Account(String name, String lastname, LocalDate birthdate, boolean newsletter, boolean sexe) {
        this.idaccount = idaccount;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.newsletter = newsletter;
        this.sexe = sexe;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idaccount=" + idaccount +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", newsletter=" + newsletter +
                ", sexe=" + sexe +
                '}';
    }
}


