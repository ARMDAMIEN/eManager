package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="League")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLeague;

    private String Region;

    private String name;

    private String logo;

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "league")
    private Set<Team> teams;

    public League(){

    }
    public League(int idLeague, String region, String name, String logo) {
        this.idLeague = idLeague;
        Region = region;
        this.name = name;
        this.logo = logo;
        this.teams = teams;
    }

    public int getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(int idLeague) {
        this.idLeague = idLeague;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
