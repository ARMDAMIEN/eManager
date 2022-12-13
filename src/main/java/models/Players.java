package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Players")
public class Players {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idplayers;

    private String name;

    private String role;

    private int price ;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="idTeam")
    private Team team;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="Players_has_Uteam", joinColumns=@JoinColumn(name="mtom_idPlayers", referencedColumnName = "idPlayers"), inverseJoinColumns = @JoinColumn(name="mtom_idUteam", referencedColumnName = "idUteam"))
    private Set<Uteam> uteams=new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "wantedPlayers")
    private Set<Offres> offres;


    public Players(int idplayers, String name, String role, int price, Team team, Set<Offres> offres){
        this.idplayers = idplayers;
        this.name = name;
        this.role = role;
        this.price = price;
        this.team = team;
        this.offres = offres;
    }

    public Players(int idplayers, String name, String role, int price, Team team){
        this.idplayers = idplayers;
        this.name = name;
        this.role = role;
        this.price = price;
        this.team = team;
    }

    public Players(){

    }


    public int getIdplayers() {
        return idplayers;
    }

    public void setIdplayers(int idplayers) {
        this.idplayers = idplayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Offres> getInteresteduteams() {
        return offres;
    }

    public void setUsers(Set<Offres> offres) {
        this.offres = offres;
    }

    @Override
    public String toString() {
        return "Players{" +
                "idplayers=" + idplayers +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", price=" + price +
                ", team=" + team +
                ", uteams=" + uteams +
                ", offres=" + offres +
                '}';
    }
}
