package com.foo.bar.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.foo.bar.activities.Activity;
import com.foo.bar.activities.ActivityManager;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

@SpringView(name="activities")
public class Activities extends AbsoluteLayout implements View {

	@Autowired
	ActivityManager activityManager;

	Grid<Activity> activitiesGrid;

	public Activities() {

		buildContent();
		addAttachListener(e -> loadRecent());
	}

	protected void buildContent() {
		// LayoutBuilder.builder(this).sizeFull();

		addComponent(new HorizontalLayout(
				new Button("All", e -> loadAll()), 
				new Button("Recent", e -> loadRecent())
				)
			,"top:20px;left:20px");

		activitiesGrid = new Grid<>(Activity.class);
		activitiesGrid.setSizeFull();
		addComponent(activitiesGrid,"top:60px;left:20px;right:20px;bottom:20px");

	}

	public void loadAll() {

		activitiesGrid.setItems(activityManager.getAll());
	}

	public void loadRecent() {

		activitiesGrid.setItems(activityManager.getRecent());
	}

}
