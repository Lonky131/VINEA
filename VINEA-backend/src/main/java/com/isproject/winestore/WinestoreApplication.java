package com.isproject.winestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
}) //disablea bazu podataka */
public class WinestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinestoreApplication.class, args);
	}

}
