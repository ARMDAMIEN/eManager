package services;

import models.Uleague;
import models.User;
import models.Uteam;

public interface UteamService {

    public void CreateUteam(String name, int points, int wins, int defeats, Uleague uleague, User user,int transfert_status, int budget);

    public void creationUteam(Uteam uteam);
}
