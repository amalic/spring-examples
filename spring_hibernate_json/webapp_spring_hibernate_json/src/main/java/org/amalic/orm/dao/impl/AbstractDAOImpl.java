package org.amalic.orm.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAOImpl {
	
	@PersistenceContext
	protected EntityManager entityManager;

}
