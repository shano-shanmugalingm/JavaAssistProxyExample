package com.senthur.javaassist.proxy;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class ProxyManager {

	public static <T> T getProxyPerson(Class<? extends T> clazz) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(clazz);
		proxyFactory.setFilter(new MethodFilter() {
			
			public boolean isHandled(Method filterMethod) {
				if (filterMethod.getName().equals("displayName"))
					return false;
				return true;
			}
		});
		
		Class<?> c = proxyFactory.createClass();
		MethodHandler methodHandler = new MethodHandler() {
			
			public Object invoke(Object self, Method method, Method originalClassMethod, Object[] args) throws Throwable {
				StringBuilder builder = new StringBuilder("Secured Area");
				builder.append(originalClassMethod.invoke(self, args));
				builder.append("Secure Area");
				return builder.toString();
			}
		};
		
		final T instance;
		try {
		instance = (T) c.newInstance();
		((ProxyObject) instance).setHandler(methodHandler);
		return instance;
		} catch (InstantiationException e) {
		throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
		throw new RuntimeException(e);
		}
	}

}
