package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

    @NotBlank(message = "veuillez remplir tout les champs")
    @Email(message = "veuillez indiquer un mail valide")
    private String username;

    @NotBlank
    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Veuillez indiquer un message qui remplit toutes les conditions")
    private String password;


    private boolean enabled;

    @OneToOne (fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="account_id",referencedColumnName = "idaccount", unique = true)
    private Account account;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy = "admin")
    Set<Uleague> admin_leagues;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "user")
    Set<Uteam> uteams;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="user_has_league", joinColumns=@JoinColumn(name="users_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name="league_id", referencedColumnName = "uleague_id"))
    private Set<Uleague> uleagues = new HashSet<>();


    public User() {
    }


    public User(int user_id, String username, String password, boolean enabled, Account account) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.account = account;

    }

    public User(int user_id, String username, String password, boolean enabled, Account account, Set<Uleague> admin_leagues, Set<Uleague> uleagues, Set<Uteam> uteams) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.account = account;
        this.admin_leagues = admin_leagues;
        this.uleagues = uleagues;
        this.uteams = uteams;
    }

    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Uleague> getAdmin_leagues() {
        return admin_leagues;
    }

    public void setAdmin_leagues(Set<Uleague> admin_leagues) {
        this.admin_leagues = admin_leagues;
    }

    public Set<Uleague> getUleagues() {
        return uleagues;
    }

    public void setUleagues(Set<Uleague> uleagues) {
        this.uleagues = uleagues;
    }

    public Set<Uteam> getUteams() {
        return uteams;
    }

    public void setUteams(Set<Uteam> uteams) {
        this.uteams = uteams;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", account=" + account +
                ", admin_leagues=" + admin_leagues +
                ", uteams=" + uteams +
                ", uleagues=" + uleagues +
                '}';
    }
}
