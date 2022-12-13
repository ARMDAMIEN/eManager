package services;

import dao.AccountDao;
import models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountDao dao;

    @Override
    public Account createAccount(String name, String lastname, LocalDate birthdate, boolean newsletter, boolean sexe) {

        Account acc = new Account();
        acc.setName(name);
        acc.setLastname(lastname);
        acc.setBirthdate(birthdate);
        acc.setNewsletter(newsletter);
        acc.setSexe(sexe);
        dao.save(acc);
        return acc;

    }
}
