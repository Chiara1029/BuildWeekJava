package it.team8.bw.exception;

public class VendingMachineInactiveException extends Exception {
    public VendingMachineInactiveException() {
        super("Vending Machine inactive");
    }

    public VendingMachineInactiveException(String message) {
        super(message);
    }
}