package it.team8.bw.entities.users;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.abstractClass.TicketIssue;
import it.team8.bw.abstractClass.TicketOffice;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket extends TicketOffice {


    private boolean obliterated;
    private LocalDate convalidationDate;
    @ManyToOne
    @JoinColumn(name = "means_id")
    private Means meanUsed;

    public Ticket() {
    }

    public Ticket(LocalDate emissionDate, TicketIssue ticketIssue) {
        super(emissionDate, ticketIssue);
        this.obliterated = false;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", obliterated=" + obliterated +
                ", convalidationDate=" + convalidationDate +
                '}';
    }

    public boolean isObliterated() {
        return obliterated;
    }

    public void setObliterated(boolean obliterated) {
        this.obliterated = obliterated;
    }

    public LocalDate getConvalidationDate() {
        return convalidationDate;
    }

    public void setConvalidationDate(LocalDate convalidationDate) {
        this.convalidationDate = convalidationDate;
    }
}
