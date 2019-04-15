package com.foo.bar.ui.security;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("loginPage")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class LoginPage extends Div {

	public LoginPage() {

		setSizeFull();
		getStyle().set("display", "flex").set("align-items","center").set("justify-content", "center");

		LoginForm loginForm = new LoginForm();
		loginForm.setAction("login");
		add(loginForm);
	}
}
