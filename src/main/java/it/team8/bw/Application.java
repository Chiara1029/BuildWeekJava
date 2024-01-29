package it.team8.bw;

import it.team8.bw.dao.MeansDAO;
import it.team8.bw.dao.RoadsDAO;
import it.team8.bw.dao.TicketIssueDAO;
import it.team8.bw.dao.TicketOfficeDAO;
import it.team8.bw.utils.Fulltable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!");
        MeansDAO meansDAO = new MeansDAO(em);
        RoadsDAO roadsDAO = new RoadsDAO(em);
        TicketIssueDAO ticketIssueDAO = new TicketIssueDAO(em);
        TicketOfficeDAO ticketOfficeDAO = new TicketOfficeDAO(em);
        Fulltable.creation(meansDAO, roadsDAO, ticketIssueDAO, ticketOfficeDAO);


    }
}
