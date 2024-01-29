package it.team8.bw.entities;

import it.team8.bw.abstractClass.TicketOffice;
import it.team8.bw.enums.SubscriptionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscription")
public class Subscription extends TicketOffice {

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
    private LocalDate annualDeadline;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate paymentDay;

    public Subscription(LocalDate emissionDate, SubscriptionType subscriptionType, User user, LocalDate paymentDay) {
        super(emissionDate);
        this.subscriptionType = subscriptionType;
        this.annualDeadline = emissionDate.plusYears(1);
        this.user = user;
        this.paymentDay = paymentDay;
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
