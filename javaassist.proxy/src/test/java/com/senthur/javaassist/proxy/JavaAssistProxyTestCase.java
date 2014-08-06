package com.senthur.javaassist.proxy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaAssistProxyTestCase {
	
	@Test
	public void testJavaAssistProxyMethod() {
		Person person = ProxyManager.getProxyPerson(Person.class);
		String result = person.displaySecurityCode();
		Assert.assertTrue(result.contains("Secured"), "Did not Proxy....");
	}
	
	@Test
	public void testJavaAssistNonProxyMethod() {
		Person person = ProxyManager.getProxyPerson(Person.class);
		String result = person.displayName();
		Assert.assertTrue(!result.contains("Secured"), "Did not Proxy....");
	}
	
}
