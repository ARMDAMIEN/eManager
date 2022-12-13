package models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Uleague")
public class Uleague{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uleague_id;

    @NotBlank
    private String name;

    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User admin;


    @NotBlank
    private String status;

    @NotBlank
    private String region;

    private int etat; //(1 = en attente des autres joueurs pour lancer la partie, 2= en cours, 3= terminé)


    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy = "uleague")
    Set<Uteam> uteams;

    @ManyToMany(fetch= FetchType.EAGER, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST
    }, mappedBy = "uleagues")
    private Set<User> users = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "uleague")
    private Transfert transf;




    public Uleague(Integer uleague_id, String name, String status, String region, User admin, Set<User> users,Set<Uteam> uteams) {
        this.uleague_id = uleague_id;
        this.name = name;
        this.status = status;
        this.region = region;
        this.admin = admin;
        this.users = users;
        this.uteams=uteams;
    }

    public Uleague(Integer uleague_id, String name, String status, String region, User admin) {
        this.uleague_id = uleague_id;
        this.name = name;
        this.status = status;
        this.region = region;
        this.admin = admin;

    }

    public Uleague(String name, String status, String region, User admin) {
        this.name = name;
        this.status = status;
        this.region = region;
        this.admin = admin;
    }

    public Uleague(String name, String status, String region) {
        this.name = name;
        this.status = status;
        this.region = region;
    }

    public Uleague(){
    }

    public Integer getUleague_id() {
        return uleague_id;
    }

    public void setUleague_id(Integer uleague_id) {
        this.uleague_id = uleague_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Uteam> getUteams() {
        return uteams;
    }

    public void setUteams(Set<Uteam> uteams) {
        this.uteams = uteams;
    }

    public Transfert getTransf() {
        return transf;
    }

    public void setTransf(Transfert transf) {
        this.transf = transf;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
