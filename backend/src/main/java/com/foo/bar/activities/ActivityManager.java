package com.foo.bar.activities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ActivityManager {

	@Autowired
	ActivitiesRepository activitiesRepository;
	
	@Transactional
	public synchronized Activity enqueue(Activity activity) {
		activity.setStartTime(LocalDateTime.now());
		activity.setStatus(ActivityStatus.ENQUEUED);

		return activitiesRepository.save(activity);
	}
	
	
	public List<Activity> getAll() {
		return activitiesRepository.findAll();
	}


	public List<Activity> getRecent() {
		return activitiesRepository.findTop100ByOrderByStartTimeDesc();
		
	}
	
}
