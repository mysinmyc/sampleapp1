package com.foo.bar.ui.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;

public class LayoutBuilder<T extends Component> {

	T component;
	
	private LayoutBuilder(T component) {
		this.component = component;
	}
	
	public  LayoutBuilder<T> absolutePosition() {
		component.getElement().getStyle().set("position", "absolute");
		return this;		
	}
	
	public  LayoutBuilder<T> relativePosition() {
		component.getElement().getStyle().set("position", "relative");
		return this;		
	}
	
	
	public  LayoutBuilder<T> top(String value) {
		component.getElement().getStyle().set("top", value);
		return this;		
	}
	
	public  LayoutBuilder<T> bottom(String value) {
		component.getElement().getStyle().set("bottom", value);
		return this;		
	}
	
	
	public  LayoutBuilder<T> left(String value) {
		component.getElement().getStyle().set("left", value);
		return this;		
	}
	
	public  LayoutBuilder<T> right(String value) {
		component.getElement().getStyle().set("right", value);
		return this;		
	}
	
	public  LayoutBuilder<T> margin(String value) {
		component.getElement().getStyle().set("margin", value);
		return this;		
	}
	
	public  LayoutBuilder<T> width(String value) {
		component.getElement().getStyle().set("width", value);
		return this;		
	}
	
	public  LayoutBuilder<T> height(String value) {
		component.getElement().getStyle().set("height", value);
		return this;		
	}
	
	public  LayoutBuilder<T> sizeFull() {
		return width("100%").height("100%");
	}
	
	
	public  LayoutBuilder<T> addTo(HasComponents parent) {
		parent.add(component);
		return this;
	}
	
	
	public T build() {
		return component;
	}
	
	public static <X extends Component>  LayoutBuilder<X> builder(X component) {
		return new LayoutBuilder<X>(component);
	}
}
