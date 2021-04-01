package main;
import domain.TicketController;

public class StartUp {
	TicketController tc = new TicketController();
	
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {
		 
		for(String ticket : tc.geefAlleTickets()) { System.out.println(ticket); }
		 
      
    }
    
}
