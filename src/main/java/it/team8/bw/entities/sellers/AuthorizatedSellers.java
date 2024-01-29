package it.team8.bw.entities.sellers;

import it.team8.bw.abstractClass.TicketIssue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("authorizated_sellers")
public class AuthorizatedSellers extends TicketIssue {

    public AuthorizatedSellers() {
    }

    public AuthorizatedSellers(String sellerName) {
        super(sellerName);
    }
}
