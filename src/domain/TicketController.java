package domain;

import java.util.List;
import java.util.stream.Collectors;


public class TicketController {

	private TicketManager tm = new TicketManager();
	
	public List<String> getAllTickets() {
        List<Ticket> li = tm.getAllTickets();
    	return li.stream()
        		 .map(a -> a.toString())
        		 .collect(Collectors.toList());
    }
	
	public void close() {
        tm.closePersistence();
    }
}
