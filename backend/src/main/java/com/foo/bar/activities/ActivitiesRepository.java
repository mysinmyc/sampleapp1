package com.foo.bar.activities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activity, Long> {

	
		List<Activity> findTop100ByOrderByStartTimeDesc();
}
