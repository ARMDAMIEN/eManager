package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTeam;

    private String name;

    private String logo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="idLeague")
    private League league;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "team")
    private Set<Players> players;

    public Team(){
    }

    public Team(int idTeam, String name, String logo, League league, Set<Players> players) {
        this.idTeam = idTeam;
        this.name = name;
        this.logo = logo;
        this.league = league;
        this.players = players;
    }

    public Team(int idTeam, String name, String logo, League league) {
        this.idTeam = idTeam;
        this.name = name;
        this.logo = logo;
        this.league = league;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Set<Players> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Players> players) {
        this.players = players;
    }
}


