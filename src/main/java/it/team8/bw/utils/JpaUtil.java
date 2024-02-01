package it.team8.bw.utils;

import lombok.Getter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaUtil {
    @Getter
    private static final EntityManagerFactory entityManagerFactory;


    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("gestionetrasporti");
        } catch (Throwable ex) {
            System.err.println("Inizializzazione della creazione dell'EntityManagerFactory è fallita. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
