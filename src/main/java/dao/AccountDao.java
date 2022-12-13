package dao;

import models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AccountDao extends JpaRepository<Account, Integer> {

}
