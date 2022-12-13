package services;

import models.Account;

import java.time.LocalDate;

public interface AccountService {

    public Account createAccount(String name, String lastname, LocalDate birthdate, boolean newsletter, boolean sexe);
}
