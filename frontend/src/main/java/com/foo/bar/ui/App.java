package com.foo.bar.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.foo.bar.EnableFoorBarBackend;
import com.foo.bar.ui.data.TestDataPopulator;
import com.vaadin.flow.spring.annotation.EnableVaadin;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableVaadin
@EnableFoorBarBackend
@EnableTransactionManagement
public class App extends SpringBootServletInitializer implements ApplicationListener<ApplicationReadyEvent>{
	
	@Autowired
	TestDataPopulator testDataPopulator;
	
	  public static void main(String[] args) {
	        SpringApplication.run(App.class, args);
	    }

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		testDataPopulator.populateAll();
	}
  
}
