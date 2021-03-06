package com.draftking.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HibernateDAOHandler<T> implements InvocationHandler {
	
	
	private JPAGenericDAOImpl<T> daoImpl;
	private Class<T> domainClass;
	public HibernateDAOHandler(JPAGenericDAOImpl<T> daoImpl,Class<T> domainClass){
		this.daoImpl=daoImpl;
		this.domainClass=domainClass;
	}
	
	public Object invoke(Object proxy, Method method,Object[] args)
			throws Throwable {
		
		String methodName=method.getName();
		
		if (methodName.startsWith("findBy") || methodName.startsWith("findAll") || methodName.startsWith("readBy"))
				{
		return daoImpl.executeListQuery(method, domainClass,args);
				}
		return null;
	}
	
	
}
