package testen;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.Company;

class CompanyTest {
	
	private Company company;

	@BeforeEach
	public void before() {
	 company = new Company();
	 
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void companyNameCanNotBeNullOrEmpty(String companyName) {
	
	Assertions.assertThrows(IllegalArgumentException.class, 
			() -> {
				company.setCompanyName(companyName);
				});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void companyAddressCanNotBeNullOrEmpty(String companyAddress) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			company.setCompanyAdress(companyAddress);
		});
	}
	
	
	private static Stream<Arguments> parameters() {
		return Stream.of(
				Arguments.of("Apple", "Kortrijksesteenweg 255a, 9830 Sint-Martens-Latem", LocalDate.now(), true),
				Arguments.of("Tesla", "Boomsesteenweg 8\r\n 2630 Aartselaar", LocalDate.now(), false),
				Arguments.of("Mediamarkt", "Kernenergiestraat 56, 2610 Antwerpen", LocalDate.now(), true)
				);			
	}
	
	@ParameterizedTest
	@MethodSource("parameters")
	public void createCorrectCompany(String companyName, String companyAddress, LocalDate date, Boolean status) {
		Company company1 = new Company(companyName, companyAddress, date, status);
		Assertions.assertEquals(companyName, company1.getCompanyName());
		Assertions.assertEquals(companyAddress, company1.getCompanyAdress());
		Assertions.assertEquals(date, company1.getCustomerInitDate());
		Assertions.assertEquals(status, company1.getStatus());
		
	}
	
		
	

}
