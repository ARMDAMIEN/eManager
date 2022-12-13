package services;

import models.Account;
import models.User;
import security.MyUserDetails;

public interface UserService{


    public void createUser(String name, String password, Account account);

    public void saveUser(User user);

    public User getCurrentUser(MyUserDetails usr);
}
