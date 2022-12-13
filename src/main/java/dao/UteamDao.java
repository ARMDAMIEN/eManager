package dao;

import models.Uteam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface UteamDao extends JpaRepository<Uteam, Integer>{
}
