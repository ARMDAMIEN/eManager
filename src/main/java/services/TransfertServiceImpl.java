package services;

import dao.*;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TransfertServiceImpl implements TransfertService{

    @Autowired
    TransfertDao transfertDao;

    @Autowired
    TourDao tourDao;

    @Autowired
    UteamDao uteamDao;

    @Autowired
    PlayersDao playersDao;

    @Autowired
    OffresDao offresDao;

    @Override
    public void makeTransfert(int id, int etatTransfert,  Set<Tour> tours,Uleague uleague) {
         Transfert transf = new Transfert( id, etatTransfert, tours, uleague);
        transfertDao.save(transf);

    }


    @Override
    public void createTransfert(Transfert transfert) {
        transfertDao.save(transfert);
    }

    @Override
    public boolean isTransfertOver(Uleague uleague) {

        Set<Uteam> uteams = uleague.getUteams();
        uteams.forEach(uteam -> System.out.println(uteam.getUplayers().size()));
        List<Uteam> ut =  uteams.stream().filter(uteam -> uteam.getUplayers().size()!=5).collect(Collectors.toList());
        if(ut.isEmpty() && uleague.getTransf().getEtatTransfert()==2){
            return true;
        }else{
        return false;
        }
    }

    @Override
    public void closeTransfert( int id) {


        Transfert transf =transfertDao.findById(id).get();
        transf.setEtatTransfert(1);
        transfertDao.save(transf);


    }

    @Override
    public boolean isTourOver(Tour tour) {
        List<Uteam> validateTeam = new ArrayList();
        Set<Uteam> uteams = tour.getTransf().getUleague().getUteams();
        List<Uteam> uts = uteams.stream().filter(uteam -> Objects.nonNull(uteam.getUplayers())).filter(uteam -> uteam.getUplayers().size()<5&&uteam.getTransfert_status()==1).collect(Collectors.toList()); //on retire les équipes qui ont déjà 5 joueurs
        if(uts.isEmpty()){
            return true;
        }else{
            return false;
        }
        }

    @Override
    public void nouveauTour(Transfert transfert) {

        Set<Tour> tours = transfert.getTours().stream().filter(tour -> tour.getEtatTour()==1).collect(Collectors.toSet());
        if(tours.isEmpty()){
            Tour nouveauTour = new Tour(tours.size()+1, 1, transfert);
            tours.add(nouveauTour);
            transfert.setTours(tours);
            transfertDao.save(transfert);
        }else{
            tours.forEach(tour -> tour.setEtatTour(0));
            Tour nouveauTour = new Tour(tours.size()+1, 1, transfert);
            tours.add(nouveauTour);
            transfert.setTours((Set<Tour>) tours);
            transfertDao.save(transfert);
        }

    }

    @Override
    public void makeOffer(Tour tour, Uteam uteam, Players players, int prix) {
        if(tour!=null) {
            if(players.getPrice()<=prix){
            Offres offre = new Offres(prix, uteam, players, tour,1);
            offresDao.save(offre);
            }else{
                System.out.println("erreur prix");
            }
        }else{
            System.out.println("tour est null");
        }
    }

    @Override
    public List<Offres> getBestOffers(Tour tour) {

        List<Offres> allOffres = new ArrayList<>();
        allOffres = tour.getOffres().stream().collect(Collectors.toList());
        Players oldplayer = new Players();
        List<Offres> offresgagnantes = new ArrayList<>();
        if(allOffres.isEmpty()){
            System.out.print("pas d'offres pour le moment");
        }else{
        while (allOffres.iterator().hasNext()) {
            Players player = allOffres.iterator().next().getWantedPlayers();
            System.out.println("player=" +player);
            if (player.equals(oldplayer)) {
                allOffres.remove(allOffres.iterator().next()); //supprimer les offres pour ce joueur pour
            } else {
                Offres maxOffres = allOffres.stream().filter(offres -> offres.getTour().equals(tour)&&offres.getWantedPlayers().equals(player)).max(Comparator.comparing(Offres::getPrixPropose)).get();//recupération de l'offre max éviter de re Setter ce joueur a l'offre max
                System.out.println( "offres gagnante: "+maxOffres);
                offresgagnantes.add(maxOffres);
                oldplayer = player;
                allOffres = allOffres.stream().filter(offres -> offres.getTour().equals(tour)&& !offres.getWantedPlayers().equals(player)).collect(Collectors.toList());
                }
            }
        }
        return offresgagnantes;
    }

    @Override
    public void saveAllplayerstoTeamWithBestOffers(List<Offres> offres){


        List<Uteam> uteams = new ArrayList<>();
       for(Offres o : offres){
           Offres offreprioritaire = offres.stream().filter(offres1 -> offres1.getWantedPlayers().getRole().equals(o.getWantedPlayers().getRole())&&offres1.getInterestedTeam().equals(o.getInterestedTeam())).min(Comparator.comparing(Offres::getPriority)).get();
           offreprioritaire.getInterestedTeam().setUplayers(Arrays.asList(offreprioritaire.getWantedPlayers()).stream().collect(Collectors.toSet()));
           uteams.add(offreprioritaire.getInterestedTeam());
           offres = offres.stream().filter(offres1 -> offres1.getWantedPlayers().getRole().equals(o.getWantedPlayers().getRole())&&offres1.getInterestedTeam().equals(o.getInterestedTeam())).collect(Collectors.toList());
       }
        uteamDao.saveAll(uteams);

    }

    public List<Players> getPlayersToBuy(Uteam uteam){

        List<Players> joueurRestantsDispo = new ArrayList<>();
        for (Players pls :  uteam.getUplayers()) {
            joueurRestantsDispo = playersDao.findAll().stream().filter(p->!p.getRole().equals(pls.getRole())).collect(Collectors.toList()); //supprimer les joueurs qui ont le rôle des joueurs de notre équipe, ne laisser que les rôles que l'user n'a pas encore dans son équipe.
        }
        return joueurRestantsDispo;

    }


}
