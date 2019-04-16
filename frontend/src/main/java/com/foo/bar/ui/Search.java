package com.foo.bar.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.foo.bar.configuration.ConfigurationItemClassDescriptor;
import com.foo.bar.configuration.ConfigurationItemDao;
import com.foo.bar.configuration.ConfigurationItemFieldDescriptor;
import com.foo.bar.configuration.ConfigurationItemQuery;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SpringView(name="Search")
public class Search extends AbsoluteLayout  implements View {

	@Autowired
	ConfigurationItemDao configurationItemDao;

	@Autowired
	List<ConfigurationItemClassDescriptor> ConfigurationItemClassDescriptores;

	ComboBox<ConfigurationItemClassDescriptor> comboConfigurationItem;

	TextField txtSearch;

	Button btnSearch;

	Button btnNew;

	Grid<?> gridResults;

	Label lblMessage;
	
	public Search() {
		addAttachListener(e -> buildContent());
	}

	protected void buildContent() {
		setSizeFull();
	
		comboConfigurationItem = new ComboBox<ConfigurationItemClassDescriptor>();
		comboConfigurationItem.setItems(ConfigurationItemClassDescriptores);
		comboConfigurationItem.setPlaceholder("Configuration item class");
		comboConfigurationItem.setItemCaptionGenerator( ConfigurationItemClassDescriptor::getName);
		comboConfigurationItem.addValueChangeListener(e -> setCurrentClass(e.getValue()));
		comboConfigurationItem.setWidth("300px");
		txtSearch = new TextField();
		txtSearch.setPlaceholder("");

		btnSearch = new Button(VaadinIcons.SEARCH);
		btnSearch.addClickListener(e -> startSearch());
		btnSearch.setClickShortcut(KeyCode.ENTER);
		btnSearch.setDisableOnClick(true);
		btnNew = new Button(VaadinIcons.FILE_ADD);

		HorizontalLayout barLayout = new HorizontalLayout();
		barLayout.addComponents(comboConfigurationItem, txtSearch, btnSearch, btnNew);
		addComponent(barLayout, "top:20px;left:20px");
		setFieldsVisibile(false);
		
		lblMessage = new Label();
		addComponent(lblMessage,"bottom:10px;left:20px");
	}

	ConfigurationItemClassDescriptor currentClass;

	private void setCurrentClass(ConfigurationItemClassDescriptor value) {
		currentClass = value;
		setFieldsVisibile(value != null);
	}

	protected void setFieldsVisibile(boolean visible) {

		Stream.of(txtSearch, btnSearch, btnNew).forEach(e -> e.setVisible(visible));
	}

	public void startSearch() {

		btnSearch.setEnabled(true);
		if (gridResults != null) {
			removeComponent(gridResults);
		}

		gridResults = new Grid<>();
		gridResults.setSizeFull();
		Column<?,?> buttonColumn = gridResults.addComponentColumn( i->  {
			CssLayout divButtons = new CssLayout();
			Button btnEdit = new Button(VaadinIcons.EDIT);
			btnEdit.setStyleName(ValoTheme.BUTTON_BORDERLESS);
			
			Button btnActions = new Button(VaadinIcons.COG);
			btnActions.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		

			divButtons.addComponents(btnEdit,btnActions);
			return divButtons;
		}).setCaption("Actions");
		
		currentClass.getFields().values().stream().filter(ConfigurationItemFieldDescriptor::isVisible).forEach(v->gridResults.addColumn(i->{
			try {
				return v.getReadMethod().invoke(i);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				return "!!ERROR!!!";
			}
		}).setCaption(v.getLabel()));

		if (gridResults.getColumns().size()==1) {
			gridResults.addColumn(i-> i.toString());
		}

		addComponent(gridResults,"top:100px;left:20px;right:10px;bottom:60px");

		try {
			List<?> found= configurationItemDao.executeSearch(new ConfigurationItemQuery(currentClass, txtSearch.getValue()));
			
			String message = "Found "+found.size()+" cis of type "+currentClass.getName()+" that matches query '"+txtSearch.getValue()+"'";
			
			if (found.size()>50) {
				message+=", first 50  displayed";
			}
			lblMessage.setCaption(message);
			gridResults.setItems((Stream)found.stream().limit(50));
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("ERROR:"+e.getMessage());
		}
		

		
	
	}
}
