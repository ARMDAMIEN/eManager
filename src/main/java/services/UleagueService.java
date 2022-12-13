package services;

import models.Uleague;
import models.User;

import java.util.Set;

public interface UleagueService{


    public void createUleague(String name, String status, String league, User user);

    public void creationUleague(Uleague uleague);

    public void joinLeague(User user, Uleague uleague);

    public void startleague(Uleague uleague);
}
