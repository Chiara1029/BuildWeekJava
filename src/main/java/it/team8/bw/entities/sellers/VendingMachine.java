package it.team8.bw.entities.sellers;

import it.team8.bw.abstractClass.TicketIssue;
import it.team8.bw.enums.VendingMachineStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("vending_machine")
public class VendingMachine extends TicketIssue {

    @Override
    public String toString() {
        return "VendingMachine{" +
                "vendingMachineStatus=" + vendingMachineStatus +
                ", sellerName='" + sellerName + '\'' +
                ", ticketOffices=" + ticketOffices +
                '}';
    }

    @Enumerated(EnumType.STRING)
    private VendingMachineStatus vendingMachineStatus;

    public VendingMachine() {
    }

    public VendingMachine(String sellerName, VendingMachineStatus vendingMachineStatus) {
        super(sellerName);
        this.vendingMachineStatus = vendingMachineStatus;
    }

    public VendingMachineStatus getVendingMachineStatus() {
        return vendingMachineStatus;
    }

    public void setVendingMachineStatus(VendingMachineStatus vendingMachineStatus) {
        this.vendingMachineStatus = vendingMachineStatus;
    }
}
