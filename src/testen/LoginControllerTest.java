package testen;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.LoginController;

public class LoginControllerTest {

	private LoginController lc;
	
	@BeforeEach
	void before() {
		lc = new LoginController();
	}
	
	@Test
	void getValidationThrowsExceptionWhenServerNotReachable() {
		Assertions.assertThrows(IOException.class, lc.getValidation("test", "test"));
	}
	
	@Test
	void getValidationWhenMakingCallToServer() {
		Assertions.assertEquals("false", lc.getValidation("test", "test"));
	}
}
