package domain;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TicketController {

	private TicketManager tm = new TicketManager();
	
	public ObservableList<Ticket> getAllTickets() {
        List<Ticket> li = tm.getAllTickets();
        ObservableList<Ticket> obListTickets = FXCollections.observableList(li);
        return obListTickets;
    }
	
	public void close() {
        tm.closePersistence();
    }
}
