package domain;

import java.util.List;

public class ContractType {

	private String name;
    public ContractTypeCreationMethod CreationMethod;
    public boolean IsOutsideBusinessHours;
    public boolean IsActive;
    public int MaxResponseTime;
    public int MinDuration;
    public double Price;
    public List<Contract> contracts;
    
    protected ContractType() {}
    
    public String getName() {
    	return name;
    }
}