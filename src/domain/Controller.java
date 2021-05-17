package domain;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

import gui.TicketEditPanelController;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class Controller {

	/////////////////////////////////////////////////////////////////////
	/////////////////////TechnicianController////////////////////////////
	/////////////////////////////////////////////////////////////////////

	public void addStatusFilterOnTickets(List<? extends TicketStatusEnum> addedSubList) {
	}

	public void removeStatusFilterOnTickets(List<? extends TicketStatusEnum> removed) {
	}

	public void addTypeFilterOnTickets(List<? extends TicketTypeEnum> addedSubList) {
	}

	public void removeTypeFilterOnTickets(List<? extends TicketTypeEnum> removed) {
	}

	public void addTicketListener(PropertyChangeListener pcl) {
	}

	public void setTicket(int ticketNr) {
	}

	public ObservableList<ITicket> getFilteredTickets() {
		return null;
	}

	//////////////////////////////////////////////////////////////////////
	/////////////////// SupportManagerController//////////////////////////
	/////////////////////////////////////////////////////////////////////

	public void updateTicket(TicketStatusEnum selectedItem, String text, String first, String last) {
	}

	public List<String> getAllCompanyNames() {
		return null;
	}

	public List<String> getContactPersonFromCompanyName(String selectedItem) {
		return null;
	}

	public void createTicket(LocalDate value, String text, String text2, TicketTypeEnum value2, String value3, String text3, String text4) {
	}

	public void addReaction(String response) {
	}
	
	public IEmployee getEmployee() {
		return null;
	}
	
	public void createContactPerson(String firstName, String lastName, String email, String companyName) {
		
	}
	
	public void close() {}
	
	public List<String> getAllEmployeesCombo(){return null;}
	
	public void updateCompany(String text, String text2) {		
	}

	public void updateContactPerson(String text, String text2, String text3) {
		
	}

	public void setContactPerson(int index) {
		
	}

	public void createCompany(String companyName, String companyAddress, LocalDate date, Boolean status) {
		
	}

}
