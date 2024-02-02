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

        //dato l'ID di una tratta, un orario di servizio e il numero dei giri, restituisce il tempo effettivo di percorrenza
        //e il numero di tappe percorse
        roadsDAO.roundTrace(8, 8, 1);

        //dato l'ID di un mezzo specifico, restituisce la lista dei biglietti convalidati su quel mezzo
        ticketOfficeDAO.getConvalidationTicketsByMean(meansDAO.findById(9L));

        //dato l'ID di un mezzo specifico, restituisce la lista di tutte le manutenzioni di quel mezzo
        meansDAO.getMantenanceById(9L);

        //se l'abbonamento dell'utente è scaduto, questo metodo permette di rinnovarlo andando ad aggiornare la data di scadenza
        ticketOfficeDAO.renewalSubscription(16);


        //per testare e scatenare un errore, inseriamo un dato errato tramite scanner, salvando l'errore nel db
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Insert Long to find a TicketOffice by ID or a different type to generate an error: ");
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
        scanner.close();
    }
}
