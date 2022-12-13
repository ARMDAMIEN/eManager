package models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Uteam")
public class Uteam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUteam")
    private int idUteam;

    @NotBlank
    private String name;

    private int points;

    private int wins;

    private int defeat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_uleague")
    Uleague uleague;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_user_id")
    User user;

    private int transfert_status;// 0 l'équipe n'a pas encore fait ses transfert, 1 l'équipe a validé son tour, 2 l'équipe est complète

    private int transfert_budget;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "interestedTeam")
    @Column(nullable = true)
    private Set<Offres> offers;


    @Column(nullable=true)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "uteams")
    private Set<Players> uplayers;

    public Uteam(String name, int points, int wins, int defeat, Uleague uleague, User user, int transfert_status, int transfert_budget) {
        this.idUteam = idUteam;
        this.name = name;
        this.points = points;
        this.wins = wins;
        this.defeat = defeat;
        this.uleague = uleague;
        this.user = user;
        this.transfert_status = transfert_status;
        this.transfert_budget = transfert_budget;
        this.offers = offers;
        this.uplayers = uplayers;
    }

    public Uteam(int idUteam, String name, int points, int wins, int defeat, Uteam uteam, Uleague uleague, User user) {
        this.idUteam = idUteam;
        this.name = name;
        this.points = points;
        this.wins = wins;
        this.defeat = defeat;
        this.uleague = uleague;
        this.user=user;
    }

    public  Uteam(){

    }

    public Uteam(String name, int points, int wins, int defeat, Uleague uleague, User user) {
        this.name = name;
        this.points = points;
        this.wins = wins;
        this.defeat = defeat;
        this.uleague = uleague;
        this.user = user;
    }

    public int getIdUteam() {
        return idUteam;
    }

    public void setIdUteam(int idUteam) {
        this.idUteam = idUteam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDefeats() {
        return defeat;
    }

    public void setDefeats(int defeats) {
        this.defeat = defeats;
    }

    public Uleague getUleague() {
        return uleague;
    }

    public void setUleague(Uleague uleague) {
        this.uleague = uleague;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDefeat() {
        return defeat;
    }

    public void setDefeat(int defeat) {
        this.defeat = defeat;
    }

    public Set<Offres> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offres> offers) {
        this.offers = offers;
    }

    public Set<Players> getUplayers() {
        return uplayers;
    }

    public void setUplayers(Set<Players> uplayers) {
        this.uplayers = uplayers;
    }

    public int getTransfert_status() {
        return transfert_status;
    }

    public void setTransfert_status(int transfert_status) {
        this.transfert_status = transfert_status;
    }

    public int getTransfert_budget() {
        return transfert_budget;
    }

    public void setTransfert_budget(int transfert_budget) {
        this.transfert_budget = transfert_budget;
    }
}


