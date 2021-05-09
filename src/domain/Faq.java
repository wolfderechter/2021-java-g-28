package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity(name = "Faq")
@Table(name = "Faq")


@Access(AccessType.FIELD)
public class Faq implements IFaq {

	@Id
	@Column(name = "id")
	 public int Id;
	
   //  public StringProperty problem;
     public String problem;
     public String[] solution;
     
     protected Faq() {
    	 
     }
     
//     public StringProperty Problem() {
//    	 return problem;
//     }
//     
//     @Override
//	@Access(AccessType.PROPERTY)
     public String getProblem() {
    	 return problem;
     }
     
     public void setProblem(String problem) {
    	 this.problem = problem;
     }

	public String[] getSolution() {
		return solution;
	}

	public void setSolution(String[] solution) {
		this.solution = solution;
	}
     
     
     
     }
