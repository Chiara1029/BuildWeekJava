package it.team8.bw;

import it.team8.bw.dao.MeansDAO;
import it.team8.bw.dao.RoadsDAO;
import it.team8.bw.dao.TicketIssueDAO;
import it.team8.bw.dao.TicketOfficeDAO;
import it.team8.bw.exception.TicketOfficeNotFoundException;
import it.team8.bw.utils.Fulltable;

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
        //Fulltable crea automaticamente la tabella nel database, inserendo valori casuali con Faker per ogni elemento
        Fulltable.creation(meansDAO, roadsDAO, ticketIssueDAO, ticketOfficeDAO);

        //la data della convalida dei biglietti viene istanziata di default come "null"
        //riceve un valore solo nel momento in cui viene convalidato e viene associato all'ID di un mezzo
        LocalDate now = LocalDate.now();
        ticketOfficeDAO.setConvalidation(19L, meansDAO.findById(9L));

        //restituisce la lista di tutti i biglietti emessi nell'intervallo di tempo indicato
        ticketOfficeDAO.getTicketsByTime(now.minusDays(20), now);

        //restituisce la lista di tutti i biglietti convalidati nell'intervallo di tempo indicato
        ticketOfficeDAO.getConvalidationTicketsByTime(now.minusDays(20), now);

        //restituisce la lista di tutti gli abbonamenti emessi nell'intervallo di tempo indicato
        ticketOfficeDAO.getSubscriptionByTime(now.minusDays(20), now);

        //restituisce la lista di tutti i biglietti emessi da uno specifico rivenditore
        ticketOfficeDAO.getTicketsByLocation("jellyfish");
        ticketOfficeDAO.getTicketsByLocation("The Carpenters");

        //restituisce la lista di tutti i biglietti emessi da uno specifico rivenditore
        ticketOfficeDAO.getSubscriptionByLocation("jellyfish");
        ticketOfficeDAO.getSubscriptionByLocation("The Carpenters");

        //restituisce un valore booleano per indicare se l'abbonamento sia ancora valido oppure no
        ticketOfficeDAO.getSubscriptionValidation(17);
        ticketOfficeDAO.getSubscriptionValidation(20);

        roadsDAO.roundTrace(8, 8);


        ticketOfficeDAO.getConvalidationTicketsByMean(meansDAO.findById(9L));

        meansDAO.getMantenanceById(9L);

        ticketOfficeDAO.renewalSubscription(37);


        try {

            ticketOfficeDAO.findById(8432209);

        } catch (TicketOfficeNotFoundException ex) {

            System.err.println("Error type:" + ex.getMessage());

        } finally {
            emf.close();
            em.close();
        }


    }
}
