package com.foo.bar.ui;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;

@SpringView(name="")
public class Home extends AbsoluteLayout implements View{


	public Home() {
		addComponent(new Label("Welcome "));
	}
}
