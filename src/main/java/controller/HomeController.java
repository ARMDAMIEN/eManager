package controller;

import models.Uleague;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import security.MyUserDetails;
import services.UleagueService;
import services.UserService;

import java.util.Optional;


@Controller
public class HomeController {


    @Autowired
    UleagueService uleagueservice;

    @Autowired
    UserService userService;




    @GetMapping("/home")
    public String home() {

        return "home";
    }

    @GetMapping("/createLeague")
    public String createLeague() {

        return "createLeague";
    }

    @PostMapping("/createLeague")
    public String saveLeague(@RequestParam("leagueName")String leaguename, @RequestParam("leagueStatus") String status, @RequestParam("leagueRegion") String region){
        MyUserDetails usr = new MyUserDetails();
        User currentUser = userService.getCurrentUser(usr);
        Uleague uleague =  new Uleague(leaguename,status,region, currentUser);
        return "leagueInvite";

    }
}
