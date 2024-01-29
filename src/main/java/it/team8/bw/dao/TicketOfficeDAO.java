package it.team8.bw.dao;

import it.team8.bw.abstractClass.TicketOffice;
import it.team8.bw.entities.users.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TicketOfficeDAO {

    private final EntityManager em;


    public TicketOfficeDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * function to save autobus and tram
     *
     * @param ticketOffice and User
     */
    public void saveTicketOffice(TicketOffice ticketOffice) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticketOffice);
        transaction.commit();
        System.out.println("TicketOffice " + ticketOffice + " add with success!");
    }

    public void saveUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        System.out.println("User " + user + " add with success!");
    }


    public TicketOffice findById(long id) {
        return em.find(TicketOffice.class, id);
    }

    public void findByIdAndDelete(long id) {
        TicketOffice found = this.findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();


            em.remove(found);


            transaction.commit();

            System.out.println("TicketOffice" + found + "and delete with success !");

        } else {

            System.out.println("TicketOffice with id :" + id + " didn't found");
        }
    }

}
