package it.team8.bw;

import it.team8.bw.dao.MeansDAO;
import it.team8.bw.dao.RoadsDAO;
import it.team8.bw.dao.TicketIssueDAO;
import it.team8.bw.dao.TicketOfficeDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!");

        MeansDAO meansDAO = new MeansDAO(em);
        RoadsDAO roadsDAO = new RoadsDAO(em);
        TicketIssueDAO ticketIssueDAO = new TicketIssueDAO(em);
        TicketOfficeDAO ticketOfficeDAO = new TicketOfficeDAO(em);

//        Fulltable.creation(meansDAO, roadsDAO, ticketIssueDAO, ticketOfficeDAO);

        LocalDate now = LocalDate.now();


        ticketOfficeDAO.setConvalidation(19L, meansDAO.findById(9L));


        ticketOfficeDAO.getTicketsByTime(now.minusDays(20), now);

        ticketOfficeDAO.getConvalidationTicketsByTime(now.minusDays(20), now);


        ticketOfficeDAO.getSubscriptionByTime(now.minusDays(20), now);

        ticketOfficeDAO.getTicketsByLocation("jellyfish");
        ticketOfficeDAO.getTicketsByLocation("The Carpenters");

        ticketOfficeDAO.getSubscriptionByLocation("jellyfish");
        ticketOfficeDAO.getSubscriptionByLocation("The Carpenters");

        roadsDAO.roundTrace(8, 8);

    }
}
