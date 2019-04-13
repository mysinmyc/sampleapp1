package com.foo.bar.ui;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.foo.bar.configuration.ConfigurationItemClass;
import com.foo.bar.configuration.ConfigurationItemDao;
import com.foo.bar.configuration.ConfigurationItemQuery;
import com.foo.bar.ui.util.LayoutBuilder;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanPropertySet;
import com.vaadin.flow.router.Route;

@Route(value = "search", layout = MainLayout.class)
public class Search extends Div {

	@Autowired
	ConfigurationItemDao configurationItemDao;

	@Autowired
	List<ConfigurationItemClass> configurationItemClasses;

	ComboBox<ConfigurationItemClass> comboConfigurationItem;

	TextField txtSearch;

	Button btnSearch;

	Button btnNew;

	Grid<?> gridResults;

	public Search() {
		addAttachListener(e -> buildContent());
	}

	protected void buildContent() {

		LayoutBuilder.builder(this).margin("20px");

		comboConfigurationItem = new ComboBox<ConfigurationItemClass>();
		comboConfigurationItem.setItems(configurationItemClasses);
		comboConfigurationItem.setPlaceholder("Configuration item class");
		comboConfigurationItem.setItemLabelGenerator(ConfigurationItemClass::getName);
		comboConfigurationItem.addValueChangeListener(e -> setCurrentClass(e.getValue()));
		comboConfigurationItem.setWidth("300px");
		txtSearch = new TextField();
		txtSearch.setPlaceholder("");

		btnSearch = new Button(VaadinIcon.SEARCH.create());
		btnSearch.addClickListener(e -> startSearch());
		btnSearch.addClickShortcut(Key.ENTER);
		btnNew = new Button(VaadinIcon.FILE_ADD.create());

		HorizontalLayout barLayout = new HorizontalLayout();
		barLayout.setWidthFull();

		barLayout.setPadding(true);
		barLayout.add(comboConfigurationItem, txtSearch, btnSearch, btnNew);
		add(barLayout);
		setFieldsVisibile(false);
	}

	ConfigurationItemClass currentClass;

	private void setCurrentClass(ConfigurationItemClass value) {
		currentClass = value;
		setFieldsVisibile(value != null);
	}

	protected void setFieldsVisibile(boolean visible) {

		Stream.of(txtSearch, btnSearch, btnNew).forEach(e -> e.setVisible(visible));
	}

	public void startSearch() {

		if (gridResults != null) {
			remove(gridResults);
		}

		gridResults = new Grid<>(currentClass.getJavaClass());
		Column<?> buttonColumn = gridResults.addComponentColumn( i->  {
			HorizontalLayout divButtons = new HorizontalLayout();
			divButtons.setPadding(true);
			divButtons.add(new Button(VaadinIcon.EDIT.create()));
			divButtons.add(new Button(VaadinIcon.COG.create()));
			return divButtons;
		}).setHeader("Actions");
	  
		gridResults.setHeightByRows(true);

		add(gridResults);

		try {
			gridResults.setItems(
					configurationItemDao.executeSearch(new ConfigurationItemQuery(currentClass, txtSearch.getValue())));
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("ERROR:"+e.getMessage());
		}
		

		
	
	}
}
