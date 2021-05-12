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

	public void updateTicket(TicketStatusEnum selectedItem, String text) {
	}

	public List<String> getAllCompanyNames() {
		return null;
	}

	public List<String> getContactPersonFromCompanyName(String selectedItem) {
		return null;
	}

	public void createTicket(LocalDate value, String text, String text2, TicketTypeEnum value2, String value3) {
	}

	public void addReaction(String response) {
	}
	
	public IEmployee getEmployee() {
		return null;
	}
	
	public void close() {}

}
