package it.team8.bw.abstractClass;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket_office")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TicketOffice {
    @Column(nullable = false)
    protected LocalDate emissionDate;
    @ManyToOne
    @JoinColumn(name = "ticketIssue")
    protected TicketIssue ticketIssue;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    protected TicketOffice() {
    }

    public TicketOffice(LocalDate emissionDate, TicketIssue ticketIssue) {
        this.emissionDate = emissionDate;
        this.ticketIssue = ticketIssue;

    }


    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDate emissionDate) {
        this.emissionDate = emissionDate;
    }

    public TicketIssue getTicket() {
        return ticketIssue;
    }

    public void setTicket(TicketIssue ticket_issue_id) {
        this.ticketIssue = ticket_issue_id;
    }

    public Long getId() {
        return id;
    }
}
