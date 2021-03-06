package testen;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Company;
import domain.ContactPerson;

import domain.IContactPerson;

import domain.Employee;
import domain.IEmployee;
import domain.Ticket;
import domain.TicketTypeEnum;
import domain.User;
import domain.TicketStatusEnum;



class TicketTest {
//	private Ticket ticket;
//	private ContactPerson cp;
//	private Company comp;
	
//	@BeforeEach
//	public void before() {
//		 = new Calculator();
//		}
//	
	@Test
	void newTicketNeedsContactPersonwithActiveContracts() {
		User user = new User();
		ContactPerson cp = new ContactPerson("Test","ContactPerson", user);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {new Ticket(LocalDate.now(),"test Ticket","ticket voor te testen",TicketTypeEnum.NoImpact,cp,null);});

	}
	
//	@ParameterizedTest
//	@CsvSource({"2.0, 1.0, 1.0", "3.5, 2.5, 1.0", "4.2, 3.1, 1.1"})
//	void nieuwTicketMetOngeldigeGegevens() {
//		
//	}

}
