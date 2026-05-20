package com.test.invoice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pruebatec.invoice.InvoiceApplication;

@SpringBootTest( classes = InvoiceApplication.class )
@ActiveProfiles("test")
class InvoiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
