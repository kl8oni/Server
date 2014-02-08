package org.smartcity.dao;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import java.math.BigInteger;

class JPAEntityDAOImpl
		implements EntityDAO<EntityManager> {

	@PersistenceContext(
			type = PersistenceContextType.EXTENDED
	)
	private EntityManager em;

	@Override
	public <T> T retrieveEntityByID( BigInteger ID, Class<T> entityClass ) {
		return null;
	}

	@Override
	public void removeEntityByID( BigInteger ID ) {

	}

	@Override
	public EntityManager getEntitiesManager() {
		return em;
	}

}
