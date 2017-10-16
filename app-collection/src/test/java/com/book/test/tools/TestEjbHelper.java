package com.book.test.tools;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

public class TestEjbHelper {

	public static  EJBContainer getEjbContainer(){ 
		Properties p = new Properties();
		p.setProperty("DB", "new://Resource?type=DataSource");
		p.setProperty("DB.JdbcDriver", "com.mysql.jdbc.Driver");
		p.setProperty("DB,JdbcUrl", "jdbc:mysql://localhost:3306/dbtest");
		p.setProperty("DB.UserName", "root");
		p.setProperty("DB.Password", "");
		
		EJBContainer ejbContainer = EJBContainer.createEJBContainer(p); 
		return ejbContainer;
	}
}
