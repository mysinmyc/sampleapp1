package com.foo.bar.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
public class AppUI extends UI {

	@Autowired
	@Value("${git.version}")
	String version;

	private final SpringViewProvider viewProvider;

	@Autowired
	public AppUI(SpringViewProvider viewProvider) {
		this.viewProvider = viewProvider;
	}

	@Override
	protected void init(VaadinRequest request) {
		final AbsoluteLayout root = new AbsoluteLayout();
		root.setSizeFull();

		setContent(root);

		final CssLayout viewContainer = new CssLayout();
		viewContainer.setSizeFull();
		root.addComponent(viewContainer, "top:60px;left:20px;right:20px;bottom:20px");

		Navigator navigator = new Navigator(this, viewContainer);
		navigator.addProvider(viewProvider);

		MenuBar navigationBar = new MenuBar();
		viewProvider.getViewNamesForCurrentUI().stream().filter(e -> !equals(""))
				.forEach(i -> navigationBar.addItem(i, e -> navigator.navigateTo(i)));
		root.addComponent(navigationBar, "top:20px;left:40px");

	}

}
