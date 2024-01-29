package it.team8.bw.abstractClass;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ticket_issue")
@DiscriminatorColumn(name = "type_of_issue")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TicketIssue {
    @Column(nullable = false)
    protected String sellerName;

    @OneToMany(mappedBy = "ticket_issue_id")
    protected Set<TicketOffice> ticketOffices;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    protected TicketIssue() {
    }

    public TicketIssue(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }
}
