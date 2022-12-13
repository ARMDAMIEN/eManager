package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idTour;

    private int nbrtour;

    private int etatTour;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_idTransfert")
    private Transfert transf;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy="tour")
    private Set<Offres> offres = new HashSet<>();

    public Tour(int nbrtour, int etatTour, Transfert transf) {
        this.nbrtour = nbrtour;
        this.etatTour = etatTour;
        this.transf = transf;
    }

    public Tour() {
    }

    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }

    public int getNbrtour() {
        return nbrtour;
    }

    public void setNbrtour(int nbrtour) {
        this.nbrtour = nbrtour;
    }

    public int getEtatTour() {
        return etatTour;
    }

    public void setEtatTour(int etatTour) {
        this.etatTour = etatTour;
    }

    public Transfert getTransf() {
        return transf;
    }

    public void setTransf(Transfert transf) {
        this.transf = transf;
    }

    public Set<Offres> getOffres() {
        return offres;
    }

    public void setOffres(Set<Offres> offres) {
        this.offres = offres;
    }
}
