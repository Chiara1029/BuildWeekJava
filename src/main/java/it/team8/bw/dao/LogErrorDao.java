package it.team8.bw.dao;

import it.team8.bw.exception.LogError;
import it.team8.bw.utils.JpaUtil;

import javax.persistence.EntityManager;

public class LogErrorDao {
    public void saveError(LogError a) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
