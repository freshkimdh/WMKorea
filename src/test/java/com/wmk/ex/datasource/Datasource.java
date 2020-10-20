package com.wmk.ex.datasource;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class Datasource {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testdatasource() {
		System.out.println(dataSource);
	}

}
