package Client;

import SupportFiles.Ticket;
import SupportFiles.TicketStatus;
import SupportFiles.Location;
import Client.Modules.RFIDModule;
import Client.Modules.TicketModule;
import Gates.GateResponse;
import Gates.InGate;
import Gates.IGate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

interface IClient {
    public void buyTicket(Location from, Location to);

    public void makeGateRequest(Ticket ticket, IGate gate);
}

public class Client implements IClient {

    private RFIDModule rfidModule;
    private TicketModule ticketModule;
    private ArrayList<Ticket> ticketList;

    public Client(RFIDModule rfidModule, TicketModule ticketModule) {
        this.rfidModule = rfidModule;
        this.ticketModule = ticketModule;
        this.ticketList = new ArrayList<Ticket>();
    }

    public Client() {
        this.rfidModule = new RFIDModule();
        this.ticketModule = new TicketModule();
        this.ticketList = new ArrayList<Ticket>();
    }

    public Ticket getTicket() {
        if (ticketList.isEmpty()) return null;

        return ticketList.stream()
                         .filter(x -> x.getTicketStatus() == TicketStatus.BOUGHT)
                         .findFirst()
                         .get();
    }

    @Override
    public void buyTicket(Location from, Location to) {
        // TODO
    }

    // Simulates situation when we attach ticket to the gate
    // (RFID detects gate)
    @Override
    public void makeGateRequest(Ticket ticket, IGate gate) {
        GateResponse response = rfidModule.sendRequest(ticket, gate);
        System.out.println(response);
    }

}