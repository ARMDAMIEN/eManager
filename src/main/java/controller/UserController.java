package controller;

import dao.UserDao;
import models.Account;
import models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.AccountService;
import services.UserService;

import javax.validation.Valid;
import java.time.LocalDate;


@Controller
public class UserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    static Logger log = Logger.getLogger(UserController.class.getName());

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult errors, @RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String lastname, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate, @RequestParam boolean newsletter, @RequestParam boolean sexe, Model model) {
        if (errors.hasErrors()) {
            log.error("Erreur lors de la validation du nouveau compte" + user.getuser_id());
            return "signin";
        } else {
            userService.createUser(username, encoder.encode(password), accountService.createAccount(name, lastname, birthdate, newsletter, sexe));
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(Model model) {

        String message = "Vous avez été déconnecté";
        model.addAttribute("message", message);
        return "home";

    }


}
