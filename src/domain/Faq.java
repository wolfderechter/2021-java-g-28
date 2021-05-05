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


@Entity(name = "Faqs")
@Table(name = "Faqs")
@NamedQueries({
	@NamedQuery(name = "Faq.getAll", query = "SELECT f FROM Faqs f")
})

@Access(AccessType.FIELD)
public class Faq {

	@Id
	@Column(name = "id")
	 public int Id;
     public StringProperty problem;
     public List<String> solution;
     
     protected Faq() {
    	 
     }
     
     public StringProperty Problem() {
    	 return problem;
     }
     
     @Access(AccessType.PROPERTY)
     public String getProblem() {
    	 return problem.getValue();
     }
     
     public void setProblem(String problem) {
    	 this.problem = new SimpleStringProperty(problem);
     }
     
     
     }
