package dao;

import models.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransfertDao extends JpaRepository<Transfert, Integer> {
}
