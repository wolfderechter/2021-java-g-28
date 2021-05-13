package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


@Entity(name = "Faq")
@Table(name = "Faq")


@Access(AccessType.FIELD)
public class Faq implements IFaq {

	@Id
	@Column(name = "id")
	 public int Id;
	
   //  public StringProperty problem;
     public String problem;
     public String solution;
     @Transient
     public String[] solutionArray;
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

    public String getSolution() {
    	return solution;
    }
	public String[] getSolutionArray() {
		return solutionArray;
	}

	public void setSolutionArray(String[] solutionArray) {
		this.solutionArray = solutionArray;
	}
	
	public void convertSolution() {
		solutionArray = solution.subSequence(1, solution.length() - 1).toString().split("\",");
		String[] tempArray = new String[solutionArray.length];
		
		for (int i = 0; i < solutionArray.length; i++) {
			tempArray[i] = solutionArray[i].substring(1, solutionArray[i].length() - 1);
		}
		solutionArray = tempArray;
	}
     
     
}
