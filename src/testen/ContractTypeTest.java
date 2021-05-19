package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;


import domain.ContractType;

class ContractTypeTest {
	
	private ContractType contractType;
	
	@BeforeEach
	public void before() {
		contractType = new ContractType();
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void invalidName(String name) {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> {
					contractType.setName(name);
					});
	}
	
	@ParameterizedTest
	@CsvSource({"0","4","5","6","-3"})
	public void invalidDuration(int duration) {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> {
					contractType.setMinDuration(duration);
					});
	}
	
	@ParameterizedTest
	
	public void createContractTypeWithInvalidData() {
		
	} 
			

	

}
