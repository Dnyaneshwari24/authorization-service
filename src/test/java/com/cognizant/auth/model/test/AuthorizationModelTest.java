package com.cognizant.auth.model.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.auth.model.AuthUser;

@SpringBootTest
@RunWith(SpringRunner.class)
 class AuthorizationModelTest {
	

	@Test
	void testPensionerBean() {		
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection();
		beanTester.testBean(AuthUser.class);
	}

}
