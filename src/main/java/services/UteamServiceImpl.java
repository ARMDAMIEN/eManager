package services;

import dao.UteamDao;
import models.Uleague;
import models.User;
import models.Uteam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UteamServiceImpl implements UteamService{

    @Autowired
    UteamDao dao;

    @Transactional
    @Override
    public void CreateUteam(String name, int points, int wins, int defeats, Uleague uleague, User user, int transfert_status, int budget) {
        Uteam uteam = new Uteam(name,points, wins, defeats, uleague, user,transfert_status,budget);
        List<User> users = uleague.getUsers().stream().filter(u -> u.equals(user)).collect(Collectors.toList());
        if(!users.isEmpty() || uleague.getAdmin().equals(user)){
            users.forEach(user1 -> System.out.println(user1.getuser_id()));
            dao.save(uteam);
        }else{
            System.out.println("user n'a pas rejoint la league");
        }
    }

    @Override
    public void creationUteam(Uteam uteam) {

        dao.save(uteam);
    }
}
