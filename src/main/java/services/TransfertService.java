package services;


import models.*;

import java.util.List;
import java.util.Set;

public interface TransfertService {

    public void makeTransfert(int id, int etatTransfert, Set<Tour> tours, Uleague uleague);

    public void createTransfert(Transfert transfert);

    public boolean isTransfertOver(Uleague uleague);

    public void closeTransfert(int id);

    public boolean isTourOver(Tour tour);

    public void nouveauTour(Transfert transfert);

    public void makeOffer(Tour tour, Uteam team, Players player, int prix);

    public List<Offres> getBestOffers(Tour tour);

    public void saveAllplayerstoTeamWithBestOffers(List<Offres> offres);

    public List<Players> getPlayersToBuy(Uteam uteam);




}
