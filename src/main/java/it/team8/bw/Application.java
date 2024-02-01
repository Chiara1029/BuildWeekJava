package it.team8.bw;

import it.team8.bw.dao.*;
import it.team8.bw.exception.LogError;
import it.team8.bw.utils.Fulltable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");

    private static LogError log = new LogError();

    public static void main(String[] args) {


        EntityManager em = emf.createEntityManager();


        MeansDAO meansDAO = new MeansDAO(em);
        RoadsDAO roadsDAO = new RoadsDAO(em);
        TicketIssueDAO ticketIssueDAO = new TicketIssueDAO(em);
        TicketOfficeDAO ticketOfficeDAO = new TicketOfficeDAO(em);
        LogErrorDao logErrorDao = new LogErrorDao();

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
        try {
            ticketOfficeDAO.getSubscriptionValidation(17);
            ticketOfficeDAO.getSubscriptionValidation(20);
        } catch (NullPointerException ex) {
            System.out.println("Error:" + ex);
        }


        roadsDAO.roundTrace(8, 8, 1);


        ticketOfficeDAO.getConvalidationTicketsByMean(meansDAO.findById(9L));

        meansDAO.getMantenanceById(9L);

        ticketOfficeDAO.renewalSubscription(16);

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Insert Potato");
            long input = Long.parseLong(scanner.nextLine());
            ticketOfficeDAO.findById(input);

        } catch (Exception ex) {
            StackTraceElement[] error = ex.getStackTrace();
            String temp = "";
            for (StackTraceElement element : error) {
                temp += element.toString() + "____";
            }

            log.setMessage(temp.substring(0, 200));

            log.setTimestamp(new Date());
            logErrorDao.saveError(log);
            System.out.println(log);


        } finally {
            emf.close();
            em.close();
        }
        emf.close();
        em.close();


    }
}
