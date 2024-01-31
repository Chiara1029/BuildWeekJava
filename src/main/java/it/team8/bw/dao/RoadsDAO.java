package it.team8.bw.dao;

import it.team8.bw.entities.road.Draft;
import it.team8.bw.entities.road.Stop;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RoadsDAO {
    private final EntityManager em;

    public RoadsDAO(EntityManager em) {
        this.em = em;
    }

    public void saveDraft(Draft draft) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(draft);
        transaction.commit();
        System.out.println("Draft " + draft + " has been saved with success!");
    }

    public void saveStop(Stop stop) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(stop);
        transaction.commit();
        System.out.println("Stop " + stop + " has been saved with success!");
    }

    public Draft findById(long id) {
        return em.find(Draft.class, id);
    }

    public void findByIdAndDelete(long id) {
        Draft found = this.findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Draft " + found + " has been deleted with success!");
        } else {
            System.out.println("Draft with id: " + id + " not found");
        }
    }

    public void roundTrace(int hourService, int round) {
        try{

        int arrival = 0;
        int stop = 0;
        for (int i = 0; i < round; i++) {
            arrival++;
            stop += 2;
        }
        int effectiveMinutesTime = hourService * 60 / round;

        System.out.println("Effective time is " + effectiveMinutesTime + " minute");
        System.out.println("The mean travel on stop " + stop + " time and on arrival " + arrival + " time");
        }catch (Exception e){
            System.out.println("Error in RoundTrace: "+e.getMessage());
        }

    }

}


