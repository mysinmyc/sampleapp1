package com.foo.bar.configuration;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foo.bar.configuration.api.ConfigurationItem;

@Configuration
public class SpringConfiguration {

	@Bean
	public List<ConfigurationItemClass> configurationItemClasses(EntityManager entityManager) {
		return entityManager.getMetamodel().getEntities().stream().map(this::buildFromEntityType).filter(c -> c != null)
				.collect(Collectors.toList());

	}

	protected ConfigurationItemClass buildFromEntityType(EntityType<?> entityType) {

		ConfigurationItem annotation = entityType.getJavaType().getAnnotation(ConfigurationItem.class);
		if (annotation == null) {
			return null;
		}
		ConfigurationItemClass configurationItemClass = new ConfigurationItemClass();
		configurationItemClass.setJavaClass(entityType.getJavaType());
		configurationItemClass.setCategory(annotation.category());
		configurationItemClass
				.setName("".equals(annotation.name()) ? entityType.getJavaType().getSimpleName() : annotation.name());
		return configurationItemClass;
	}
}
