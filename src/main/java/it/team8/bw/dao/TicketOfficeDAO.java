package it.team8.bw.dao;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.abstractClass.TicketOffice;
import it.team8.bw.entities.users.Subscription;
import it.team8.bw.entities.users.Ticket;
import it.team8.bw.entities.users.User;
import it.team8.bw.enums.SubscriptionType;
import it.team8.bw.exception.TicketOfficeNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TicketOfficeDAO {
    private final EntityManager em;

    public TicketOfficeDAO(EntityManager em) {
        this.em = em;
    }


    public void saveTicketOffice(TicketOffice ticketOffice) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticketOffice);
        transaction.commit();
        System.out.println("TicketOffice " + ticketOffice + " has been saved with success!");
    }

    public void saveUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        System.out.println("User " + user + " has been saved with success!");
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
            System.out.println("TicketOffice " + found + " has been deleted with success !");
        } else {
            throw new TicketOfficeNotFoundException();
        }
    }

    public void setConvalidation(Long ticketId, Means meanId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.createQuery("UPDATE Ticket t SET t.convalidationDate = CURRENT_DATE, t.obliterated = TRUE,  t.meanUsed = :meanId WHERE t.id = :id AND convalidationDate IS NULL")
                    .setParameter("id", ticketId).setParameter("meanId", meanId).executeUpdate();
            transaction.commit();
            System.out.println("Ticket " + ticketId + " has been convalidated!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error during ticket validation: " + e.getMessage());
        }
    }

    public List<Ticket> getTicketsByTime(LocalDate startDate, LocalDate endDate) {
        try {


            TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.emissionDate BETWEEN :startDate AND :endDate", Ticket.class);

            query.setParameter("startDate", startDate);

            query.setParameter("endDate", endDate);

            List<Ticket> result = query.getResultList();


            if (result.isEmpty()) {
                System.out.println("No tickets were emitted during this period.");
            } else {
                System.out.println("Tickets emitted from " + startDate + " to " + endDate + ": ");
                result.forEach(System.out::println);
            }
            return result;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No tickets were emitted during this period.");
            return null;
        }
    }

    public List<Ticket> getConvalidationTicketsByTime(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.obliterated = TRUE AND t.convalidationDate BETWEEN :startDate AND :endDate", Ticket.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Ticket> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("No tickets were obliterated during this period.");
            } else {
                System.out.println("Tickets obliterated from " + startDate + " to " + endDate + ": ");
                result.forEach(System.out::println);
            }
            return result;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No tickets were obliterated during this period.");
            return null;
        }
    }

    public List<Ticket> getConvalidationTicketsByMean(Means mean) {
        try {
            TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.meanUsed = :mean", Ticket.class);
            query.setParameter("mean", mean);
            List<Ticket> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("No tickets were obliterated on this mean.");
            } else {
                System.out.println("Tickets obliterated on mean: " + mean.getId());
                result.forEach(System.out::println);
            }
            return result;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No tickets were obliterated on this mean.");
            return null;
        }
    }

    public List<Ticket> getTicketsByLocation(String sellerName) {
        try {
            TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t JOIN t.ticketIssue s WHERE s.sellerName = :sellerName", Ticket.class);
            query.setParameter("sellerName", sellerName);
            List<Ticket> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("No tickets were sold in: " + sellerName);
            } else {
                System.out.println("Tickets sold in: " + sellerName + result.size());
                result.forEach(System.out::println);
            }
            return result;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No tickets were sold in: " + sellerName);
            return Collections.emptyList();
        }
    }

    public List<Subscription> getSubscriptionByTime(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s WHERE s.emissionDate BETWEEN :startDate AND :endDate", Subscription.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Subscription> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("No subscriptions have been activated in this period.");
            } else {
                System.out.println("Subscriptions activated from " + startDate + " to " + endDate + ": ");
                result.forEach(System.out::println);
            }
            return result;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No subscriptions have been activated in this period.");
            return Collections.emptyList();
        }
    }

    public List<Subscription> getSubscriptionByLocation(String sellerName) {
        try {
            TypedQuery<Subscription> query = em.createQuery("SELECT b FROM Subscription b JOIN b.ticketIssue s WHERE s.sellerName = :sellerName", Subscription.class);
            query.setParameter("sellerName", sellerName);
            List<Subscription> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("No subscriptions were sold in: " + sellerName);
            } else {
                System.out.println("Subscription sold in: " + sellerName + result.size());
                result.forEach(System.out::println);
            }
            return result;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No subscriptions were sold in: " + sellerName);
            return Collections.emptyList();
        }
    }


    public void renewalSubscription(long idSubscription) {
        LocalDate now = LocalDate.now();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        TicketOffice subscription = this.findById(idSubscription);
        LocalDate newAnnualDeadline = now.plusYears(1);
        LocalDate newPaymentDay;
        if (subscription instanceof Subscription) {
            Subscription subscription1 = (Subscription) subscription;
            if (subscription1.getSubscriptionType() == SubscriptionType.WEEKLY) {
                newPaymentDay = now.plusDays(7);
            } else {
                newPaymentDay = now.plusDays(30);
            }
            em.createQuery("UPDATE Subscription s SET s.annualDeadline= :newAnnualDeadline, s.paymentDay= :newPaymentDay WHERE s.id= :idSubscription ")
                    .setParameter("newAnnualDeadline", newAnnualDeadline)
                    .setParameter("newPaymentDay", newPaymentDay)
                    .setParameter("idSubscription", idSubscription)
                    .executeUpdate();
            transaction.commit();
            System.out.println("This subscription has been renewed.");
        } else {

        }

    }

    public boolean getSubscriptionValidation(long subId) {
        try {
            Subscription found = em.find(Subscription.class, subId);
            if (found == null) {
                throw new NullPointerException("Subscription not found");
            }
            LocalDate now = LocalDate.now();
            if (found.getAnnualDeadline().isAfter(now)) {
                LocalDate validPaymentDate;
                if (found.getSubscriptionType() == SubscriptionType.MONTHLY) {
                    validPaymentDate = now.minusDays(30);
                } else if (found.getSubscriptionType() == SubscriptionType.WEEKLY) {
                    validPaymentDate = now.minusDays(7);
                } else {
                    throw new IllegalArgumentException("Unsupported subscription type");
                }
                if (found.getPaymentDay().isAfter(validPaymentDate)) {
                    System.out.println("This subscription is valid.");
                    return true;
                }
            }
            System.out.println("This subscription is invalid.");
            return false;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
}
