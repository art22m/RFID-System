package Server;

import Server.DatabaseFiles.*;
import Server.DatabaseFiles.Requests.*;
import Server.DatabaseFiles.Responses.*;
import Server.DatabaseModules.*;

/*
* Firewall is used to control access to server manager
*/
public class Firewall implements IDatabase {
    private ServerManager serverManager;

    // Firewall should ckeck if it can connect server with client
    private boolean checkConnection() {
        // Some check stuf
        return true;
    }

    public Firewall() {
        this.serverManager = new ServerManager(new PaymentModule(), new TicketStatusCheckingModule(),
                new TicketStatusUpdationModule(), new TicketExtractionModule());
    }

    @Override
    public IDatabaseResponse execute(IDatabaseRequest request) {
        if (checkConnection()) {
            return serverManager.execute(request);
        }
        return new DatabaseResponse(DatabaseResponseStatus.FAILURE);
    }

}