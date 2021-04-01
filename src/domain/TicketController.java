package domain;

import java.util.List;
import java.util.stream.Collectors;


public class TicketController {

	private TicketBeheerder tb = new TicketBeheerder();
	
	public List<String> geefAlleTickets() {
        //List<Auto> li = gb.geefAutosMetOnderhoudsbeurt();
        List<Ticket> li = tb.geefAlleTickets();
    	return li.stream()
        		 .map(a -> a.toString())
        		 .collect(Collectors.toList());
    }
	
	public void close() {
        tb.closePersistentie();
    }
}
