package it.team8.bw.dao;

import it.team8.bw.abstractClass.TicketIssue;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TicketIssueDAO {
    private final EntityManager em;

    public TicketIssueDAO(EntityManager em) {
        this.em = em;
    }

    public void save(TicketIssue ticketIssue){
        try {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticketIssue);
        transaction.commit();
        System.out.println("Ticket " + ticketIssue + " has been saved with success!");
        }catch (Exception e){
            System.out.println("Error while saving TicketIssue: "+e.getMessage());
        }
    }

    public TicketIssue findById (long id){
        return em.find(TicketIssue.class, id);
    }

    public void findByIdAndDelete(long id){
        try{

        TicketIssue found = this.findById(id);
        if(found != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Ticket " + found + " has been deleted with success!");
        } else {
            System.out.println("Ticket Issue with id: " + id + " not found.");
        }
        }catch (Exception e){
            System.out.println("Error while deliting TicketIssue by ID: "+ e.getMessage());
        }
    }
}
