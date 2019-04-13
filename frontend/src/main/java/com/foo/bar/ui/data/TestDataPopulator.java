package com.foo.bar.ui.data;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.foo.bar.activities.ActivitiesRepository;
import com.foo.bar.activities.Activity;
import com.foo.bar.activities.ActivityStatus;
import com.foo.bar.entities.Host;
import com.foo.bar.entities.ProductiveStage;

@Component
public class TestDataPopulator{

	 @PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	ActivitiesRepository activitiesRepository;
	

	@Transactional
	public void populateAll() {
		populateActivites();
		
		populateStages();
		populateInfrastructure();
	}

	@Transactional
	public void populateStages() {

		for (String currentStageName : new String[] {"test", "uat","production"}) {
			ProductiveStage currentStage  = new ProductiveStage();
			currentStage.setName(currentStageName);
			entityManager.persist(currentStage);
		}
		
	}
	@Transactional
	public void populateInfrastructure() {

		int vNet=10;
		for ( ProductiveStage currentStage : entityManager.createQuery("from ProductiveStage",ProductiveStage.class).getResultList()) {
			vNet++;
			for (int vCnt=0;vCnt<200;vCnt++) {
				Host currentHost = new Host();
				currentHost.setProductiveStage(currentStage);
				currentHost.setName(currentStage.getName()+"host"+vCnt);
				currentHost.setAddress("10."+vNet+".0."+vCnt);
				entityManager.persist(currentHost);
			}
		}
		
	}

	@Transactional
	public void populateActivites() {
		
		for ( int vCnt = 0; vCnt<1000;vCnt++) {
			
			Activity currentActivity = new Activity();
			
			currentActivity.setSubmitter("user"+(vCnt%10+1));
			currentActivity.setName("Generic activity "+vCnt);
			currentActivity.setStatus( vCnt%7 ==0 ? ActivityStatus.FAILED:ActivityStatus.SUCCEDED);
			currentActivity.setStartTime(LocalDateTime.now().minus(Duration.ofHours(vCnt)));
			currentActivity.setStartTime(LocalDateTime.now().minus(Duration.ofHours(vCnt).plus(Duration.ofMinutes(vCnt))));
			activitiesRepository.save(currentActivity);
		}
	}

}
