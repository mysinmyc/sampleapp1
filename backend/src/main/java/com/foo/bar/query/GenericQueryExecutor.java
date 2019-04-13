package com.foo.bar.query;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.foo.bar.configuration.ConfigurationItemQuery;
import com.foo.bar.configuration.api.ConfigurationItemQueryExecutor;
import com.foo.bar.configuration.api.Searchable;

@Component
public class GenericQueryExecutor implements ConfigurationItemQueryExecutor<Object> {

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public boolean isAvailableFor(ConfigurationItemQuery query) {

		return Stream.of(query.getConfigurationItemClass().getJavaClass().getDeclaredFields()).filter(f->f.getAnnotation(Searchable.class)!=null).findAny().isPresent();
	}

	@Override
	public List<Object> execute(ConfigurationItemQuery query) {

		for (Field currentField : query.getConfigurationItemClass().getJavaClass().getDeclaredFields()) {

			if (currentField.getAnnotation(Searchable.class) == null) {
				continue;
			}
			

			String jpaQueryString = "select c from "+query.getConfigurationItemClass().getJavaClass().getSimpleName()+" c where c."+currentField.getName()+" like ?1";
			 TypedQuery jpaQuery = entityManager.createQuery(jpaQueryString,query.getConfigurationItemClass().getJavaClass());
			 jpaQuery.setParameter(1, "%"+query.getCriteria()+"%");
			 return jpaQuery.getResultList();
			
		}
		return null;
	}

}
