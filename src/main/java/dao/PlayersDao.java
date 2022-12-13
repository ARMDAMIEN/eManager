package dao;

import models.Players;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersDao extends JpaRepository<Players, Integer> {
}
