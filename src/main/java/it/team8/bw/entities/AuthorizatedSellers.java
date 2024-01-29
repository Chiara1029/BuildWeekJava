package it.team8.bw.entities;

import it.team8.bw.abstractClass.TicketIssue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("authorizated-sellers")
public class AuthorizatedSellers extends TicketIssue {

}
