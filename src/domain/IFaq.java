package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;

import javafx.collections.ObservableList;

public interface IFaq {

	String getProblem();
	
	String[] getSolutionArray();
	

}