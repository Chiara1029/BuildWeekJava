package it.team8.bw.entities.users;

import it.team8.bw.abstractClass.TicketIssue;
import it.team8.bw.abstractClass.TicketOffice;
import it.team8.bw.enums.SubscriptionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscription")
public class Subscription extends TicketOffice {
    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionType=" + subscriptionType +
                ", annualDeadline=" + annualDeadline +
                ", user=" + user +
                ", paymentDay=" + paymentDay +
                ", emissionDate=" + emissionDate +
                '}';
    }

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
    private LocalDate annualDeadline;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate paymentDay;

    public Subscription(LocalDate emissionDate, TicketIssue ticketIssue, SubscriptionType subscriptionType, LocalDate paymentDay, User user) {
        super(emissionDate, ticketIssue);
        this.subscriptionType = subscriptionType;
        this.annualDeadline = emissionDate.plusYears(1);
        this.paymentDay = paymentDay;
        this.user = user;
    }

    public Subscription() {
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public LocalDate getAnnualDeadline() {
        return annualDeadline;
    }

    public void setAnnualDeadline(LocalDate annualDeadline) {
        this.annualDeadline = annualDeadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(LocalDate paymentDay) {
        this.paymentDay = paymentDay;
    }


}
