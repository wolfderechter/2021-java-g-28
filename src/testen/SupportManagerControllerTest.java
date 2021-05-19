package testen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.Company;
import domain.Contract;
import domain.DomainManager;
import domain.Employee;
import domain.Faq;
import domain.IFaq;
import domain.ITicket;
import domain.SupportManagerController;
import domain.Ticket;
import domain.TicketStatusEnum;
import domain.TicketTypeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import domain.TicketStatusEnum;
import domain.TicketTypeEnum;


@ExtendWith(MockitoExtension.class)
public class SupportManagerControllerTest {

	//het mock object aanmaken
	@Mock
	private DomainManager domainManagerDummy;
	
	//de te testen klasse
	@InjectMocks
	private SupportManagerController supportManagerController;
	
//	@BeforeEach
//	public void before() {
//		supportManagerController = new SupportManagerController();
//		supportManagerController.setDomainManager(new DomainManager());	
//	}
	
	@Test
	public void testGetAllContractsReturnsContracts() {
		Contract contract = new Contract();
		List<Contract> contracts = new ArrayList<Contract>();
		contracts.add(contract);
		Mockito.when(domainManagerDummy.getAllContracts()).thenReturn(FXCollections.observableArrayList(contracts));
		
		ObservableList<Contract> contractList = supportManagerController.getAllContracts();
	
		Assertions.assertEquals(contracts, contractList);
		
		//controle of mock goed is aangeroepen
		Mockito.verify(domainManagerDummy).getAllContracts();
	}
	
	@Test
    public void testGetAllFaqsReturnsFaqs() {

        Faq faq = new Faq();
        List<Faq> faqs = new ArrayList<Faq>();
        faqs.add(faq);

        Mockito.when(domainManagerDummy.getAllFaqs()).thenReturn(FXCollections.observableArrayList(faqs));
        ObservableList<IFaq> result = supportManagerController.getAllFaqs();
        
        Assertions.assertEquals(faqs, result);
        
        Mockito.verify(domainManagerDummy).getAllFaqs();

    }
	
	@Test
    public void testGetAllCompanyNamesReturnsCompanyNames() {
		
        Company comp = new Company("volvo", "nieuwstraat 3", LocalDate.now(), true);
        List<Company> companies = new ArrayList<Company>();
        companies.add(comp);

        List<String> names = new ArrayList<>();
        names.add("volvo");
        
        
        Mockito.when(domainManagerDummy.getAllCompanies()).thenReturn(FXCollections.observableArrayList(companies));
        List<String> result = supportManagerController.getAllCompanyNames();
        
        Assertions.assertEquals(names, result);
        
        Mockito.verify(domainManagerDummy).getAllCompanies();

    }
	
	@Test
    public void testGetFiltererdTicketsReturnsFilteredTickets() {
		
        Ticket ticket = new Ticket();
        ticket.setType(TicketTypeEnum.NoImpact);
        ticket.setStatus(TicketStatusEnum.Created);
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(ticket);
       
        supportManagerController.addStatusFilterOnTickets(Arrays.asList(TicketStatusEnum.values()));
        supportManagerController.addTypeFilterOnTickets(Arrays.asList(TicketTypeEnum.values()));
        Mockito.when(domainManagerDummy.getAllTickets()).thenReturn(FXCollections.observableArrayList(tickets));
        List<ITicket> result = supportManagerController.getFilteredTickets();
        
        Assertions.assertEquals(tickets, result);
        
        Mockito.verify(domainManagerDummy).getAllTickets();

    }
	
	@Test
    public void GetAllEmployeesComboReturnsListOfAllFullNames() {
        Employee emp1  = new Employee();
        emp1.setFirstName("Fred");
        emp1.setLastName("Test");
        emp1.setRole("TE");
        List<Employee> emplist = new ArrayList<>();
        emplist.add(emp1);
        Mockito.when(domainManagerDummy.getAllEmployees()).thenReturn(FXCollections.observableArrayList(emplist));
        List<String> result= supportManagerController.getAllEmployeesCombo();
        List<String> listnames = new ArrayList<>();
        listnames.add("Fred Test, TE");
        Assertions.assertEquals(listnames,result);
        Mockito.verify(domainManagerDummy).getAllEmployees();
    }
	

}
