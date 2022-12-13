package dao;

import models.Offres;
import models.Players;
import models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OffresDao extends JpaRepository<Offres,Integer> {

    @Query("SELECT o FROM Offres o where o.wantedPlayers=:wantedPlayers AND o.tour =:tour")
    List<Offres> offresByplayers(@Param("wantedPlayers") Players wantedPlayers, @Param("tour") Tour tour);
}
