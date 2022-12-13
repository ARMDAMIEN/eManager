package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Offres")
public class Offres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOffre;

    private int prixPropose;

    @ManyToOne(fetch= FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="fk_idTour")
    private Tour tour;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_idUteam")
    private Uteam interestedTeam;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_idPlayers")
    private Players wantedPlayers;


    @NotNull
    private int priority;


    public Offres(int idOffre, int prixPropose, Uteam interestedTeam, Players wantedPlayers, Tour tour) {
        this.idOffre = idOffre;
        this.prixPropose = prixPropose;
        this.interestedTeam = interestedTeam;
        this.wantedPlayers = wantedPlayers;
        this.tour = tour;
    }

    public Offres(int prixPropose, Uteam interestedTeam, Players wantedPlayers, Tour tour, int priority) {
        this.prixPropose = prixPropose;
        this.interestedTeam = interestedTeam;
        this.wantedPlayers = wantedPlayers;
        this.tour = tour;
        this.priority = priority;
    }

    public Offres() {

    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getPrixPropose() {
        return prixPropose;
    }

    public void setPrixPropose(int prixPropose) {
        this.prixPropose = prixPropose;
    }

    public Uteam getInterestedTeam() {
        return interestedTeam;
    }

    public void setInterestedTeam(Uteam interestedTeam) {
        this.interestedTeam = interestedTeam;
    }

    public Players getWantedPlayers() {
        return wantedPlayers;
    }

    public void setWantedPlayers(Players wantedPlayers) {
        this.wantedPlayers = wantedPlayers;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Offres{" +
                "idOffre=" + idOffre +
                ", prixPropose=" + prixPropose +
                ", tour=" + tour +
                ", interestedTeam=" + interestedTeam +
                ", wantedPlayers=" + wantedPlayers +
                ", priority=" + priority +
                '}';
    }
}
