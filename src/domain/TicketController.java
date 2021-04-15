package domain;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;


public class TicketController {

	private TicketManager tm = new TicketManager();
	
	public ObservableList<Ticket> getAllTickets() {
        List<Ticket> li = tm.getAllTickets();
    	return (ObservableList<Ticket>) li;
    }
	
	public void close() {
        tm.closePersistence();
    }
}
