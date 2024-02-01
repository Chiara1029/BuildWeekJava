package it.team8.bw.exception;

public class TicketOfficeNotFoundException extends RuntimeException {

    public TicketOfficeNotFoundException() {
        super("Ticket office not found");
    }

    public TicketOfficeNotFoundException(String message) {
        super(message);
    }
}
