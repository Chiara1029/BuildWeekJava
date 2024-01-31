package it.team8.bw.utils;

import com.github.javafaker.Faker;
import it.team8.bw.abstractClass.Means;
import it.team8.bw.abstractClass.TicketIssue;
import it.team8.bw.abstractClass.TicketOffice;
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
import it.team8.bw.exception.VendingMachineInactiveException;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Fulltable {
    public static Supplier<Draft> newDraft = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        Random rnd = new Random();
        Draft draft = new Draft(faker.address().city(), faker.address().city(), rnd.nextInt(30, 50));
        return draft;
    };
    public static Supplier<User> newUser = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new User(faker.name().firstName(), faker.name().lastName());
    };

    ;
    public static Supplier<VendingMachine> newVendingMachine = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        Random rndm = new Random();
        VendingMachineStatus[] values = VendingMachineStatus.values();
        return new VendingMachine(faker.rockBand().name(), values[rndm.nextInt(values.length)]);
    };
    public static Supplier<AuthorizatedSellers> newAuthorizatedSellers = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new AuthorizatedSellers(faker.animal().name());
    };

    public static Supplier<Means> newTram(Draft draft) {
        return () -> {
            Faker faker = new Faker(Locale.ENGLISH);
            Random rndm = new Random();
            return new Tram(MeansStatus.SERVICE, draft);
        };
    }

    public static Supplier<Means> newAutobus(Draft draft) {
        return () -> {
            Faker faker = new Faker(Locale.ENGLISH);
            Random rndm = new Random();
            MeansStatus[] values = MeansStatus.values();
            return new Autobus(values[rndm.nextInt(values.length)], draft);
        };

    }

    public static Supplier<Ticket> newTicket(TicketIssue ticketIssue) {
        return () -> new Ticket(LocalDate.now(), ticketIssue);
    }

    ;

    public static Supplier<Subscription> newSubscription(User user, TicketIssue ticketIssue) {
        SubscriptionType[] values = SubscriptionType.values();
        Random rndm = new Random();
        return () -> new Subscription(LocalDate.now(), ticketIssue, values[rndm.nextInt(values.length)], LocalDate.now(), user);
    }

    public static Supplier<Maintenance> newMaintenance(Means means) {
        return () -> {
            LocalDate now = LocalDate.now();
            Random rndm = new Random();
            return new Maintenance(now.minusDays(rndm.nextInt(200, 250)), now.minusDays(rndm.nextInt(180, 200)), means);
        };
    }

    public static Supplier<Stop> newStop(Draft draft) {
        return () -> {
            Faker faker = new Faker(Locale.ENGLISH);
            return new Stop(faker.address().firstName(), faker.address().firstName(), draft);
        };
    }

    public static void creation(MeansDAO meansDAO, RoadsDAO roadsDAO, TicketIssueDAO ticketIssueDAO, TicketOfficeDAO ticketOfficeDAO) {
        for (int i = 0; i < 3; i++) {
            Draft draft = newDraft.get();
            roadsDAO.saveDraft(draft);

            Draft draft2 = newDraft.get();
            roadsDAO.saveDraft(draft2);

            for (int p = 0; p < 3; p++) {
                roadsDAO.saveStop(newStop(draft).get());
                roadsDAO.saveStop(newStop(draft2).get());
            }


            Means autobus = newAutobus(draft).get();
            meansDAO.saveMeans(autobus);

            Means tram = newTram(draft2).get();
            meansDAO.saveMeans(tram);

            Maintenance maintenance = newMaintenance(autobus).get();
            Maintenance maintenance2 = newMaintenance(autobus).get();

            meansDAO.saveMaintenance(maintenance);
            meansDAO.saveMaintenance(maintenance2);

            VendingMachine vendingMachine = newVendingMachine.get();
            TicketIssue authorizatedSellers = newAuthorizatedSellers.get();

            ticketIssueDAO.save(vendingMachine);
            ticketIssueDAO.save(authorizatedSellers);

            for (int j = 0; j < 2; j++) {
                User user = newUser.get();
                ticketOfficeDAO.saveUser(user);

                try {
                    if (vendingMachine.getVendingMachineStatus() == VendingMachineStatus.INACTIVE) {
                        throw new VendingMachineInactiveException();
                    } else {
                        Ticket ticket = newTicket(vendingMachine).get();
                        ticketOfficeDAO.saveTicketOffice(ticket);
                    }
                } catch (Exception e) {
                    System.err.println("Errore: " + e.getMessage());
                }


                TicketOffice subscription = newSubscription(user, authorizatedSellers).get();


                ticketOfficeDAO.saveTicketOffice(subscription);
            }

        }


    }


}
