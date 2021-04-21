package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Reactions")
public class Reaction {
	@Id
	public int ReactionId;
    public String Text;
    public boolean IsSolution;
    public String NameUserReaction;
    public boolean ReactionSup;
    @ManyToOne
    @JoinColumn(name="TicketId")
    public Ticket ticket;
    
}
