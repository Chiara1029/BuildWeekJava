package it.team8.bw.utils;

import com.github.javafaker.Faker;
import it.team8.bw.abstractClass.Means;
import it.team8.bw.abstractClass.TicketIssue;
import it.team8.bw.dao.MeansDAO;
import it.team8.bw.dao.RoadsDAO;
import it.team8.bw.dao.TicketIssueDAO;
import it.team8.bw.dao.TicketOfficeDAO;
import it.team8.bw.entities.means.Autobus;
import it.team8.bw.entities.means.Maintenance;
import it.team8.bw.entities.means.Tram;
import it.team8.bw.entities.road.Draft;
import it.team8.bw.entities.road.Stop;
import it.team8.bw.entities.sellers.AuthorizatedSellers;
import it.team8.bw.entities.sellers.VendingMachine;
import it.team8.bw.entities.users.Subscription;
import it.team8.bw.entities.users.Ticket;
import it.team8.bw.entities.users.User;
import it.team8.bw.enums.MeansStatus;
import it.team8.bw.enums.SubscriptionType;
import it.team8.bw.enums.VendingMachineStatus;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Fulltable {
    public static Supplier<Stop> newStop = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new Stop(faker.address().firstName(), faker.address().firstName());
    };


    public static Supplier<Draft> newDraft = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        Random rnd = new Random();
        Draft draft = new Draft(faker.address().city(), faker.address().city());
//        for (int i = 0; i < rnd.nextInt(1, 3); i++) {
//            draft.addStop(newStop.get());
//        }
        return draft;
    };
    public static Supplier<Means> newAutobus = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        Random rndm = new Random();
        return new Autobus(MeansStatus.SERVICE);
    };
    public static Supplier<Means> newTram = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        Random rndm = new Random();
        return new Tram(MeansStatus.SERVICE);
    };
    public static Supplier<User> newUser = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new User(faker.name().firstName(), faker.name().lastName());
    };
    public static Supplier<Subscription> newSubscription = () -> {
        return new Subscription(LocalDate.now(), SubscriptionType.WEEKLY, LocalDate.now());
    };
    public static Supplier<Ticket> newTicket = () -> {
        return new Ticket(LocalDate.now());
    };
    public static Supplier<Maintenance> newMaintenance = () -> {
        LocalDate now = LocalDate.now();
        Random rndm = new Random();
        return new Maintenance(now.minusDays(rndm.nextInt(200, 250)), now.minusDays(rndm.nextInt(180, 200)));
    };
    public static Supplier<VendingMachine> newVendingMachine = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new VendingMachine(faker.rockBand().name(), VendingMachineStatus.ACTIVE);
    };
    public static Supplier<AuthorizatedSellers> newAuthorizatedSellers = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new AuthorizatedSellers(faker.animal().name());
    };

    public static void creation(MeansDAO meansDAO, RoadsDAO roadsDAO, TicketIssueDAO ticketIssueDAO, TicketOfficeDAO ticketOfficeDAO) {

        Draft draft = newDraft.get();
        roadsDAO.save(draft);

        Draft draft2 = newDraft.get();
        roadsDAO.save(draft2);

        Maintenance maintenance = newMaintenance.get();


        Means autobus = newAutobus.get();
        autobus.setDraft(draft);
        meansDAO.save(autobus);

        Means tram = newTram.get();
        tram.setDraft(draft2);
        meansDAO.save(tram);

        TicketIssue vendingMachine = newVendingMachine.get();
        TicketIssue authorizatedSellers = newAuthorizatedSellers.get();

        ticketIssueDAO.save(vendingMachine);
        ticketIssueDAO.save(authorizatedSellers);

        for (int i = 0; i < 2; i++) {
            User user = newUser.get();
            Ticket ticket = newTicket.get();
            Subscription subscription = newSubscription.get();
            subscription.setUser(user);
            ticketOfficeDAO.save(ticket);
            ticketOfficeDAO.save(subscription);

        }


    }


}
