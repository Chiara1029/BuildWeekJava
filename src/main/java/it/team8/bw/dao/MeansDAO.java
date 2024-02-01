package it.team8.bw.dao;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.entities.means.Maintenance;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class MeansDAO {
    private final EntityManager em;

    public MeansDAO(EntityManager em) {
        this.em = em;
    }

    public void saveMeans(Means mean) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mean);
        transaction.commit();
        System.out.println("Mean " + mean + " has been saved with success!");
    }

    public void saveMaintenance(Maintenance maintenance) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(maintenance);
        transaction.commit();
        System.out.println("Maintenance " + maintenance + " has been saved with success!");
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
            System.out.println("Means " + found + " has been deleted with success!");
        } else {
            System.out.println("Means with id :" + id + " not found");
        }
    }

    public List<Maintenance> getMantenanceById(long id) {
        TypedQuery<Maintenance> query = em.createQuery("SELECT m FROM Maintenance m JOIN m.mean t WHERE t.id = :id", Maintenance.class);
        query.setParameter("id", id);
        List<Maintenance> result = query.getResultList();
        if (result.isEmpty()) {
            System.out.println("This mean is on service and has never been on maintenance.");
        } else {
            System.out.println("Maintenance on this mean: " + result.size());
            result.forEach(System.out::println);
        }
        return result;
    }
}
