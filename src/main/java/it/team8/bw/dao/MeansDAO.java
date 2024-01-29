package it.team8.bw.dao;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.entities.means.Maintenance;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MeansDAO {
    private final EntityManager em;


    public MeansDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * function to save autobus and tram
     *
     * @param means
     */
    public void saveMeans(Means means) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(means);

        transaction.commit();

        System.out.println("Means " + means + " add with success!");
    }

    public void saveMaintenance(Maintenance maintenance) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(maintenance);

        transaction.commit();

        System.out.println("Maintenance " + maintenance + " add with success!");
    }

    public Means findById(long id) {

        return em.find(Means.class, id);
    }

    public void findByIdAndDelete(long id) {

        Means found = this.findById(id);

        if (found != null) {

            EntityTransaction transaction = em.getTransaction();


            transaction.begin();


            em.remove(found);


            transaction.commit();

            System.out.println("Means" + found + "and delete with success !");

        } else {

            System.out.println("Means with id :" + id + " didn't found");
        }
    }

}
