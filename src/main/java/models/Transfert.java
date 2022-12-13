package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Transfert")
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransfert;

    private int etatTransfert; //(1= en cours, 2=terminé)

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "transf")
    private Set<Tour> tours;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_uleague_id",referencedColumnName = "uleague_id")
    private Uleague uleague;

    public Transfert(int idTransfert, int etatTransfert, Set<Tour> tours, Uleague uleague) {
        this.idTransfert = idTransfert;
        this.etatTransfert = etatTransfert;
        this.tours = tours;
        this.uleague = uleague;
    }

    public Transfert(int idTransfert, int etatTransfert,Uleague uleague) {
        this.idTransfert = idTransfert;
        this.etatTransfert = etatTransfert;
        this.uleague = uleague;
    }

    public Transfert(int etatTransfert){

    }

    public Transfert(){

    }

    public int getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(int idTransfert) {
        this.idTransfert = idTransfert;
    }

    public int getEtatTransfert() {
        return etatTransfert;
    }

    public void setEtatTransfert(int etatTransfert) {
        this.etatTransfert = etatTransfert;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Uleague getUleague() {
        return uleague;
    }

    public void setUleague(Uleague uleague) {
        this.uleague = uleague;
    }
}
