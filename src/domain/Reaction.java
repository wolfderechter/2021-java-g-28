package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reactions")
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ReactionId;
    public String text;
    public boolean IsSolution;
    private String NameUserReaction;
    public boolean ReactionSup;
    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name="TicketId")
    public Ticket ticket; 
    
    public Reaction() {};
    public Reaction(String text,boolean isSolution,String nameUser,Ticket ticket) {
    	this.text = text;
    	this.IsSolution = isSolution;
    	this.NameUserReaction = nameUser;
    	this.ticket = ticket;
    }
    
    public String getNameUserReaction() {
    	return NameUserReaction;
    }
    
    public String getText() {
    	return text;
    }
}
