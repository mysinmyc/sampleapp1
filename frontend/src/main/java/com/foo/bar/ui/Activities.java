package com.foo.bar.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.foo.bar.activities.Activity;
import com.foo.bar.activities.ActivityManager;
import com.foo.bar.ui.util.LayoutBuilder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "activities", layout = MainLayout.class)
public class Activities extends Div {

	@Autowired
	ActivityManager activityManager;

	Grid<Activity> activitiesGrid;

	public Activities() {

		buildContent();
		addAttachListener(e -> loadRecent());
	}

	protected void buildContent() {
		// LayoutBuilder.builder(this).sizeFull();

		add(new HorizontalLayout(
				new Button("All", e -> loadAll()), 
				new Button("Recent", e -> loadRecent())
				)
			);

		activitiesGrid = new Grid<>(Activity.class);
		activitiesGrid.setWidthFull();
		activitiesGrid.setHeightByRows(true);
		LayoutBuilder.builder(activitiesGrid).addTo(this);//

	}

	public void loadAll() {

		activitiesGrid.setItems(activityManager.getAll());
	}

	public void loadRecent() {

		activitiesGrid.setItems(activityManager.getRecent());
	}

}
