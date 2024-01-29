package it.team8.bw.abstractClass;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket_office")
public abstract class TicketOffice {
    protected LocalDate emissionDate;
    @ManyToOne
    @JoinColumn(name = "ticket_issue_id")
    protected TicketIssue ticket_issue_id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    protected TicketOffice() {
    }

    public TicketOffice(LocalDate emissionDate) {
        this.emissionDate = emissionDate;

    }

    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDate emissionDate) {
        this.emissionDate = emissionDate;
    }

    public TicketIssue getTicket_issue_id() {
        return ticket_issue_id;
    }

    public void setTicket_issue_id(TicketIssue ticket_issue_id) {
        this.ticket_issue_id = ticket_issue_id;
    }

    public Long getId() {
        return id;
    }
}
