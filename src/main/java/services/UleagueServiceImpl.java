package services;

import dao.UleagueDao;
import dao.UserDao;
import models.Transfert;
import models.Uleague;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UleagueServiceImpl implements UleagueService{

    @Autowired
    UleagueDao uleagueDao;

    @Autowired
    UserDao userDao;

    @Autowired
    TransfertService transfertService;


    @Override
    public void createUleague(String name, String status, String league, User user){

        Uleague uleague = new Uleague(name,status, league,user);
        uleague.setUleague_id(uleagueDao.findAll().size()+1);
        uleagueDao.save(uleague);

    }

    @Override
    public void creationUleague(Uleague uleague) {

        uleagueDao.save(uleague);
    }

    @Override
    @Transactional
    public void joinLeague(User user, Uleague uleague){
        //todo empêcher l'admin de rejoindre sa propre league
        if(uleague.getUsers().stream().filter(u -> u.equals(user)).collect(Collectors.toList()).isEmpty() && !user.equals(uleague.getAdmin())) {
            Set <Uleague> uleagues = new HashSet<>();
            uleagues.add(uleague);
            user.setUleagues(uleagues);
            user.getUleagues().add(uleague);
            userDao.save(user);
        }else{
            System.out.println("vous avez déjà rejoint cette league");
        }


    }

    @Override
    @Transactional
    public void startleague(Uleague uleague) {
        if (uleague.getEtat()==1&&uleague.getUteams().size()>=5){
            uleague.setEtat(2);
            Transfert tr = new Transfert(1);
            uleague.setTransf(tr);
            uleagueDao.save(uleague);
        }else{
            System.out.println("vous devez avoir minimum 5 équipes dans la league pour jouer");
        }
    }


}
