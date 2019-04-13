package com.foo.bar.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainLayout extends AppLayout implements RouterLayout {

	@Autowired
	@Lazy
	Search searchComponent;

	@Autowired
	@Lazy
	Activities activitiesComponent;

	public MainLayout() {

		buildContent();
	}

	protected void buildContent() {
		setBranding(new Label("Sample application"));
		buildMenu();
	}

	protected void buildMenu() {
		AppLayoutMenu menu = createMenu();
		menu.addMenuItems(new AppLayoutMenuItem(VaadinIcon.SEARCH.create(), "Search", "search"),
				new AppLayoutMenuItem(VaadinIcon.CLOCK.create(), "Activities","activities"),
				new AppLayoutMenuItem(VaadinIcon.EXIT.create(), "Logout", e -> {
					UI.getCurrent().getPage().executeJavaScript("window.location=$0;", "/logout");
				}));
	}

	@Override
	public void showRouterLayoutContent(HasElement content) {

		if (content == null) {
			return;
		}

		setContent((Component) content);
	}
}
