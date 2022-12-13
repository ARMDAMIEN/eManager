package services;

import dao.UserDao;
import models.Account;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.MyUserDetails;

@Service
public class UserServiceImpl implements UserService{

    BCryptPasswordEncoder encoder;

    @Autowired
    UserDao dao;

    @Override
    public void createUser(String username, String password, Account account) {

        User usr = new User();
        usr.setUsername(username);
        usr.setPassword(encoder.encode(password));
        usr.setAccount(account);
        dao.save(usr);

    }

    @Override
    public void saveUser(User user) {
        dao.save(user);
    }

    @Override
    public User getCurrentUser(MyUserDetails usr) {
        usr= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication();
        return dao.findByUsername(usr.getUsername()).get();
    }

}
