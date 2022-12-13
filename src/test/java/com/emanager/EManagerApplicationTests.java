package com.emanager;

import controller.UserController;
import dao.*;
import models.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import services.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class EManagerApplicationTests {


	static Logger log = Logger.getLogger(UserController.class.getName());


	@Autowired
	UteamService uteamService;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountDao accountDao;

	@Autowired
	UserDao userDao;

	@Autowired
	UleagueDao uleagueDao;

	@Autowired
	UleagueService uleagueService;

	@Autowired
	UteamDao uteamDao;

	@Autowired
	TransfertDao transfertDao;

	@Autowired
	TransfertService transfertService;

	@Autowired
	TourDao tourDao;

	@Autowired
	PlayersDao playersDao;

	@Autowired
	OffresDao offresDao;






	@Test
	void contextLoads() {
	}


	@Test
	@Transactional
	void remove(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(24,"teston@gmail.com",encoder.encode("Armourdom75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(60); //pour arrêter de créer de nouvelle league
		user.getUleagues().remove(uleague);
		userDao.save(user);

	}

	//todo supprimer toutes les données de la bdd et faire de nouveaux objets sans passer par la bdd


	@Test
	@Transactional
	@Rollback(true)
	void createUser(){
		Account acc = new Account("mathieu","letest", LocalDate.parse("2022-02-18"),true,true);
		User user = new User(1,"testinho@gmail.com",encoder.encode("Armourdom75-"),true, acc);
		userService.saveUser(user);
		Assert.assertFalse(userDao.findAll().isEmpty());
		Assert.assertFalse(accountDao.findAll().isEmpty());


	}


	@Test
	void testBCrypt(){
		System.out.println(encoder.encode("Armourdom75-"));
		Assert.assertNotEquals("Armourdom75-", encoder.encode("Armourdom75-"));
	}

	@Test
	@Transactional
	@Rollback(true)
	void testCreateLeague(){

		Account acc = new Account("mathieu", "lesteston",LocalDate.parse("1995-12-31"),true,true);
		acc.setIdaccount(2);
		User user = new User(1, "testinho@gmail.com" ,encoder.encode("Armourdom75-"),true, acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		uleagueService.creationUleague(uleague);
		Assert.assertNotNull(uleague.getAdmin());
		user.getUleagues().forEach((u)->System.out.println(u.getName()));
		Assert.assertNotNull(user.getUleagues());
	}



	@Transactional
	@Test
	@Rollback(true)
	void testManyToMany(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Account acc2 = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user2 = new User(2, "damien@gmail.com", encoder.encode("Armourdom75-"),true,acc2);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user2);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		uleagueService.joinLeague(user,uleague);
		user.getUleagues().forEach((u)->System.out.println(user.getuser_id()+ "=" +u.getName()));
		uleague.getUsers().forEach((u) ->System.out.println(uleague.getUleague_id()+ " = " +u.getUsername()));
		Assert.assertFalse(user.getUleagues().isEmpty());

	}




	@Transactional
	@Test
	@Rollback(true)
	void testCreateUTeam(){
		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		uteamService.CreateUteam("Gkorp",3,1,0,uleague,user, 0,500);
		Assert.assertFalse(uteamDao.findAll().isEmpty());



	}


	@Transactional
	@Test
	void testStartLeague(){
		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		uleague.setEtat(0);
		uleagueService.startleague(uleague);
		Assert.assertEquals(1,uleague.getEtat());
		Assert.assertNotNull(uleague.getTransf());


	}




	@Test
	@Transactional
	void testCreateTour(){
		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		Set<Tour> vtours = new HashSet<>();
		Transfert transfert = new Transfert(1,0, vtours,uleague);
		transfertService.nouveauTour(transfert);
		List<Tour> tours = transfert.getTours().stream().collect(Collectors.toList());
		Assert.assertTrue(!tours.isEmpty());
		//Assert.assertTrue(tours.get(tours.size()-2)==null || tours.get(tours.size()-2).getEtatTour()==0);



	}




	@Test
	@Transactional
	@Rollback(true)
	void testMakeOffer(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		Set<Tour> vtours = new HashSet<>();
		Transfert transfert = new Transfert(1,0, vtours, uleague);
		transfertService.nouveauTour(transfert);
		vtours = transfert.getTours().stream().collect(Collectors.toSet());
		League league = new League(1,"FRANCE", "LFL", " ");
		Team team = new Team(1,"Karmine","",league);
		Set<Team> teams = new HashSet<>();
		teams.add(team);
		league.setTeams(teams);
		Players players = new Players(1,"Saken","mid",20,team);
		Uteam uteam = new Uteam("Dkorp",3,1,0,uleague,user,transfert.getEtatTransfert(),500);
		transfertService.makeOffer(vtours.stream().collect(Collectors.toList()).get(vtours.size()-1),uteam, players, 20);
		Assert.assertFalse( offresDao.findAll().isEmpty());

	}






	@Test
	@Transactional
	@Rollback(true)
	void testGetBestOffers(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		Set<Tour> vtours = new HashSet<>();
		Transfert transfert = new Transfert(1,0, vtours, uleague);
		transfertService.nouveauTour(transfert);
		vtours = transfert.getTours().stream().collect(Collectors.toSet());
		League league = new League(1,"FRANCE", "LFL", " ");
		Team team = new Team(1,"Karmine","",league);
		Set<Team> teams = new HashSet<>();
		teams.add(team);
		league.setTeams(teams);
		Players players = new Players(1,"Saken","mid",20,team);
		Players players2 = new Players(2,"Peng","mid",15,team);
		Tour tour = new Tour(1,1,transfert);
		Uteam uteam = new Uteam("Dkorp",3,1,0,uleague,user,transfert.getEtatTransfert(),500);
		Offres off1 = new Offres(1,50,uteam,players,tour);
		Offres off2 = new Offres(2,20,uteam,players2,tour);
		Offres off3 = new Offres(3,100,uteam,players,tour);
		List<Offres> offres = new ArrayList<>();
		offres.add(off1);
		offres.add(off2);
		offres.add(off3);
		tour.setOffres(offres.stream().collect(Collectors.toSet()));
		List<Offres> offresgagnates = transfertService.getBestOffers(tour);
		offresgagnates.forEach(o->System.out.println("prix "+ o.getPrixPropose()+ " joueur : "+o.getWantedPlayers()));
		Assert.assertTrue(!offresgagnates.isEmpty());
		Assert.assertTrue(offresgagnates.size()==2);


	}




	@Test
	void testIsTransfertOver(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1); //pour arrêter de créer de nouvelle league
		Set<Tour> vtours = new HashSet<>();
		Transfert transfert = new Transfert(1,2, vtours, uleague);
		transfertService.nouveauTour(transfert);
		vtours = transfert.getTours().stream().collect(Collectors.toSet());
		League league = new League(1,"FRANCE", "LFL", " ");
		Team team = new Team(1,"Karmine","",league);
		Set<Team> teams = new HashSet<>();
		teams.add(team);
		league.setTeams(teams);
		Players players = new Players(1,"Saken","mid",20,team);
		Players players2 = new Players(2,"Cabochard","top",15,team);
		Players players3 = new Players(3,"Skeanz","jungle",20,team);
		Players players4 = new Players(4,"Kaori","adc",15,team);
		Players players5 = new Players(5, "Advienne","support",10,team);
		Tour tour = new Tour(1,1,transfert);
		Uteam uteam = new Uteam("Dkorp",3,1,0,uleague,user,transfert.getEtatTransfert(),500);
		Set<Uteam> uteams = new HashSet<>();
		uteams.add(uteam);
		uteam.setUplayers(Arrays.asList(players,players2,players3,players4,players5).stream().collect(Collectors.toSet()));
		uleague.setUteams(uteams);
		uleague.setTransf(transfert);
		Assert.assertTrue(transfertService.isTransfertOver(uleague));

	}

	@Test
	void testIsTourOver(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		Transfert transfert = new Transfert(1,2, uleague);
		Tour tour = new Tour(1,1,transfert);
		Uteam uteam = new Uteam("Dkorp",3,1,0,uleague,user,transfert.getEtatTransfert(),500);
		transfert.setTours(Arrays.asList(tour).stream().collect(Collectors.toSet()));
		transfert.setUleague(uleague);
		uleague.setUteams(Arrays.asList(uteam).stream().collect(Collectors.toSet()));
		Assert.assertTrue(transfertService.isTourOver(tour));

	}


	@Test
	@Transactional
	void testSetPlayersToTeams(){

		Account acc = new Account("mathieu","leteston",LocalDate.parse("1995-12-21"),true,true);
		User user = new User(1,"damien@gmail.com",encoder.encode("Demorleau75-"),true,acc);
		Uleague uleague = new Uleague("league de Dems","privé","LCK",user);
		uleague.setUleague_id(1);
		Transfert transfert = new Transfert(1,2, uleague);
		Tour tour = new Tour(1,1,transfert);
		Uteam uteam = new Uteam("Dkorp",3,1,0,uleague,user,transfert.getEtatTransfert(),500);
		transfert.setTours(Arrays.asList(tour).stream().collect(Collectors.toSet()));
		transfert.setUleague(uleague);
		uleague.setUteams(Arrays.asList(uteam).stream().collect(Collectors.toSet()));
		League league = new League(1,"FRANCE", "LFL", " ");
		Team team = new Team(1,"Karmine","",league);
		Set<Team> teams = new HashSet<>();
		teams.add(team);
		league.setTeams(teams);
		Players players = new Players(1,"Saken","mid",20,team);
		Players players2 = new Players(2,"Peng","mid",15,team);
		Offres of1 = new Offres(50,uteam,players,tour,2);
		Offres of2 = new Offres(100,uteam,players2,tour,1);
		List<Offres> offres = Arrays.asList(of1,of2);
		transfertService.saveAllplayerstoTeamWithBestOffers(offres);
		uteamDao.findAll().get(0).getUplayers().forEach(p->System.out.println(p.getName()));
		List<Players> pls = uteamDao.findAll().get(0).getUplayers().stream().collect(Collectors.toList());
		Assert.assertTrue(pls.get(0).getName().equals(players.getName()));

	}

	@Test
	@Transactional
	void testGetPlayersToBuy(){

	}
}
